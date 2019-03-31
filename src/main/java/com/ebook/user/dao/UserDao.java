/*
 * 广州丰石科技有限公司拥有本软件版权2019并保留所有权利。
 * Copyright 2019, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 */

package com.ebook.user.dao;

import com.ebook.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b><code>UserDao</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/3/3 22:06.
 *
 * @author 谢德奇
 * @since SpringBootDemo ${PROJECT_VERSION}
 */
@Mapper
@Repository
public interface UserDao {

    public User getUser(@Param("name") String name,@Param("password") String password);

    public List<User> listUser();

    public void addUser(@Param("user") User user);

    public void delUser(@Param("id") Integer id);

    public void updateUser(@Param("user") User user);

}
