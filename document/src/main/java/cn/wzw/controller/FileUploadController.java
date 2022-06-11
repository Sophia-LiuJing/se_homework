package cn.wzw.controller;

import cn.wzw.service.FileUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import java.io.File;

@Controller
public class FileUploadController {

    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;
    @Autowired
    private FileUrlService fileUrlService;

    @PostMapping("/upload")
    public String fileUplaod(@RequestParam(value = "file") MultipartFile file){
        if (file == null) {
            return "error";
        }
        if (file.getSize() > 1024 * 1024 * 1000) {
            return "error";
        }
        //获取文件名
        String suffix = file.getOriginalFilename();
        String savePath = UPLOAD_FOLDER;
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        try {
            //将文件保存指定目录
            file.transferTo(new File(savePath + suffix));
            fileUrlService.add(suffix);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "ok";
    }
}