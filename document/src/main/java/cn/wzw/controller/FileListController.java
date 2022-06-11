package cn.wzw.controller;

import cn.wzw.domain.FileUrl;
import cn.wzw.service.FileUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FileListController {

    @Autowired
    private FileUrlService fileUrlService;


    @GetMapping("/fileList")
    public List<FileUrl> list(){
        return fileUrlService.list();
    }
}
