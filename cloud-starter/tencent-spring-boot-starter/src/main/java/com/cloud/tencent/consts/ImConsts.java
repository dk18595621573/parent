package com.cloud.tencent.consts;

/**
 * 腾讯云即时通讯IM 常量.
 *
 * @author Luo
 * @date 2023-03-27 14:32
 */
public interface ImConsts {

    /**
     * IM请求处理结果.
     */
    String ACTION_STATUS_OK = "OK";
    String ACTION_STATUS_FAIL = "FAIL";

    /**
     * IM发消息是否同步到发送方（1-同步，2-不同步）.
     */
    Integer SYNC_OTHER_MACHINE_YES = 1;
    Integer SYNC_OTHER_MACHINE_NO = 2;

    /**
     * IM消息对象类型：
     * TIMTextElem	文本消息。
     * TIMLocationElem	地理位置消息。
     * TIMFaceElem	表情消息。
     * TIMCustomElem	自定义消息，当接收方为 iOS 系统且应用处在后台时，此消息类型可携带除文本以外的字段到 APNs。一条组合消息中只能包含一个 TIMCustomElem 自定义消息元素。
     * TIMSoundElem	语音消息。
     * TIMImageElem	图像消息。
     * TIMFileElem	文件消息。
     * TIMVideoFileElem	视频消息。
     */
    String TIM_TEXT_ELEM = "TIMTextElem";
    String TIM_LOCATION_ELEM = "TIMLocationElem";
    String TIM_FACE_ELEM = "TIMFaceElem";
    String TIM_CUSTOM_ELEM = "TIMCustomElem";
    String TIM_SOUND_ELEM = "TIMSoundElem";
    String TIM_IMAGE_ELEM = "TIMImageElem";
    String TIM_FILE_ELEM = "TIMFileElem";
    String TIM_VIDEO_FILE_ELEM = "TIMVideoFileElem";

    /**
     * 微信响应消息类型.
     * WX_MSG_TYPE_EVENT：事件类型，事件类型对应"user_enter_tempsession"表示建立会话
     * WX_MSG_TYPE_TEXT：文本类型
     * WX_MSG_TYPE_TEXT：图片类型
     * WX_MSG_TYPE_TEXT：小程序卡片
     */
    String WX_MSG_TYPE_EVENT = "event";
    String WX_MSG_TYPE_TEXT = "text";
    String WX_MSG_TYPE_IMAGE = "image";
    String WX_MSG_TYPE_APPLET_CARD = "miniprogrampage";

}
