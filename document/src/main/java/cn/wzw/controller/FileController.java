package cn.wzw.controller;

import cn.wzw.service.FileUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@Controller
public class FileController {
    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;
    @Autowired
    private FileUrlService fileUrlService;
    @GetMapping("/delete/{path}")
    public String uploadCustomPathFile(@PathVariable String path){
        FileSystemUtils.deleteRecursively(new File(UPLOAD_FOLDER + path));
        fileUrlService.delete(path);
        return "ok";
    }

}