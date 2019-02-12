<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>文章详情</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@ include file="include/css.jsp" %>
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">
    
    <style>
        body {
            margin-top: 60px;
        }
        
    </style>
</head>
<body>
       <%@ include file="include/navbar.jsp" %>

              <div class="container">
                    <div class="box">
                            <ul class="breadcrumb" style="background-color: #fff;margin-bottom: 0px;">
                                <li><a href="/article/list">首页</a></li>

                            </ul>
                            
                            <div class="topic-head">
                                <h3 class="title">${article.title}</h3>
                            </div>
                           
                            <div class="topic-body">
                                   ${article.content}
                            </div>
                            <div class="topic-body pull-right">
                                <a href="/article/edit/${article.id}" class="btn btn-primary">修改</a>
                                <a href="javascript:;" class="del btn btn-danger" rel="${article.id}">删除</a>
                            </div>
                        </div>
              </div>

<%@ include file="include/js.jsp" %>
<script>
    $(function () {
        $(".del").click(function() {

            layer.confirm("确定要删除么?", function() {
                $.get("/article/delete/${article.id}").done(function(json) {
                    if(json.state == "success") {
                        layer.alert("删除成功",function() {
                            window.location.href = "/article/list";
                        });
                    } else {
                        layer.msg(json.message);
                    }
                }).error(function() {
                    layer.msg("系统异常");
                });
            });
        });
    });
</script>   
</body>
</html>