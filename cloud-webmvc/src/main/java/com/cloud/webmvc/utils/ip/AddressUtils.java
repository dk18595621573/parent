package com.cloud.webmvc.utils.ip;

import com.cloud.common.utils.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cloud.common.config.SystemConfig;
import com.cloud.common.constant.Constants;
import com.cloud.common.utils.StringUtils;
import com.cloud.webmvc.utils.http.HttpUtils;

import java.util.Map;

/**
 * 获取地址类
 *
 * @author author
 */
public class AddressUtils {
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    // IP地址查询
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        if (SystemConfig.isAddressEnabled()) {
            try {
                String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
                if (StringUtils.isEmpty(rspStr)) {
                    log.error("获取地理位置异常 {}", ip);
                    return UNKNOWN;
                }
                Map obj = JsonUtil.parse(rspStr, Map.class);
                String region = (String) obj.get("pro");
                String city = (String) obj.get("city");
                return String.format("%s %s", region, city);
            } catch (Exception e) {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return UNKNOWN;
    }
}
