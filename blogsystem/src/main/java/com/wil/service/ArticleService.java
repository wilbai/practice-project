package com.wil.service;

import com.wil.dao.ArticleDao;
import com.wil.dao.LabelDao;
import com.wil.entity.Article;
import com.wil.entity.Label;
import com.wil.util.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by wil on 2018/8/17.
 * article业务逻辑处理类
 */
@Service
public class ArticleService {

    ArticleDao articleDao = new ArticleDao();
    LabelDao labelDao = new LabelDao();

    public Page<Article> pageArticleByParams(String labelId, String keys, Integer paperNo) {

        Map<String, String> params = new HashMap<>();
        params.put("labelId", labelId);
        params.put("keys", keys);

        Integer count = articleDao.count(params);
        Page<Article> pages = new Page<>(paperNo, count);

        params.put("start", String.valueOf(pages.getStart()));
        params.put("pageSize", String.valueOf(pages.getPageSize()));

        List<Article> articles = articleDao.findListByParams(params);
        for(Article a : articles) {
            List<Label> labelList = labelDao.findByArticleId(a.getId());
            a.setLabelList(labelList);
        }
        pages.setItems(articles);
        return pages;
    }


    public Article findById(Integer id) {
        return articleDao.findById(id);
    }

    public Integer saveArticle(String title, String labelNames, String content) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreateTime(new Timestamp(System.currentTimeMillis()));

        //jsoup解析html,找到第一个p标签和img标签
        Document doc = Jsoup.parseBodyFragment(content);
        String pElement = doc.select("p").first().toString();
        Element pic = doc.select("img").first();
        //一定要判断是pic是否为空，否则当没有插入图片时会抛NullPointer
        if(pic != null) {
            pic.attr("width", "128px");
            pic.attr("height", "124px");
            article.setPicture(pic.toString());
        }

        article.setSimpleContent(pElement);

        int articleId = articleDao.save(article);

        //保存labels
        String[] names = labelNames.split(",");

        for(String name : names) {
            Label label = labelDao.findByName(name);
            if(label != null) {
                articleDao.addArticleLabel(articleId, label.getId());
            } else {
                label = new Label();
                label.setName(name);
                int id = labelDao.add(label);
                articleDao.addArticleLabel(articleId, id);
            }
        }
        return articleId;
    }

    public void editArticle(Integer id, String title, String labelNames, String content) {
        Article article = articleDao.findById(id);
        if(article != null) {
            article.setTitle(title);
            article.setContent(content);

            //jsoup解析html,找到第一个p标签和img标签
            Document doc = Jsoup.parseBodyFragment(content);
            String pElement = doc.select("p").first().toString();
            Element pic = doc.select("img").first();
            //一定要判断是pic是否为空，否则当没有插入图片时会抛NullPointer
            if(pic != null) {
                pic.attr("width", "128px");
                pic.attr("height", "124px");
                article.setPicture(pic.toString());
            }

            article.setSimpleContent(pElement);
            articleDao.update(article);

            //处理labelNames
            articleDao.delArticleLabelByAid(id);
            String[] names = labelNames.split(",");
            for(String name : names) {
                Label label = labelDao.findByName(name);
                if(label == null) {
                    label = new Label();
                    label.setName(name);
                    Integer labelId = labelDao.add(label);
                    articleDao.addArticleLabel(id, labelId);
                } else {
                    articleDao.addArticleLabel(id, label.getId());
                }
            }

        }
    }

    public void delArticle(Integer id) {
        //根据article_id删除article_label表中数据
        articleDao.delArticleLabelByAid(id);
        articleDao.delete(id);
    }

    public List<Label> findLabelsByAid(Integer aId) {
        return labelDao.findByArticleId(aId);
    }
}
