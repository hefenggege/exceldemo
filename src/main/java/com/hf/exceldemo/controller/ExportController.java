package com.hf.exceldemo.controller;

import com.hf.exceldemo.service.ExportService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class ExportController {
    @Resource
    private ExportService exportService;


    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response ){
        return exportService.exportExcel(request,response);



    }




}




