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
<%@ include file="taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理员登录</title>

    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>

    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>

</head>

<body>

<div class="row">

    <div class="col-lg-12 text-center v-center">

        <h1>Login</h1>
        <p class="lead">请输入管理员账号和密码来登录</p>
        <br>
        <br>
        <div class="col-lg-12">
            <%--隐藏域--%>
            <input type="hidden" name="login_admin" id="login_admin" value="${login_admin}">
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

</body>

<script>
    var errorMsg = "";
    $(document).ready(function () {
    });
    $("#submit").click(function () {
        var userName = $("#userName").val();
        var password = $("#password").val();

        if (isLogin()) {
            errorMsg = "<span style='color: red'>亲，您已在登录了，请不要重复登录~</span>";
            layer.msg(errorMsg, {
                icon: 2,
                time: 3000 //3秒关闭（如果不配置，默认是3秒）
            });
            return false;
        } else {
            if (userName == null || userName == "") {

                $("#userName").css("border", "1px solid red");
                $("#userName").focus();
                return false;
            } else if (password == null || password == "") {
                $("#password").css("border", "1px solid red");
                $("#password").focus();
                return false;
            } else {
                var data = {
                    userName: userName,
                    password: password,
                }
                $.ajax({
                    type: "post",
                    url: "${pgContext}/adminLogin",
                    data: data,
                    dataType: "json",
                    success: function (jsonData) {
                        var data = eval(jsonData);
                        var res = data.res;

                        if (res == 1) {
                            errorMsg = "<span style='color: red'>亲爱的管理员，欢迎您的到来~</span>";

                            layer.msg(errorMsg, {
                                icon: 1,
                                time: 2000 //3秒关闭（如果不配置，默认是3秒）
                            });

                            location.href = "${pgContext}/admin/index.jsp";
                        } else {
                            errorMsg = "<span style='color: red'>账号或密码错误，请重新登录~</span>";

                            layer.msg(errorMsg, {
                                icon: 2,
                                time: 3000 //3秒关闭（如果不配置，默认是3秒）
                            });
                            $("#passwordTips").html(errorMsg);
                        }
                    }
                })
            }
        }
    });

    //    验证用户是否已经登录
    function isLogin() {
        var flag = $("#login_admin").val();
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

            $("#userName").focus();
            $("#userNameTips").html(errorMsg);
        } else {
            $("#userNameTips").html("");
        }
    });
    $("#password").blur(function () {
        var password = $("#password").val();

        if (password == null || password == "") {
            errorMsg = "<span style='color: red'>请输入密码哟~</span>";

            $("#password").focus();
            $("#passwordTips").html(errorMsg);
        } else {
            $("#passwordTips").html("");
        }
    });

</script>
</html>