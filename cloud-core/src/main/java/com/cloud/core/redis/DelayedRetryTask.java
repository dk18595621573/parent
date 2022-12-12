package com.cloud.core.redis;

import com.cloud.core.redis.model.DelayRetry;
import lombok.extern.slf4j.Slf4j;

/**
 * 延迟自动重试任务.
 *
 * @author zenghao
 * @date 2022/12/12
 */
@Slf4j
public abstract class DelayedRetryTask<T> extends DelayedTask<DelayRetry<T>> {

    @Override
    public void consumer(final DelayRetry<T> task) {
        try {
            log.info("[RETRY]【{}】第[{}]次重试:{}", getTaskGroup(), task.getCount(), task.getData());
            boolean retry = execute(task.getData());
            if (!retry) {
                throw new RuntimeException("任务处理失败");
            }
        } catch (Exception e) {
            log.error("[RETRY]【{}】第[{}]次重试失败:", getTaskGroup(), task.getCount(), e);
            if (hasNext(task)) {
                long delay = nextTime(task);
                log.info("[RETRY]【{}】准备间隔{}秒后进行第[{}]次重试", getTaskGroup(), delay, task.getCount());
                this.producer(task, delay);
            }
        }
    }

    /**
     * 执行任务
     *
     * @param data 任务数据
     * @return 执行结果，返回false 或抛出异常则重试
     */
    protected abstract boolean execute(T data);

    /**
     * 判断是否开启下一次重试 默认是根据重试次数校验
     *
     * @param task 任务数据
     * @return 开启下一次重试
     */
    public boolean hasNext(final DelayRetry<T> task) {
        return task.hasNext();
    }

    /**
     * 下次重试时间 默认根据 重试次数*间隔时间
     * @param task 任务数据
     * @return 下次重试时间
     */
    public long nextTime(final DelayRetry<T> task) {
        return task.nextTime();
    }
}
