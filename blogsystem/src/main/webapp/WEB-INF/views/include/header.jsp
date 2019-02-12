<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="main-header">
        <a href="#" class="logo">
          <span class="logo-mini">博客</span>
          <span class="logo-lg">我的博客</span>
        </a>
     
        <nav class="navbar navbar-static-top">
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
           
          </a>
    
          <div class="navbar-custom-menu">
              <ul class="nav navbar-nav">
                 <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                      <img src="/static/dist/img/user3-128x128.jpg" class="user-image" alt="User Image">
                      <span class="hidden-xs">Alexander Pierce</span>
                    </a>
                    
                  </li>
                  <!-- Messages: style can be found in dropdown.less-->
                  <li class="dropdown messages-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                      <i class="fa fa-envelope-o"></i>
                      <span class="label label-success">4</span>
                    </a>
                    
                  </li>
                  <li class="dropdown notifications-menu">
                    <a href="/admin/notify" class="dropdown-toggle" >
                      <i class="fa fa-bell-o"></i>
                      <span class="label label-warning" id="unreadcount"></span>
                    </a>
                
                  </li>
                 
                 
                  <li class="dropdown user user-menu">
                      <a href="/user/article/list" class="dropdown-toggle" >
                        <span class="hidden-xs"><i class="fa fa-sign-in"></i> </span>
                      </a>
                      
                    </li>
                </ul>
              </div>
            </nav>
      </header>