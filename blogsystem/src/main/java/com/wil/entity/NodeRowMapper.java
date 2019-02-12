package com.wil.entity;

import com.wil.util.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wil on 2018/8/17.
 */
public class NodeRowMapper implements RowMapper<Node> {

    @Override
    public Node mapperRow(ResultSet rs) throws SQLException {
        Node node = new Node();
        node.setId(rs.getInt(1));
        node.setName(rs.getString(2));
        node.setCreateTime(rs.getTimestamp(3));
        return node;
    }
}
