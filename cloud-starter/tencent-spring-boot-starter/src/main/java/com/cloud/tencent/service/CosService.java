package com.cloud.tencent.service;

import com.cloud.tencent.properties.CosProperties;
import com.cloud.tencent.strategy.RenameStrategy;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * cos服务，提供cos基础方法.
 *
 * @author zenghao
 */
@Slf4j
@AllArgsConstructor
public class CosService {

    private final CosProperties cosProperties;

    private final COSClient cosClient;

    /**
     * 创建Bucket.
     *
     * @param bucketName Bucket名称
     */
    public void createBucket(final String bucketName) {
        boolean exist = cosClient.doesBucketExist(bucketName);
        if (exist) {
            log.warn("The bucket [{}] is exist.", bucketName);
            return;
        }
        cosClient.createBucket(bucketName);
    }

    /**
     * 获取cos域名.
     *
     * @return cos域名
     */
    public String getCosDomain() {
        return cosProperties.getDomain();
    }

    /**
     * 获取文件完整路径，在传入路径前拼接cos域名.
     *
     * @param path 文件路径
     * @return 文件完整路径
     */
    public String getFileUrl(final String path) {
        return String.format("%s/%s", this.getCosDomain(), path);
    }

    /**
     * 文件上传到腾讯云COS.
     *
     * @param url     文件url，请查阅文件协议。本地文件以file:// 、远程http以 http:// 开头.
     * @param oldName 原始文件名
     * @return 上传后文件路径
     */
    public String upload(final String url, final String oldName) {
        InputStream inputStream = null;
        try {
            Resource resource = new UrlResource(url);
            inputStream = resource.getInputStream();

            return this.upload(inputStream, oldName);
        } catch (IOException e) {
            log.error("文件【{}】上传到OSS失败,失败原因为：{}", oldName, e);
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("关闭inputStream流失败", e);
                }
            }
        }
    }

    /**
     * 文件上传到腾讯云COS.
     *
     * @param inputStream 文件流
     * @param oldName     原始文件名
     * @return 上传后文件路径
     */
    public String upload(final InputStream inputStream, final String oldName) {
        return this.upload(inputStream, oldName, null);
    }

    /**
     * 文件按比列压缩上传到腾讯云COS.
     *
     * @param inputStream 文件流
     * @param oldName     原始文件名
     * @param strategy    文件自定义策略
     * @return 上传后文件路径
     */
    public String upload(final InputStream inputStream, final String oldName, final RenameStrategy strategy) {
        if (inputStream == null || !StringUtils.hasText(oldName)) {
            return null;
        }
        RenameStrategy renameStrategy = strategy != null ? strategy : RenameStrategy.defaultStrategy();

        //设置新的文件名
        String ext = oldName.substring(oldName.lastIndexOf('.') + 1);
        String lastName = renameStrategy.getFileName().rename(oldName);
        String filePath = String.format("%s/%s.%s", renameStrategy.getFilePath().rename(oldName), lastName, ext);

        try {
            File tempFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), ext);
            // 写入本地临时文件再上传
            log.debug("文件：{} 写入临时文件 {}", oldName, tempFile.getPath());
            OutputStream out = new FileOutputStream(tempFile);
            FileCopyUtils.copy(inputStream, out);

            syncUpload(oldName, filePath, tempFile);
        } catch (IOException e) {
            log.error("文件【{}】上传到OSS失败,失败原因为：{}", oldName, e);
            return null;
        }
        return filePath;
    }

    /**
     * 同步上传.
     *
     * @param filename 上传文件名
     * @param filePath 上传文件路径
     * @param tempFile 上传的文件
     */
    private void syncUpload(final String filename, final String filePath, final File tempFile) {
        try (InputStream inputStream = new FileInputStream(tempFile)) {
            // 创建上传Object的Metadata
            ObjectMetadata meta = new ObjectMetadata();
            //设置ContentLength
            meta.setContentLength(inputStream.available());
            log.debug("开始上传：{} 文件至 COS", filename);
            PutObjectResult putObjectResult = cosClient.putObject(cosProperties.getBucketName(), filePath, inputStream, meta);
            log.debug("文件【{}】上传至COS：【{}】 成功，返回数据: {}", filename, filePath, putObjectResult.getETag());
            //上传完成删除临时文件
            if (!tempFile.delete()) {
                log.warn("删除文件【{}】失败", tempFile.getAbsolutePath());
            }
        } catch (Exception e) {
            log.error("文件【{}】上传到COS失败,失败原因为：{}", filename, e);
        }
    }

    /**
     * 下载cos中的文件.
     *
     * @param filePath     文件路径(非完整路径)
     * @param outputStream 输出流
     * @return true 下载成功， false 下载失败
     */
    public boolean download(final String filePath, final OutputStream outputStream) {
        try {
            COSObject cosObject = cosClient.getObject(cosProperties.getBucketName(), filePath);
            StreamUtils.copy(cosObject.getObjectContent(), outputStream);
            return true;
        } catch (Exception e) {
            log.error("【{}】下载失败,失败原因为：{}", filePath, e);
            return false;
        }
    }

    /**
     * 删除cos中的文件.
     *
     * @param filePath 文件路径(非完整路径)
     * @return true 删除成功， false 删除失败
     */
    public boolean delete(final String filePath) {
        try {
            if (cosClient.doesObjectExist(cosProperties.getBucketName(), filePath)) {
                cosClient.deleteObject(cosProperties.getBucketName(), filePath);
            }
            return true;
        } catch (Exception e) {
            log.error("【{}】删除失败,失败原因为：{}", filePath, e);
            return false;
        }
    }

}
