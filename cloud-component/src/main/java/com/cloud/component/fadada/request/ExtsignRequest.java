package com.cloud.component.fadada.request;

import lombok.Data;

/**
 * 手动签署
 *
 * @author mft
 */
@Data
public class ExtsignRequest {

    /**
     * 客户编号
     * 注册账号时返回
     * <p> 必填
     */
    private String customerId;

    /**
     * 交易号
     * 每次请求视为一个交易。只允许长度<=32的英文或数字字符。交易号为接入平台生成，必须保证唯一并自行记录。
     * <p> 必填
     */
    private String transactionId;

    /**
     * 合同编号
     * <p> 必填
     */
    private String contractId;

    /**
     * 文档标题
     * <p> 必填
     */
    private String docTitle;

    /**
     * 定位类型
     * 0-关键字（默认）
     * 1-坐标
     * <p> 非必填
     */
    private String positionType;

    /**
     * 定位关键字
     * 关键字为文档中的文字内容（要能使 用ctrl+f搜索到）。法大大按此关键字进行签章位置的定位，将电子章盖在这个关键字上面。凡出现关键字的地方均会盖上指定用户的电子章，建议关键字在合同中保持唯一。
     * <p> 非必填
     */
    private String signKeyword;

    /**
     * 关键字签章策略
     * 关键字签章策略： 0：所有关键字签章 （默认）； 1：第一个关键字签章 ； 2：最后一个关键字签章；
     * <p> 非必填
     */
    private String keywordStrategy;

    /**
     * 定位坐标
     * JsonArray[SearchLocation]
     * <p>
     * 示例： [{"pagenum":0,"x":350,"y":350}, {"pagenum":1,"x":350.225,"y":750}]
     * <p> 非必填
     */
    private String signaturePositions;

    /**
     * 印章编号
     * 调新增签章接口返回
     * <p> 非必填
     */
    private String signatureId;

    /**
     * 页面跳转URL（签署 结果同步通 知）
     * <p> 非必填
     */
    private String returnUrl;

    /**
     * 签署结果异步通知URL
     * 如果指定，当签章完成后，法大大将 向此URL发送签署结果。
     * <p> 非必填
     */
    private String notifyUrl;

    /**
     * 刷脸成功后是否显示结果页面
     * 1、直接跳转到同步地址， 2、需要用户点击完成验证按钮后才 跳转到同步地址。 默认为2
     * <p> 非必填
     */
    private String resultType;

    /**
     * 合同必读时间
     * 0-60秒
     * <p> 非必填
     */
    private String readTime;

    /**
     * 是否需要短信验证
     * 0：否 1：是 不传值， 默认为否
     * <p> 非必填
     */
    private String readType;

    /**
     * 合同是否必读至末页
     * 0：非必读 1：必读
     * <p> 非必填
     */
    private String requiredRead;

    /**
     * 页面语言
     * zh:中文，en:英文
     * <p> 非必填
     */
    private String lang;

    /**
     * 签章类型
     * 0：全部； 1：标准； 2：手写当接口中该参数传入有值时，以接口传入的配置为准，否则则取运营后台配置
     * <p> 非必填
     */
    private String mobileSignType;

    /**
     * 是否开启手写轨迹
     * 0：关闭 1：开启 手写轨迹仅支持汉字带有当接口中该参数传入有值时，以接口传入的配置为准，否则则取运营后台配置
     * <p> 非必填
     */
    private String writingTrack;

    /**
     * 支持pc手写印章
     * 1：pc端支持手写印章（需 mobil_sign_type签章类型为2时支持）
     * <p> 非必填
     */
    private String pcHandSignature;

    /**
     * 签署意愿方式
     * 1：短信验证； 3：刷脸验证 10：短信验证和刷脸验证二选一当接口中该参数传入有值时，以接口传入的值为准，否则则取运营后台配置
     * <p> 非必填
     */
    private String signVerifyWay;

    /**
     * 签署意愿方式选择人脸识别时，人脸识别失败后自动调整为短信
     * 默认为0 0：不切换； 1：切换
     * <p> 非必填
     */
    private String verifyWayFlag;

    /**
     * 打开环境
     * 0、跳转h5；（默认） 1、支持在客户小程序path中跳转， path的写法如 /page/page1； 2、跳转法大大公证处小程序
     * <p> 非必填
     */
    private String openEnvironment;

    /**
     * 时间戳显示方式
     * 不传默认为1 1：以后台配置为准，如果存在部分签署显示、部分签署不显示的场景，需要配置显示时间戳，以及时间戳显示样式 2：不显示时间戳
     * <p> 非必填
     */
    private String signatureShowTime;

    /**
     * 骑缝章印章的客户编号
     * 需盖骑缝章合同的页数必须大于1 页。
     * <p> 非必填
     */
    private String acrosspageCustomerId;

    /**
     * 骑缝章id
     * 该印章必须属于acrosspage_customer_id，并且该印章不会显示在签署页面，用户签署时直接加盖在合同上。不支持坐标，关键字
     * <p> 非必填
     */
    private String acrossSignatureId;

    /**
     * 关键字偏移量，偏移x位置
     * [-595,595]之间的数字
     * <p> 非必填
     */
    private String keyx;

    /**
     * 关键字偏移量，偏移y位置
     * [-842,842]之间的数字
     * <p> 非必填
     */
    private String keyy;

}
