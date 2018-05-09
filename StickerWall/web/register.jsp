<%--
  Created by IntelliJ IDEA.
  User: YangYi
  Date: 2017/11/17
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ include file="WEB-INF/taglib.jsp" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户注册</title>

    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/local.css"/>

    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>

</head>

<body>

<div class="row">
    <div class="col-lg-3"></div>

    <div class="col-lg-6 text-center v-center">

        <h1>用户注册</h1>
        <%--<p class="lead">请输入您的账号和密码来登录</p>--%>

        <br>
        <br>
        <br>

        <div class="col-lg-12">
            <div style="width: 340px; text-align: center; margin: 0 auto;">
                <%--隐藏框--%>
                <input type="hidden" id="formName" name="formName" value="registerForm">

                <div class="input-group" style="width: 340px; text-align: center; margin: 0 auto;">
                    <input class="form-control input-lg" title="至少6个字符、英文开头"
                           placeholder="请输入账号" type="text" id="userName" name="userName">
                    <span id="userNameTips"></span>
                </div>
                <br>
                <div class="input-group" style="width: 340px; text-align: center; margin: 0 auto;">
                    <input class="form-control input-lg" title="密码随你、开心就好"
                           placeholder="请输入密码" type="password" id="password" name="password">
                    <span id="passwordTips"></span>
                </div>
                <br>
                <div class="input-group" style="width: 340px; text-align: center; margin: 0 auto;">
                    <input class="form-control input-lg" title="再输入一次、别弄错哦！"
                           placeholder="重复密码" type="password" id="rePassword" name="rePassword">
                    <span id="rePasswordTips"></span>
                </div>
                <br>
                <div>
                    <input class="btn btn-primary btn-block" type="button" id="submit" value="注册">
                </div>
                <div>
                    <a href="login.jsp">已有账号</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-3"></div>
</div>
<br>
<br>
<br>

</body>
<script type="text/javascript">

    var errorMsg = "";
    $(document).ready(function () {
    });

    $("#submit").click(function () {
        var userName = $("#userName").val();
        var password = $("#password").val();
        var rePassword = $("#rePassword").val();

        if (userName == null || userName == "") {
            return false;
        } else if (password == null || password == "" || rePassword == null || rePassword == "") {
            return false;
        } else if (rePassword != password) {
            $("#rePassword").css("border", "2px solid red");
            return false;
        } else {        //若用户名可用则走下一步

            var date = {
                userName: userName,
                password: password,
            }
            $.ajax({
                type: "post",
                url: "${pgContext}/registerServlet",
                data: date,
                dataType: "json",
                success: function (res) {
                    if (res) {
                        errorMsg = "<span style='color: red'>主人，注册成功啦~</span>";
                        layer.msg(errorMsg, {
                            icon: 1,
                            time: 2000 //3秒关闭（如果不配置，默认是3秒）
                        });
                        location.href = "${pgContext}/index.jsp";
                    } else {
                        errorMsg = "<span style='color: red'>注册失败了，您请重新填写信息~</span>";
                        layer.msg(errorMsg, {
                            icon: 2,
                            time: 3000 //3秒关闭（如果不配置，默认是3秒）
                        });
                    }
                }
            })
        }
    });

    $("#userName").blur(function () {
        var userName = $("#userName").val();

        if (userName == null || userName == "") {

            errorMsg = "请输入用户名！";
            $("#userNameTips").html("<span style='color: red'>" + errorMsg + "</span>");
            $("#userName").css("border", "2px solid red");
            $("#userName").focus();

            return false;
        } else {

            $.ajax({
                type: "post",
                url: "${pgContext}/checkUserNameServlet",
                data: {userName: userName},
                dataType: "json",
                success: function (josnData) {

                    var data = eval(josnData);
                    var res = data.res;

                    if (res) {
                        errorMsg = "用户名可用！";
                        $("#userNameTips").html("<span style='color: #66ccff'>" + errorMsg + "</span>");
                        $("#userName").css("border", "2px solid Lime");
                    } else {
                        errorMsg = "用户名已存在！";
                        $("#userNameTips").html("<span style='color: red'>" + errorMsg + "</span>");
                        $("#userName").focus();
                    }
                }
            })

        }
        return false;
    });

    $("#password").blur(function () {
        var password = $("#password").val();
        if (password == null || password == "") {
            errorMsg = "密码不能为空！";
            $("#passwordTips").html("<span style='color: red'>" + errorMsg + "</span>");
            $("#password").css("border", "2px solid red");
            return false;
        } else {
            $("#passwordTips").html("");
            $("#password").css("border", "2px solid Lime");
            return true;
        }
    });

    $("#rePassword").blur(function () {
        var p = $("#password").val();
        var rep = $("#rePassword").val();
        if (rep != p) {
            errorMsg = "两次密码输入不一致！";
            $("#rePasswordTips").html("<span style='color: red'>" + errorMsg + "</span>");
            $("#rePassword").css("border", "2px solid red");
            return false;
        } else {
            $("#rePasswordTips").html("");
            $("#rePassword").css("border", "2px solid Lime");

            return true;
        }
    });

</script>

</html>
