package com.wil.dao;

import com.wil.entity.User;
import com.wil.entity.UserRowMapper;
import com.wil.util.DbHelp;

/**
 * Created by wil on 2018/8/17.
 */
public class UserDao {

    public User findByPhone(String phone) {
        String sql = "select * from user where phone = ?";
        return DbHelp.queryForObject(sql, new UserRowMapper(), phone);
    }

    public void save(User user) {
        String sql = "insert into user (phone, password) values (?, ?)";
        DbHelp.executeUpdate(sql, user.getPhone(), user.getPassword());
    }
}
