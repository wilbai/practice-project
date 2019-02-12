package com.wil.entity;

import com.wil.util.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wil on 2018/8/17.
 */
public class LabelRowMapper implements RowMapper<Label> {
    @Override
    public Label mapperRow(ResultSet rs) throws SQLException {
        Label label = new Label();
        label.setId(rs.getInt("id"));
        label.setName(rs.getString("name"));
        return label;
    }
}
