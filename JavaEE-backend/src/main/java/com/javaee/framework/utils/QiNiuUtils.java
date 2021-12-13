package com.javaee.framework.utils;


import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import com.javaee.framework.enums.AppCode;
import com.javaee.framework.exception.APIException;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Random;

//七牛云服务
public class QiNiuUtils {
    //域名
    private final static String domain="http://r3tw40sg8.hd-bkt.clouddn.com";
    //ACCESS_KEY
    private final static String accessKey="bu4Sb-1O5TI0JgmqbJMKPXPTKvfPmWKQVpLVB5ZI";
    //SECRET_KEY
    private final static String secretKey="_I0aTnFMMGpWv8Z9v4sDKpp4hb7_GzdY0XZ7Nrjs";
    //空间名
    private final static String bucketName="javaee-audio";

    /**
     * 上传文件
     */
    public static String upLoad(InputStream file, String uploadFileName) {
        //构造一个带指定Zone对象的配置类,Zone.zone0()代表华东地区
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = uploadFileName + randomString();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucketName);
        try {
            Response response = uploadManager.put(file, key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return domain + "/" + putRet.key;
        } catch (QiniuException e) {
            throw new APIException(AppCode.FILE_UPLOAD_FAIL);
        }
    }

    public static String downLoad(String locateUrl)
    {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.privateDownloadUrl(locateUrl);
    }

    public static MultipartFile download2(String locateUrl){
        Auth auth = Auth.create(accessKey, secretKey);
        String finalUrl =auth.privateDownloadUrl(locateUrl);
        String fileName=locateUrl.substring(36);
        HttpUtil.downloadFile(finalUrl, FileUtil.file("D://七牛云"+fileName));
        File file = new File("D://七牛云/"+fileName);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        MultipartFile multipartFile = null;
        try {
             multipartFile=new MockMultipartFile("copy"+file.getName(),file.getName(),null,fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return multipartFile;
    }

    public static boolean deleteFromQN(String key){
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager=new BucketManager(auth,cfg);
        Response response;
        try {
            response = bucketManager.delete(bucketName, key);
        } catch (QiniuException e) {
            throw new APIException(AppCode.AUDIO_DELETE_FAIL);
        }
        return response.statusCode == 200;
    }


    public static String randomString(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

}
