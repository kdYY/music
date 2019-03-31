/*
 * 广州丰石科技有限公司拥有本软件版权2019并保留所有权利。
 * Copyright 2019, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 */

package com.ebook.user.controller;

import com.ebook.common.Result;
import com.ebook.user.model.User;
import com.ebook.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b><code>UserController</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/3/3 22:34.
 *
 * @author 谢德奇
 * @since SpringBootDemo ${PROJECT_VERSION}
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value="getUser")
    public Result getUser(String name, String password){
        Result result = new Result();
        result.setObj(userService.getUser(name,password));
        return result;
    }

    @GetMapping("listUser")
    public Result listUser(){
        Result result = new Result();
        result.setObj(userService.listUser());
        int i = 1/0;
        return result;
    }

    @GetMapping(value="addUser")
    public Result addUser(User user){
        userService.addUser(user);
        return new Result();
    }

    @GetMapping(value="updateUser")
    public Result updateUser(User user){
        userService.updateUser(user);
        return new Result();
    }

    @GetMapping(value="delUser")
    public Result delUser(Integer id){
        userService.delUser(id);
        return new Result();
    }

    @GetMapping(value = "gethtml")
    public void gethtml(){

    }

}
