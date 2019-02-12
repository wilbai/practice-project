package com.wil.entity;

import com.wil.util.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wil on 2018/8/17.
 */
public class ArticleRowMapper implements RowMapper<Article> {
    @Override
    public Article mapperRow(ResultSet rs) throws SQLException {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));
        article.setSimpleContent(rs.getString("simplecontent"));
        article.setPicture(rs.getString("picture"));
        article.setCreateTime(rs.getTimestamp("createtime"));
        return article;
    }
}
