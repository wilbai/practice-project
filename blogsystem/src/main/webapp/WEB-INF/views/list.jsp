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
    <title>文章列表</title>
      <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@ include file="include/css.jsp" %>
    <style>
        body {
            margin-top: 60px;
        }
        
    </style>
</head>
<body>
        <%@ include file="include/navbar.jsp" %>

              <!-- 文章列表开始 -->

        <div class="container">
            <div class="row">
                <div class="col-md-9">
                	<c:forEach items="${page.items}" var="article">
                        <div class="article-span">
                                <div class="media article">
                                        
                                            <div class="media-body">
                                                <a href="/article/show/${article.id}"><span class="media-heading"> ${article.title}</span></a>
                                                <span class="time"><fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>

                                                
    
                                                ${article.simpleContent}
                                                <div class="meta">
                                                <c:forEach items="${article.labelList}" var="label" varStatus="vs"> 
                                                	<a href="/article/list?labelId=${label.id}"> <span
                                                		<c:choose>
					                                		<c:when test="${vs.count==1}">class="label label-primary"</c:when>
					                                		<c:when test="${vs.count==2}">class="label label-danger"</c:when>
					                                		<c:when test="${vs.count==3}">class="label label-success"</c:when>
					                                		<c:otherwise>class="label label-info"</c:otherwise>
					                                	</c:choose>
                                                	>
                                                	${label.name}</span> </a>
                                               </c:forEach>
                                            </div> 
                                            </div>
                                        
                                            <div class="media-right">
                                            	${article.picture}
                                            </div>
                                    </div>
                        	</div>
                        </c:forEach>
    
                        
                        <div class="text-center">
                            <ul id="pagination" class="pagination"></ul>
                        </div>
                </div>    
                <div class="col-md-3">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">操作</h3>
                        </div>
                        <ul class="list-group text-primary">
                            <a href="/article/add" class="btn btn-success ">写文章</a>
                            <a href="/logout" class="btn btn-danger pull-right">安全退出</a>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

             

              
<%@ include file="include/js.jsp" %>
<script src="/static/dist/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function(){
      
        $("#pagination").twbsPagination({
         totalPages:"${page.pageTotal}",
         visiblePages:3,
         href:"/user/article/list?p={{number}}&labelId=${param.labelId}&keys=${keys}",
         first: "首页",
         prev: "上一页",
         next:"下一页",
         last:"末页"
       }); 
        
    });
</script>   
</body>
</html>