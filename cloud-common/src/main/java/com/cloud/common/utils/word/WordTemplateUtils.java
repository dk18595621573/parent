package com.cloud.common.utils.word;

import com.cloud.common.exception.ServiceException;
import com.cloud.common.utils.uuid.IdUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Map;

/**
 * word处理工具类(利用freemarker模板生成word)
 * @author peijiawei
 * @date 1/10/23 11:50 AM
 */
@Slf4j
public class WordTemplateUtils {

    private WordTemplateUtils(){};

    public static ThreadLocal<WordTemplateUtils> local = new ThreadLocal<WordTemplateUtils>();

    public static WordTemplateUtils getInstance() {
        if (local.get() == null) {
            WordTemplateUtils wordExportTools = new WordTemplateUtils();
            local.set(wordExportTools);
            return wordExportTools;
        } else {
            return local.get();
        }
    }

    /**
     * 根据模板创建word
     * <p>
     * @param dataMap 需要动态填充的数据
     * @param templateName 模板名称
     * @param templateUrl 生成的word url
     * @param templateName 生成的word名称
     */
    public File createWord(Map<String, Object> dataMap, String templateUrl, String templateName) {
        log.info("获取ftl文件地址 templateUrl:{},templateName:{}", templateUrl, templateName);
        try {
            File outFile = File.createTempFile(IdUtils.simpleUUID(), ".doc");
            @SuppressWarnings("deprecation")
            Configuration configuration = Configuration.getDefaultConfiguration();
            configuration.setDefaultEncoding("UTF-8");
            configuration.setTemplateLoader(new RemoteTemplateLoader(templateUrl));
            Template template = configuration.getTemplate(templateName);
            // 输出文件
            // 如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            if (outFile.exists()) {
                outFile.delete();
            }
            Writer out = null;
            // 生成文件
            try {
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
                // 将模板和数据模型合并生成文件
                template.process(dataMap,out);
            } catch (Exception e) {
                log.error("word文件写入失败", e);
                throw new ServiceException("word文件写入失败");
            } finally {
                out.flush();
                out.close();
            }
            return outFile;
        } catch (Exception e) {
            log.error("word文件写入失败", e);
            throw new ServiceException("word文件写入失败");
        }
    }

}
