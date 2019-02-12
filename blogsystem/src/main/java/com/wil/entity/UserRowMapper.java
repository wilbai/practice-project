package com.wil.entity;

import com.wil.util.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wil on 2018/8/17.
 */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapperRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setPhone(rs.getString("phone"));
        user.setPassword(rs.getNString("password"));
        return user;
    }
}
