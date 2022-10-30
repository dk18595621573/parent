package com.cloud.common.logback;

import ch.qos.logback.core.PropertyDefinerBase;

import java.net.InetAddress;
import java.net.UnknownHostException;
 
public class CanonicalHostNamePropertyDefiner extends PropertyDefinerBase {

    @Override
    public String getPropertyValue() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
        }
        return String.valueOf(System.currentTimeMillis());
    }

}
