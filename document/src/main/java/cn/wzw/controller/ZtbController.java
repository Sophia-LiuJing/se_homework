package cn.wzw.controller;

import cn.wzw.domain.Ztb;
import cn.wzw.service.ZtbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ZtbController {
    @Autowired
    private ZtbService ztbService;


    @RequestMapping("/ztbAdd")
    public String add(@RequestParam(value = "content") String content){
        if(content.equals("")){
            return "error";
        }
        ztbService.add(content);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/ztbList")
    public List<Ztb> list(){
        return ztbService.list();
    }

}
