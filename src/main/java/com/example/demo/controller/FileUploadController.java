package com.example.demo.controller;

import com.example.demo.service.StorageService;
import com.example.demo.utils.TencentUploadUtil;
import com.qcloud.cos.ClientConfig;
import com.tencent.cloud.CosStsClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("file")
public class FileUploadController {

    @Autowired
    TencentUploadUtil tencentUploadUtil;

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public void handleFileUpload(@RequestBody MultipartFile file) {
        storageService.store(file);
    }

    @GetMapping("/download")
    public Resource handleFileDownload(@RequestParam("filename") String filename) throws Exception {
        return storageService.loadAsResource(filename);
    }

    @PostMapping("/upload/tencent")
    public String handleFileUploadTencent(@RequestBody MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String path = tencentUploadUtil.uploadFile(name, file);
        return path;
    }

    @GetMapping ("/cosInfo")
    public Map getCosInfo() {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        try {//使用永久密钥生成临时密钥
            config.put("SecretId", tencentUploadUtil.getSecretId());
            config.put("SecretKey", tencentUploadUtil.getSecretKey());
            config.put("bucket", tencentUploadUtil.getBucket());
            config.put("region", tencentUploadUtil.getRegion());
            config.put("allowPrefix", tencentUploadUtil.getAllowPrefix());
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
            String tmpSecretId = credential.getJSONObject("credentials").getString("tmpSecretId");
            String tmpSecretKey = credential.getJSONObject("credentials").getString("tmpSecretKey");
            String sessionToken = credential.getJSONObject("credentials").getString("sessionToken");
            Map map = new HashMap();
            map.put("tmpSecretId", tmpSecretId);
            map.put("tmpSecretKey", tmpSecretKey);
            map.put("sessionToken", sessionToken);
            map.put("startTime", credential.getLong("startTime"));
            map.put("expiredTime", credential.getLong("expiredTime"));
            map.put("bucket", tencentUploadUtil.getBucket());
            map.put("region", tencentUploadUtil.getRegion());
            return map;
        } catch (Exception e) {
            //失败抛出异常
            throw new IllegalArgumentException("no valid secret !");
        }
    }

}
