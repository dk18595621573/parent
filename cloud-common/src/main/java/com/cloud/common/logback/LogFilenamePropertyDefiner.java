package com.cloud.common.logback;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.PropertyDefinerBase;
import com.cloud.common.constant.Constants;
import org.apache.commons.lang3.StringUtils;

public class LogFilenamePropertyDefiner extends PropertyDefinerBase {

    @Override
    public String getPropertyValue() {
        String filename = StringUtils.defaultString(getContext().getProperty("log.name"), StringUtils.EMPTY);
        String hostname = getContext().getProperty(CoreConstants.HOSTNAME_KEY);
        if (CoreConstants.UNKNOWN_LOCALHOST.equals(hostname)) {
            String time = String.valueOf(System.currentTimeMillis());
            return StringUtils.isBlank(filename) ? time : filename.concat(Constants.DASH).concat(time);
        }
        return hostname.contains(filename) ? hostname : filename.concat(Constants.DASH).concat(hostname);
    }

}
