<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
                <div class="container">
                  <!-- Brand and toggle get grouped for better mobile display -->
                  <div class="navbar-header">
                    <a class="navbar-brand" href="/article/list"><i class="fa fa-weibo"> 阿白的私人博客</i></a>
                  </div>
              
                  <!-- Collect the nav links, forms, and other content for toggling -->
                  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                     <ul class="nav navbar-nav navbar-right">
                     	<li><a href="/logout"><i class="fa fa-home"></i> logout</a></li>
                     </ul>
                    <form action="/article/list" method="get" class="navbar-form navbar-right" >
                      <div class="form-group">
                        <input type="text" class="form-control" name="keys" value="${keys}" placeholder="Search">
                      </div>
                      <button class="btn btn-default"><i class="fa fa-search"></i></button>
                    </form>

                  </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
              </nav>
              <!-- 导航栏结束 -->