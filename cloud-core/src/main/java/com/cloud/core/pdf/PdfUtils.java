package com.cloud.core.pdf;

import cn.hutool.json.JSONUtil;
import com.cloud.common.exception.ServiceException;
import com.cloud.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf识别工具类.
 *
 * @author zenghao
 * @date 2022/11/7
 */
@Slf4j
public class PdfUtils {

    private static final String PDF_PART_FILE = "%s/part-%s.pdf";

    private static final String PNG_PART_FILE = "%s/part-%s.png";

    private static final String PNG_PAGE_PART_FILE = "%s/part-%s-%s.png";

    private static final String IMAGE_PNG = "PNG";

    public static final String TEXT_SPLIT = "@@";

    /**
     * 获取pdf文本数据
     * @param inputStream pdf文件流
     * @return 文本数据
     */
    public static String recognizeText(final InputStream inputStream) {
        try (PDDocument document = PDDocument.load(inputStream);){
            return recognizeText(document);
        } catch (Exception e) {
            log.error("获取PDF文字信息失败：", e);
            throw new ServiceException("获取PDF文字信息失败");
        }
    }

    /**
     * 获取pdf文本数据
     * @param document pdf文档对象
     * @return 文本数据
     */
    public static String recognizeText(final PDDocument document) {
        try {
            //使用PDFTextStripper 工具
            PDFTextStripper tStripper = new PDFTextStripper();
            //设置文本排序，有规则输出
            tStripper.setSortByPosition(true);
            tStripper.setWordSeparator(TEXT_SPLIT);
            //获取所有文字信息
            return tStripper.getText(document);
        } catch (Exception e) {
            log.error("获取PDF文字信息失败：", e);
            throw new ServiceException("获取PDF文字信息失败");
        }
    }

    /**
     * 按页切割pdf文档为新的pdf文件
     * @param inputStream pdf文件流
     * @param filePath 切割后的文件存放目录
     * @return 切割后的文件名列表
     */
    public static List<String> splitPdf(final InputStream inputStream, final String filePath) {
        try (PDDocument document = PDDocument.load(inputStream);) {
            Splitter splitter = new Splitter();
            List<PDDocument> pages = splitter.split(document);

            List<String> files = new ArrayList<>();
            for (int i = 0, len = pages.size(); i < len; i++) {
                PDDocument pd = pages.get(i);
                String file = String.format(PDF_PART_FILE, filePath, i);
                pd.save(file);
                files.add(file);
            }
            return files;
        } catch (IOException e) {
            log.error("切割PDF文件失败：", e);
            throw new ServiceException("切割PDF文件失败");
        }
    }

    /**
     * 按页切割pdf文档为图片
     * @param inputStream pdf文件流
     * @param filePath 切割后的文件存放目录
     * @return 切割后的文件名列表
     */
    public static List<String> splitPdfWithImage(final InputStream inputStream, final String filePath) {
        try (PDDocument document = PDDocument.load(inputStream);) {
            List<String> files = new ArrayList<>();
            PDFRenderer renderer = new PDFRenderer(document);
            for (int i = 0, len = document.getNumberOfPages(); i < len; i++) {
                String file = String.format(PNG_PART_FILE, filePath, i);
                BufferedImage renderImage = renderer.renderImage(i, 5);
                ImageIO.write(renderImage, IMAGE_PNG, new File(file));
                files.add(file);
            }
            return files;
        } catch (IOException e) {
            log.error("切割PDF文件为图片失败：", e);
            throw new ServiceException("切割PDF文件为图片失败");
        }
    }

