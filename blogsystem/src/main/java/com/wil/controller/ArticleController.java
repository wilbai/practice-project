package com.wil.controller;

import com.google.gson.Gson;
import com.wil.entity.Article;
import com.wil.entity.Label;
import com.wil.service.ArticleService;
import com.wil.util.AjaxResult;
import com.wil.util.Page;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wil on 2018/8/17.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * @param model
     * @param paperNo 从第几页开始
     * @param keys 搜索关键字
     * @param labelId 文章标签id
     * @return
     * @throws UnsupportedEncodingException
     * 文章列表
     */
    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(name="p", required = false, defaultValue = "1") Integer paperNo,
                       String keys, String labelId) throws UnsupportedEncodingException {

        Page<Article> page = articleService.pageArticleByParams(labelId, keys, paperNo);
        model.addAttribute("page", page);
        model.addAttribute("keys", keys);
        return "list";
    }

    /**
     * 展示文章详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/show/{id:\\d+}")
    public String show(@PathVariable Integer id, Model model) {
        Article article = articleService.findById(id);
        model.addAttribute("article", article);
        return "show";
    }

    /**
     * 新增文章
     * @return
     */
    @GetMapping("/add")
    public String add() {
        return "add";
    }

    @PostMapping("/add")
    public String add(String title, String labelNames, String content) {
        Integer id = articleService.saveArticle(title, labelNames, content);
        return "redirect:/article/show/"+id;
    }

    /**
     * 修改文章
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        Article article = articleService.findById(id);
        List<Label> labelList = articleService.findLabelsByAid(id);
        model.addAttribute("article", article);
        model.addAttribute("labelList", labelList);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, String title, String labelNames, String content) {
        articleService.editArticle(id, title, labelNames, content);
        return "redirect:/article/show/"+id;
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable Integer id) {

        articleService.delArticle(id);
        return new AjaxResult().success();
    }

    /**
     * 图片上传
     * @param req
     * @param resp
     */
    @PostMapping("/pic/upload")
    public void fileUpLoad(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Part part = req.getPart("file");
            String content = part.getHeader("Content-Disposition");
            if(StringUtils.isNotEmpty(content)) {
                String fileName = content.split(";")[2].split("\"")[1];
                String newName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
                File file = new File("d:/upload", newName);
                OutputStream out = new FileOutputStream(file);
                InputStream in = part.getInputStream();
                IOUtils.copy(in, out);
                out.flush();
                out.close();
                in.close();

                //这里必须直接将map传到前端，不能再放在ResultJson中，file_path将找不到
                Map<String, 	Object> map = new HashMap<>();
                map.put("file_path", "/article/pic/download?name=" + newName);
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json;charset=UTF-8");

                PrintWriter writer = resp.getWriter();
                writer.write(new Gson().toJson(map));
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    /**
     * 图片下载--图片展示
     * @param req
     * @param resp
     */
    @GetMapping("/pic/download")
    public void downLoad(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        File file = new File("d:/upload", name);
        try {
            OutputStream out = resp.getOutputStream();
            InputStream in = new FileInputStream(file);
            IOUtils.copy(in, out);
            out.flush();
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
