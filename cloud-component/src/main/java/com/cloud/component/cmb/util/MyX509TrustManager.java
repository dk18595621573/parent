package com.cloud.component.cmb.util;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * JSSE 信任管理器
 */
public class MyX509TrustManager implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        //  Auto-generated method stub

    }

    @Override
    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        //  Auto-generated method stub

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        //  Auto-generated method stub
        return null;
    }
}
