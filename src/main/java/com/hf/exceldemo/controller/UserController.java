package com.hf.exceldemo.controller;


import com.hf.exceldemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;


    @GetMapping(value = "/index")
    public String index(){
        return "/index";
    }


    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file) {
        String result = userService.readExcelFile(file);
        return result;
    }
}