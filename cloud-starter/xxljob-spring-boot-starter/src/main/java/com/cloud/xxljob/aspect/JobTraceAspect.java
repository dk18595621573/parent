package com.cloud.xxljob.aspect;

import cn.hutool.core.util.StrUtil;
import com.cloud.common.constant.Constants;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.MDC;

import java.io.File;

/**
 * 调度日志记录处理.
 *
 * @author nlsm
 * @date 2023-4-23 09:25:32
 */
@Slf4j
@Aspect
public class JobTraceAspect {

    @Before(value = "@annotation(xxlJob)")
    public void before(final JoinPoint joinPoint, XxlJob xxlJob) {
        String jobLogFileName = XxlJobHelper.getJobLogFileName();
        if (StrUtil.isNotEmpty(jobLogFileName)) {
            int start = jobLogFileName.lastIndexOf(File.separator);
            int end = jobLogFileName.lastIndexOf(".");
            String logId = jobLogFileName.substring(start + 1, end);
            MDC.put(Constants.MDC_TRACE_ID, String.format("%s-%s", xxlJob.value(), logId));
        } else {
            MDC.put(Constants.MDC_TRACE_ID, xxlJob.value());
        }
    }

}
