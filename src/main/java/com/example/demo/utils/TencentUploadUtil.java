package com.example.demo.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.tencent.cloud.CosStsClient;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.TreeMap;

@Component
@Data
public class TencentUploadUtil {

    @Value("${cos.accessKey}")
    private String secretId;
    @Value("${cos.secretKey}")
    private String secretKey;
    @Value("${cos.bucketName}")
    private String bucket;
    @Value("${cos.regionName}")
    private String region;
    @Value("${cos.allowPrefix}")
    private String allowPrefix;
    @Value("${cos.baseUrl}")
    private String baseUrl;

    public String uploadFile(String path, MultipartFile file) throws IOException {
        //获取临时密钥
        JSONObject temp = getTempKey();
        // 用户基本信息:解析临时密钥中的相关信息
        String tmpSecretId = temp.getJSONObject("credentials").getString("tmpSecretId");
        String tmpSecretKey = temp.getJSONObject("credentials").getString("tmpSecretKey");
        String sessionToken = temp.getJSONObject("credentials").getString("sessionToken");

        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(tmpSecretId, tmpSecretKey);
        // 2 设置 bucket 区域
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 3 生成 cos 客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket名需包含appid
        String bucketName = bucket;
        // 上传 object, 建议 20M 以下的文件使用该接口
        // 设置 x-cos-security-token header 字段
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setSecurityToken(sessionToken);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, path, file.getInputStream(), objectMetadata);
        String rtValue = null;
        try {
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            rtValue = baseUrl + path;
        } catch (Exception e) {
            // 关闭客户端
            cosclient.shutdown();
            //返回文件的网络访问url
            return rtValue;
        }finally {
            // 关闭客户端
            cosclient.shutdown();
            //返回文件的网络访问url
            return rtValue;
        }
    }


    private JSONObject getTempKey() {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        try {//使用永久密钥生成临时密钥
            config.put("SecretId", secretId);
            config.put("SecretKey", secretKey);
            config.put("bucket", bucket);
            config.put("region", region);
            config.put("allowPrefix", allowPrefix);

            String[] allowActions = new String[]{
                    // 简单上传
                    "name/cos:PutObject",
                    // 表单上传、小程序上传
                    "name/cos:PostObject",
                    // 分片上传
                    "name/cos:InitiateMultipartUpload",
                    "name/cos:ListMultipartUploads",
                    "name/cos:ListParts",
                    "name/cos:UploadPart",
                    "name/cos:CompleteMultipartUpload"
            };
            config.put("allowActions", allowActions);
            JSONObject credential = CosStsClient.getCredential(config);

            return credential;
        } catch (Exception e) {
            //失败抛出异常
            throw new IllegalArgumentException("no valid secret !");
        }
    }

}
