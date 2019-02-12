package com.wil.dao;

import com.wil.entity.Label;
import com.wil.entity.LabelRowMapper;
import com.wil.util.DbHelp;

import java.util.List;

/**
 * Created by wil on 2018/8/18.
 */
public class LabelDao {

    public List<Label> findByArticleId(Integer id) {
        String sql = "select l.id, l.name from label l, article_label al where l.id = al.lid and al.aid = ?";
        return DbHelp.queryForList(sql, new LabelRowMapper(), id);
    }

    public Label findByName(String name) {
        String sql = "select * from label where name = ?";
        return DbHelp.queryForObject(sql, new LabelRowMapper(), name);
    }

    public int add(Label label) {
        String sql = "insert into label (name) values (?)";
        return DbHelp.executeUpdate(sql, label.getName());
    }
}
