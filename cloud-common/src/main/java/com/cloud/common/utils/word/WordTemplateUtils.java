package com.cloud.common.utils.word;

import com.cloud.common.exception.ServiceException;
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
     * 模板存放位置
     */
    private static final String TEMPLATE_PATH = "/word/ftl/";

    /**
     * 根据模板创建word
     * <p>
     * @param dataMap 需要动态填充的数据
     * @param templateName 模板名称
     * @param filePath 生成word的路径
     * @param templateName 生成的word名称
     */
    public String createWord(Map<String, Object> dataMap, String templateName, String filePath) {
        try {
            filePath = System.getProperty("user.home").concat("/").concat(filePath);
            @SuppressWarnings("deprecation")
            Configuration configuration = Configuration.getDefaultConfiguration();
            configuration.setDefaultEncoding("UTF-8");
            // ftl模板文件统一放至 maven目录src/main/resources word 包下面
            configuration.setClassForTemplateLoading(WordTemplateUtils.class, TEMPLATE_PATH);
            // 获取模板
            Template template = configuration.getTemplate(templateName);
            // 输出文件
            File outFile = new File(filePath);
            // 如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            if (outFile.exists()) {
                outFile.delete();
            }      /* SaveAs ExportAsFixedFormat*/
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
        } catch (Exception e) {
            log.error("word文件写入失败", e);
            throw new ServiceException("word文件写入失败");
        }
        return filePath;
    }

}
