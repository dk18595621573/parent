package com.cloud.common.desensitize.desensitization;

import cn.hutool.core.util.StrUtil;
import com.github.desensitization.StringDesensitization;
import org.apache.commons.lang3.StringUtils;

/**
 * @author nlsm
 * 银行卡（只显示后4位）脱敏器
 */
public class BankCardDesensitization implements StringDesensitization {

    /** 脱敏后只显示4个数字 */
    private final static int LEFT_STR = 4;

    @Override
    public String desensitize(String target) {
        if (StringUtils.isBlank(target)) {
            return "";
        }
        return StrUtil.hide(target, 0, target.length() - LEFT_STR);
    }
}
