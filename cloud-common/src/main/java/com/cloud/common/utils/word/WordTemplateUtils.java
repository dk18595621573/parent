package com.cloud.common.utils.word;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;

/**
 * word处理工具类(利用freemarker模板生成word)
 * @author peijiawei
 * @date 1/10/23 11:50 AM
 */
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
        long start = System.currentTimeMillis();
        //logger.debug("word生成开始计时: {} ", new SimpleDateFormat("hh:mm:ss.SSS").format(start));
        try {
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
                File file = new File(filePath);
                if(file.exists()){
                    file.delete();
                }
                throw new RuntimeException("word文件写入失败",e);
            }
            // 关闭流
            out.flush();
            out.close();
            long end = System.currentTimeMillis();
//            logger.debug("word生成计时结束：{}  耗时：{}",
//                    new SimpleDateFormat("hh:mm:ss.SSS").format(end), end - start);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

}
