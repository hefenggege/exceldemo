package com.hf.exceldemo.dao;

import com.hf.exceldemo.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserMapper {


    int saveExcelInfo(User user);

    List<User> listUser( );





}
