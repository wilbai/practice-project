<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>注册</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/dist/css/font-awesome.css">
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">

    <style type="text/css">
        body{
            background-color:#C7EDE9;
            padding-top: 120px;
        }

    </style>
</head>
<body class="col-sm-offset-3 col-sm-6 col-lg-offset-4 col-lg-4 ">

<div style="background-color: #fff;">
    <section class="content">
        <div class="" style="border-bottom: 1px solid #f0f0f0">
            <div class="box-header">
                <h3 class="text-center">用户注册</h3>
            </div>
        </div>

        <div class="box-solid">
            <div class="box-body">
                <form class="form-horizontal" method="post" id="regForm">
                    <div class="box-body">
                        <h4 class="text-center">${message}</h4>
                        <fieldset>
                            <div class="form-group">
                                <label class="col-sm-1 control-label"><i class="fa fa-user"></i></label>
                                <div class="col-sm-11">

                                    <input type="text" class="form-control"  name="phone"  placeholder="注册帐号">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label"><i class="fa fa-lock"></i></label>
                                <div class="col-sm-11">
                                    <input type="password" class="form-control"  name="password"  placeholder="注册密码">
                                </div>
                            </div>
                        </fieldset>
                        <br>

                    </div>

                    <br>

                </form>
                <button type="button" id="regBtn" class="btn btn-info  btn-lg btn-block">
                    点击注册
                </button>
            </div>
        </div>
    </section>
</div>

<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/bootstrap/js/jquery.validate.js"></script>
<script>
    $(function() {


        $("#regBtn").click(function() {
            $("#regForm").submit();
        });

        $(document).keydown(function() {
            if(event.keyCode == 13) {
                $("#regForm").submit();
            }
        });

        $("#regForm").validate({
            errorClass : "text-danger",
            errorElement : "span",
            rules : {
                phone : {
                    required : true
                },
                password : {
                    required : true
                }
            },
            messages : {
                phone : {
                    required : "请输入手机号"
                },
                password : {
                    required : "请输入密码"
                }
            }

        });

    });


</script>