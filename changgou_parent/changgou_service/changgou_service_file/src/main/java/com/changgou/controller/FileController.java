package com.changgou.controller;

import com.changgou.file.FastFDSFile;
import com.changgou.util.FastDFSClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @作者:qipeiqing
 * @时间:2019/07/27 17:36
 */
@RestController
@CrossOrigin  //跨域
public class FileController {
    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        //封装一个文件上传对象
        FastFDSFile fastFDSFile = new FastFDSFile(
                file.getOriginalFilename(),//文件名字
                file.getBytes(),           //文件字节数组
                StringUtils.getFilenameExtension(file.getOriginalFilename()) //获取文件扩展名
        );
        /**
         * 调用FastDFSClient工具类实现文件上传到FastDFS中
         */
        String[] uploads = FastDFSClient.upload(fastFDSFile);
        String url = FastDFSClient.getTrackerUrl() + "/" + uploads[0] + "/" + uploads[1];
        return url;

    }
}
