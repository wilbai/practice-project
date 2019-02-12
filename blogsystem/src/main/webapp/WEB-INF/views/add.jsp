<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>写文章</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
 <%@ include file="include/css.jsp" %>
 
</head>
<body class="hold-transition skin-blue sidebar-mini">

  <!-- Content Wrapper. Contains page content -->

          <div class="container">
          
            <form method="post" action="/article/add" class="form-horizontal" id="addForm">
              <br>
              
               <div class="form-group">
                  <input type="text" name="title" class="form-control" placeholder="请输入主题标题">
               </div>
               <div class="form-group">
                  <input type="text" name="labelNames" class="form-control" placeholder="请为主题贴上标签，多个标签使用，号分开">
               </div>
               <div class="form-group">
                  <textarea name="content" class="from-control" id="editor"  placeholder="正文从这里开始..."></textarea>
               </div>

               <br>
               <div class="form-group">
                  <button class="btn btn-primary" id="addBtn">发布文章</button>
               </div>
            </form>

          </div>




<%@ include file="include/js.jsp" %>

<script src="/static/plugins/editer/scripts/module.min.js"></script>
<script src="/static/plugins/editer/scripts/hotkeys.min.js"></script>
<script src="/static/plugins/editer/scripts/uploader.min.js"></script>
<script src="/static/plugins/editer/scripts/simditor.min.js"></script>
</body>
<script>
  $(function(){
    var editor = new Simditor({
                textarea: $('#editor'),
                upload: {
                	url: '/article/pic/upload',
                	fileKey:'file' 
                }
            });
    
    $.validator.addMethod("labelValidate", function(value, element) {
    	return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5]+(,[a-zA-Z0-9\u4e00-\u9fa5]+)*$/.test(value);
    	}, "please notice your doc");
    
    $("#addForm").validate({
    	
    	errorClass : "text-danger",
    	errorElement : "span",
    	rules : {
    		title : {
    			required : true
    		},
    		labelNames : {
    			required : true,
    			labelValidate : true
    		},
    		content : {
    			required : true
    		}

    	},
    	messages : {
    		title : {
    			required : "请填写文章标题"
    		},
    		labelNames : {
    			required : "请填写文章标签",
    			labelValidate : "请正确填写文章标签,用英文逗号隔开"
    		},
    		content : {
    			required : "请填写文章内容"
    		}

    	},

    });
    
    
    
  });


</script>
</html>
