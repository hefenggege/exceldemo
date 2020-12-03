package com.hf.exceldemo.service.impl;

import com.hf.exceldemo.dao.UserMapper;
import com.hf.exceldemo.entity.User;
import com.hf.exceldemo.service.UserService;
import com.hf.exceldemo.utils.ReadExcelUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        //创建处理Excel的类
        ReadExcelUtil readExcel = new ReadExcelUtil();
        //解析excel 获取上传的内容
        List<User> excelInfoList = readExcel.getExcelInfo(file);
        if (excelInfoList != null) {
            for ( User user : excelInfoList) {
                try {
                    userMapper.saveExcelInfo(user);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "上传失败";
                }
            }
            result="200";
        } else {
            result = "上传失败";
        }
        return result;
    }
}