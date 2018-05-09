<%--
  Created by IntelliJ IDEA.
  User: YangYi
  Date: 2017/11/17
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ include file="WEB-INF/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>

    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/local.css"/>

    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>

</head>

<body>

<div class="row">

    <div class="col-lg-12 text-center v-center">

        <h1>Login</h1>
        <p class="lead">请输入您的账号和密码来登录</p>
        <br>
        <br>
        <div class="col-lg-12">
            <%--隐藏框--%>
            <input type="hidden" name="return_url" id="return_url" value="${return_url}">
            <input type="hidden" name="login_user" id="login_user" value="${login_user}">
            <%--隐藏域结束--%>

            <div class="input-group" style="width: 340px; text-align: center; margin: 0 auto;">
                <input class="form-control input-lg" title="Confidential signup."
                       placeholder="请输入账号" type="text" id="userName" name="userName">
                <span id="userNameTips"></span>
            </div>
            <br>
            <div class="input-group" style="width: 340px; text-align: center; margin: 0 auto;">
                <input class="form-control input-lg" title="Confidential signup."
                       placeholder="请输入密码" type="password" id="password" name="password">
                <span id="passwordTips"></span>
            </div>
            <br>
            <div>
                <input class="btn btn-lg btn-primary" type="button" id="submit" value="登录"
                       style="margin: 10px;">
                <a class="btn btn-lg btn-warning" href="index.jsp">返回</a>
            </div>
        </div>
    </div>
</div>
<div class="text-center">
    <span>加入我们><a href="register.jsp">注册</a></span>
</div>
<div class="text-center">
    <span>我是管理员><a href="admin/admin_login.jsp">去登录</a></span>
</div>
</body>

<script>
    var errorMsg = "";
    $(document).ready(function () {

        $("#submit").click(function () {
            var return_url = $("#return_url").val();
            var userName = $("#userName").val();
            var password = $("#password").val();

            //处理从其他页面直接点击登录进来的URL
            var URL = window.localStorage.getItem("return_url");
            if (return_url == null || return_url == "") {
                return_url = URL;
            }
            if (isLogin()) {
                errorMsg = "<span style='color: red'>亲，您已在登录了，请不要重复登录~</span>";
                layer.msg(errorMsg, {
                    icon: 2,
                    time: 3000 //3秒关闭（如果不配置，默认是3秒）
                });
                return false;
            } else {
                if (userName == null || userName == "") {
                    return false;
                } else if (password == null || password == "") {
                    return false;
                } else {
                    var data = {
                        userName: userName,
                        password: password,
                        return_url: return_url
                    }
                    $.ajax({
                        type: "post",
                        url: "${pgContext}/loginServlet",
                        data: data,
                        dataType: "json",
                        success: function (jsonData) {
                            var data = eval(jsonData);
                            var res = data.res;
                            var return_url = data.return_url;

                            if (res == 1) {
                                errorMsg = "<span style='color: red'>主人，欢迎您的到来~</span>";

                                layer.msg(errorMsg, {
                                    icon: 1,
                                    time: 2000 //3秒关闭（如果不配置，默认是3秒）
                                });
                                if (return_url != null && return_url != '/login.jsp' && return_url != '') {
                                    location.href = return_url;
                                } else {
                                    location.href = "${pgContext}/index.jsp";
                                }
                            } else {
                                errorMsg = "<span style='color: red'>账号或密码错误，请重新登录~</span>";
                                layer.msg(errorMsg, {
                                    icon: 2,
                                    time: 3000 //3秒关闭（如果不配置，默认是3秒）
                                });
                                $("#passwordTips").html(errorMsg);
                                $("#password").css("border", "1px solid red");
                            }
                        }
                    })
                }
            }

        })
    });

    //    验证用户是否已经登录
    function isLogin() {
        var flag = $("#login_user").val();
        if (flag == 'success') {
            return true;
        } else {
            return false;
        }
    }

    $("#userName").blur(function () {

        var userName = $("#userName").val();

        if (userName == null || userName == "") {
            errorMsg = "<span style='color: red'>请先输入账号哟~</span>";

            $("#userNameTips").html(errorMsg);
            $("#userName").css("border", "1px solid red");
            $("#userName").focus();
        } else {
            $("#userNameTips").html("");
            $("#userName").css("border", "2px solid Lime");
        }
    });
    $("#password").blur(function () {
        var password = $("#password").val();

        if (password == null || password == "") {
            errorMsg = "<span style='color: red'>请输入密码哟~</span>";

            $("#passwordTips").html(errorMsg);
            $("#password").css("border", "1px solid red");
        } else {
            $("#passwordTips").html("");
            $("#password").css("border", "2px solid Lime");
        }
    });

</script>
</html>
