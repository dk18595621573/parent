package com.cloud.component.bot.request;

import com.cloud.common.utils.StringUtils;
import lombok.Data;

import java.util.Map;

/**
 * 查询联系人信息.
 *
 * @author zenghao
 * @date 2022/8/30
 */
@Data
public class ContactRequest extends PageRequest {

    /**
     * 可选，用来搜索某wxid的用户，当传入此参数时，current 和 pageSize 失效
     */
    private String wxid;

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        if (StringUtils.isNotBlank(wxid)) {
            map.put("wxid", wxid);
        }
        return map;
    }
}
