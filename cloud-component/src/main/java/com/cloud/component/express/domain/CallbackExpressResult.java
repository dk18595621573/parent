package com.cloud.component.express.domain;

import lombok.Data;

/**
 * 快递100推送的快递定位信息
 *
 * @author zhangshu
 * @link <a href="https://api.kuaidi100.com/document/5f0ffa8f2977d50a94e1023c">https://api.kuaidi100.com/document/5f0ffa8f2977d50a94e1023c</a>
 */
@Data
public class CallbackExpressResult {

    /**
     * 监控状态:polling:监控中，shutdown:结束，abort:中止，updateall：重新推送。
     * 其中当快递单为已签收时status=shutdown，当message为“3天查询无记录”或“60天无变化时” status= abort ，
     * 对于status=abort的状态，需要增加额外的处理逻辑
     */
    private String status;

    /**
     * 监控状态相关消息，如:3天查询无记录，60天无变化
     */
    private String message;

    /**
     * 快递公司编码是否出错，
     *      0为本推送信息对应的是提交的原始快递公司编码，
     *      1为本推送信息对应的是纠正后的新的快递公司编码
     */
    private String autoCheck;

    /**
     * 如果autoCheck = 0
     *订阅时提交的原始的快递公司编码
     */
    private String comOld;

    /**
     *纠正后的新的快递公司编码
     */
    private String comNew;

    /**
     *最新查询结果 全量，倒序（即时间最新的在最前）
     */
    private LastResult lastResult;

}
