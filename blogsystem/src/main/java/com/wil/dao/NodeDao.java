package com.wil.dao;

import com.wil.entity.Node;
import com.wil.entity.NodeRowMapper;
import com.wil.util.DbHelp;

import java.util.List;

/**
 * Created by wil on 2018/8/17.
 */
public class NodeDao {

    public Node findById(int id) {
        String sql = "select * from t_node where id = ?";
        return DbHelp.queryForObject(sql,new NodeRowMapper(), id);
    }

    public List<Node> findAll() {
        String sql = "select * from t_node";
        return DbHelp.queryForList(sql, new NodeRowMapper());
    }

}