    /**
     *  切割PDF文件图片
     * @param inputStream PDF文件流
     * @param filePath 截取后文件存放目录
     * @param receiptCount 每页切割数量
     * @return 截取后文件地址
     */
    public static List<String> cutPdfImage(final InputStream inputStream, final String filePath, final Integer receiptCount) {
        try (PDDocument document = PDDocument.load(inputStream);) {
            List<String> files = new ArrayList<>();
            PDFRenderer renderer = new PDFRenderer(document);
            for (int i = 0, len = document.getNumberOfPages(); i < len; i++) {
                BufferedImage renderImage = renderer.renderImage(i, 3);
                int subWidth = renderImage.getWidth();
                int imageHeight = renderImage.getHeight();
                int height = imageHeight / receiptCount;
                for (int j = 0; j < receiptCount; j++) {
                    String file = String.format(PNG_PAGE_PART_FILE, filePath, i, j);
                    BufferedImage image = renderImage.getSubimage(0, j * height, subWidth, height);
                    ImageIO.write(image, IMAGE_PNG, new File(file));
                    files.add(file);
                }
            }
            return files;
        } catch (IOException e) {
            log.error("切割PDF文件为图片失败：", e);
            throw new ServiceException("切割PDF文件为图片失败");
        }
    }

    /**
     * 截图PDF文本和图片
     * @param inputStream PDF文件流
     * @param receiptCount 每页截取数量
     * @param filePath 截取后文件存放地址
     */
    public static List<PartContent> cutTextAndImage(final InputStream inputStream, final int receiptCount, final String filePath) {
        try (PDDocument document = PDDocument.load(inputStream);) {
            PDFRenderer renderer = new PDFRenderer(document);
            List<PartContent> contents = new ArrayList<>();
            String regionName = "region";
            for (int pageIndex = 0, pages = document.getNumberOfPages(); pageIndex < pages; pageIndex++) {
                PDPage page = document.getPage(pageIndex);
                int pageHeight = (int) page.getCropBox().getHeight();
                int pageWidth = (int) page.getCropBox().getWidth();

                int cutHeight = pageHeight / receiptCount;
                Rectangle2D region = new Rectangle(pageWidth, cutHeight);
                for (int j = 1; j <= receiptCount; j++) {
                    //选中一块区域，供后续识别文本和转图片。 此处PDRectangle 原始点为左下角
                    page.setCropBox(new PDRectangle(0, pageHeight - (j * cutHeight), pageWidth, cutHeight));
                    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                    stripper.addRegion(regionName, region);
                    stripper.extractRegions(page);
                    stripper.setWordSeparator(TEXT_SPLIT);
                    stripper.setSortByPosition(true);

                    String text = stripper.getTextForRegion(regionName);
                    if (StringUtils.isBlank(text)) {
                        break;
                    }
                    BufferedImage renderImage = renderer.renderImage(pageIndex, 3);
                    String file = String.format(PNG_PAGE_PART_FILE, filePath, pageIndex, j);
                    ImageIO.write(renderImage, IMAGE_PNG, new File(file));

                    contents.add(new PartContent(text, file));
                }
            }
            return contents;
        } catch (Exception e) {
            log.error("截取PDF文本图片失败：", e);
            throw new ServiceException("截取PDF文本图片失败");
        }
    }

    public static void main(String[] args) {
        try {
            File file = new File(System.getProperty("user.home") + "/Desktop/b.pdf");
            String filePath = System.getProperty("user.home") + "/Desktop/png";

            List<PartContent> contents = cutTextAndImage(new FileInputStream(file), 3, filePath);

            for (PartContent content : contents) {
                System.out.println(JSONUtil.toJsonStr(content));
            }

//            String text = recognizeText(new FileInputStream(file));
//            System.out.println(text);
//            BufferedReader reader = new BufferedReader(new StringReader(text));
//            List<Map<String, String>> list = new ArrayList<>();
//            Map<String, String> map = null;
//            String line = null;
//            while (Objects.nonNull(line = reader.readLine())) {
//                if ("出 账 回 单".equals(line)) {
//                    map = new HashMap<>();
//                    list.add(map);
//                }
//                String[] strings = StrUtil.split(line, TEXT_SPLIT);
//                for (String string : strings) {
//                    final String[] split = StrUtil.split(string, "：");
//                    if (split.length > 1) {
//                        map.put(split[0], split[1]);
//                    }
//                }
//            }
//
//            int i = 1;
//            for (Map<String, String> stringMap : list) {
//                System.out.println((i++) + "=>" + JSONUtil.parseObj(stringMap));
//            }
//            cutPdfImage(new FileInputStream(file), filePath, 118,3);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
