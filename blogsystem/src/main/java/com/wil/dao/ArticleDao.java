package com.wil.dao;

import com.wil.entity.Article;
import com.wil.entity.ArticleRowMapper;
import com.wil.util.DbHelp;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wil on 2018/8/17.
 */
public class ArticleDao {

    public Article findById(Integer id) {
        String sql = "select * from article where id = ?";
        return DbHelp.queryForObject(sql, new ArticleRowMapper(), id);
    }

    public Integer save(Article article) {
        String sql = "insert into article (title, content, simplecontent, picture, createtime) values (?,?,?,?,?)";
        return DbHelp.executeUpdate(sql, article.getTitle(), article.getContent(), article.getSimpleContent(), article.getPicture(),article.getCreateTime());
    }

    public void update(Article article) {
        String sql = "update article set title=?, content=?, simplecontent=?, picture=?, createtime=? where id = ?";
        DbHelp.executeUpdate(sql, article.getTitle(), article.getContent(), article.getSimpleContent(), article.getPicture(),article.getCreateTime(), article.getId());
    }

    public void delete(Integer id) {
        String sql = "delete from article where id = ?";
        DbHelp.executeUpdate(sql, id);
    }

    public List<Article> findListByParams(Map<String, String> params) {

        String sql = "select * from article a ";
        List<Object> arrays = new ArrayList<>();

        if(StringUtils.isNotEmpty(params.get("labelId"))) {
            sql += ", article_label al where a.id = al.aid and lid = ?";
            arrays.add(params.get("labelId"));
        } else if(StringUtils.isNotEmpty(params.get("keys"))) {
            String keys = "%"+params.get("keys")+"%";
            sql += "where a.title like ?";
            arrays.add(keys);
        }
        sql += " order by a.id desc";
        sql += " limit ?,?";
        arrays.add(Integer.parseInt(params.get("start")));
        arrays.add(Integer.parseInt(params.get("pageSize")));
        System.out.println(sql);
        return DbHelp.queryForList(sql, new ArticleRowMapper(), arrays.toArray());
    }

    public Integer count(Map<String, String> params) {

        String sql = "select count(*) from article a ";
        List<String> arrays = new ArrayList<>();

        if(StringUtils.isNotEmpty(params.get("labelId"))) {
            sql += ", article_label al where a.id = al.aid and lid = ?";
            arrays.add(params.get("labelId"));
        } else if(StringUtils.isNotEmpty(params.get("keys"))) {
            String keys = "%"+params.get("keys")+"%";
            sql += "where a.title like ?";
            arrays.add(keys);
        }
        System.out.println(sql);
        return DbHelp.queryForCount(sql, arrays.toArray());
    }


    public void addArticleLabel(int articleId, int labelId) {
        String sql = "insert into article_label (aid, lid) values (?, ?)";
        DbHelp.executeUpdate(sql, articleId, labelId);
    }

    public void delArticleLabelByAid(Integer id) {
        String sql = "delete from article_label where aid = ?";
        DbHelp.executeUpdate(sql, id);
    }
}
