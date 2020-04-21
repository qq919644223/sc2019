package com.xzsd.pc.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(value = "spring.tengxun")
public class TencentUtil {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String bucketName;
    private String path;
    private String qianzhui;
    private String area;

    public COSClient tencentStart(){
        ///getTencent();
        System.out.println(accessKey);
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(area));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }
   /*
    public void getTencent(){
        accessKey = "AKIDQaWw5xCg8IDtFJprNceSzJLXsuergLej";
        secretKey = "C6j34oGR4dvwt0qSkpb5IA4h26JI0QYj";
        bucket = "test-1301706188";
        bucketName = "test-1301706188";
        path = "https://test-1301706188.cos.ap-chengdu.myqcloud.com";
        qianzhui = "test";
        area = "ap-chengdu";
    }


    */


    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQianzhui() {
        return qianzhui;
    }

    public void setQianzhui(String qianzhui) {
        this.qianzhui = qianzhui;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}