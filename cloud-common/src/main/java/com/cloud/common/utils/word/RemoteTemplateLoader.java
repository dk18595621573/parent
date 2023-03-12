package com.cloud.common.utils.word;

import freemarker.cache.URLTemplateLoader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author peijiawei
 * @date 3/12/23 4:12 PM
 */
@Slf4j
public class RemoteTemplateLoader extends URLTemplateLoader {
    private String remotePath;

    public RemoteTemplateLoader(String remotePath) {
        if (remotePath == null) {
            throw new IllegalArgumentException("remotePath is null");
        }
        this.remotePath = canonicalizePrefix(remotePath);
        if (this.remotePath.indexOf('/') == 0) {
            this.remotePath = this.remotePath.substring(this.remotePath.indexOf('/') + 1);
        }
    }

    @Override
    public Object findTemplateSource(String name) throws IOException {
        return super.findTemplateSource(name);
    }

    @Override
    protected URL getURL(String name) {
        name = name.replace("_zh", "");
        name = name.replace("_CN", "");
        name = name.replace("_#Hans", "");
        name = name.replace("_en", "");
        String fullPath = this.remotePath + name;
        log.info("远程URL地址:{}",fullPath);
        if ((this.remotePath.equals("/")) && (!isSchemeless(fullPath))) {
            return null;
        }

        URL url = null;
        try {
            url = new URL(fullPath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static boolean isSchemeless(String fullPath) {
        int i = 0;
        int ln = fullPath.length();

        if ((i < ln) && (fullPath.charAt(i) == '/'))
            i++;

        while (i < ln) {
            char c = fullPath.charAt(i);
            if (c == '/')
                return true;
            if (c == ':')
                return false;
            i++;
        }
        return true;
    }
}
