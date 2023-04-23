package com.cloud.component.express.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.cloud.common.core.model.LogisticsStatus;
import com.cloud.common.exception.ServiceException;
import com.cloud.component.express.domain.ExpressResult;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.Date;
import java.util.Objects;

/**
 * @author nlsm
 * 物流工具处理类
 */
@UtilityClass
public class ExpressUtils {

    /** 快递100 订阅地址 */
    public static final String EXPRESS_CALLBACK_URL = "%s/express/callbackExpress?orderId=1&expressCode=1&expressNo=1&cellphone=1";

    /** 快递单号最小长度 */
    public final static int EXPRESS_NO_MIN = 6;
    /** 快递单号最大长度 */
    public final static int EXPRESS_NO_MAX = 32;
    /** 下划线 */
    public final static String UNDERLINE =  "_";
    /** 揽收超时时间 */
    private final static Integer COLLECT_TIMEOUT = 24;

    /**
     * 判断揽收时间是否超时
     * @param startTime 起始时间
     * @param endTime 结束时间
     * @return true超时 false未超时
     */
    public static Boolean collectTimeout(Date startTime, Date endTime) {
        return DateUtil.between(startTime, endTime, DateUnit.HOUR, true) >= COLLECT_TIMEOUT;
    }

    /**
     * 提取手机号
     * @param cellphone 手机号
     * @return 截取之后手机号
     */
    public static String phoneCutOut(String cellphone) {
        // 去除特殊字符
        if (StrUtil.isBlank(cellphone)) {
            throw new ServiceException("收货人手机号未查询到");
        }
        // 截取手机号
        String phone = ReUtil.getGroup0(PatternPool.MOBILE, cellphone);
        if (Objects.isNull(phone)) {
            // 没有找到手机号 截取空格
            phone = cellphone.replaceAll(" ", "").replaceAll("[\"'\\[\\]]+", "");
        }
        return phone;
    }

    /**
     * 处理快递单号
     * @param expressNo 快递单号
     * @return 快递单号
     */
    public static String returnExpressNo(String expressNo) {
        return expressNo.replaceAll("[^((?!\\da-zA-Z).)*]", "");
    }

    /**
     * 校验快递单号格式
     * @param expressNo 快递单号
     */
    public static void checkExpressNo(String expressNo){
        //快递单号最小长度6个字符，最大长度32个字符
        if (expressNo.length() < EXPRESS_NO_MIN || expressNo.length() > EXPRESS_NO_MAX){
            throw new ServiceException("填写快递单号["+ expressNo +"]不支持");
        }
        //快递单号 只能录入英文字母 、数字
        if (Validator.isMatchRegex(PatternPool.GENERAL, expressNo)){
            //判断是否有下划线
            if (StrUtil.contains(expressNo, UNDERLINE)){
                //快递单号包含下划线
                throw new ServiceException(StrUtil.format("录入快递单号[{}]格式不正确", expressNo));
            }
        }else {
            // 快递单号 不只是包含英文字母 、数字和下划线
            throw new ServiceException(StrUtil.format("录入快递单号[{}]格式不正确", expressNo));
        }
    }

    /**
     * 默认快递
     * @param expressCode 快递公司
     * @param expressNo 快递单号
     * @param subscribed 快递是否订阅
     * @return 详情信息
     */
    public ExpressResult defaultExpress(final String expressCode, final String expressNo, final Boolean subscribed) {
        ExpressResult routes = new ExpressResult();
        routes.setCom(expressCode);
        routes.setNu(expressNo);
        routes.setState(LogisticsStatus.STATE_NO);
        routes.setSubscribed(subscribed);
        return routes;
    }

    /**
     * 默认快递
     * @param expressCode 快递公司
     * @param expressNo 快递单号
     * @param subscribed 快递是否订阅
     * @param state 状态
     * @return 详情信息
     */
    public ExpressResult defaultExpress(String expressCode, String expressNo, Boolean subscribed, String state) {
        ExpressResult routes = new ExpressResult();
        routes.setCom(expressCode);
        routes.setNu(expressNo);
        routes.setState(state);
        routes.setSubscribed(subscribed);
        return routes;
    }

    /**
     * 判断是否添加数据还是默认数据
     * @param expressResult 数据
     * @param shipTime 默认时间
     * @return 默认数据
     */
    public void defaultItem(ExpressResult expressResult, Date shipTime){
        if (CollectionUtil.isEmpty(expressResult.getData())) {
            expressResult.setData(Collections.singletonList(ExpressUtils.defaultItem(shipTime)));
        } else {
            expressResult.getData().add(0, ExpressUtils.defaultItem(shipTime));
        }
    }

    /**
     * 默认详情信息
     * @param shipTime 订阅时间
     * @return 明细信息
     */
    public ExpressResult.ExpressItem defaultItem(Date shipTime) {
        ExpressResult.ExpressItem item = new ExpressResult.ExpressItem();
        item.setContext("已下单");
        item.setFtime(DateUtil.formatDateTime(shipTime));
        return item;
    }
}
