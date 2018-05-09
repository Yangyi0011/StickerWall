<%--
  Created by IntelliJ IDEA.
  User: YangYi
  Date: 2018/1/1
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ include file="taglib.jsp" %>
<html>
<head>
    <title>查看管理员信息</title>

    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>

    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
</head>
<body>
<div class="row">
    <hr>
    <div class="col-sm-3"></div>
    <div class="text-center col-sm-6 panel panel-primary">
        <div class="row">
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">管理员账号：</div>
                <input type="text" id="adminName" class="col-sm-7">
            </div>
            <hr>

            <div class="row">
                <div class="text-center col-sm-4 ">管理员密码：</div>
                <input type="password" id="password" class="col-sm-7">
            </div>
            <hr>

            <div class="row">
                <div class="text-center col-sm-4 ">重复密码：</div>
                <input type="password" id="rePassword" class="col-sm-7">
            </div>
            <hr>

            <div class="row">
                <div class="text-center col-sm-4 ">管理员昵称：</div>
                <input type="text" id="nickname"  class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">管理员邮箱：</div>
                <input type="text" id="email"  class="col-sm-7">
            </div>
            <hr>

            <div class="row">
                <div class="col-sm-3"></div>
                <div class="col-sm-3">
                    <input type="button" id="submit" value="提交" class="btn-primary form-control ">
                </div>
                <div class="col-sm-3">
                    <input type="reset" id="reset" value="重置" class="btn-default form-control ">
                </div>
                <div class="col-sm-3"></div>
            </div>
            <hr>

        </div>

    </div>
    <div class="col-sm-3"></div>
</div>
</body>

<script type="text/javascript">

    $("#submit").click(function () {

        var name = $("#adminName").val();
        var password = $("#password").val();
        var rePassword = $("#rePassword").val();
        var nickname = $("#nickname").val();
        var email = $("#email").val();

        if( checkName(name) && checkPassword(password,rePassword) && checkEmail(email)){
            addAdmin(name,password,nickname,email);
        }
    });

    function checkName(name) {
        if(name == null || name == ""){
            layer.msg("请填写管理员账号！", {
                icon: 2,
                time: 2000 //3秒关闭（如果不配置，默认是3秒）
            });
            $("#adminName").focus();
            $("#adminName").css("border","1px solid red");
            return false;
        }
        return true;
    }

    function checkPassword(password,rePassword) {
        if(password == null || password == ""){
            layer.msg("密码不能为空！", {
                icon: 2,
                time: 2000 //3秒关闭（如果不配置，默认是3秒）
            });
            $("#password").css("border","1px solid red");

            return false;
        }
        if(rePassword == null || rePassword == ""){
            layer.msg("请再次输入密码！", {
                icon: 2,
                time: 2000 //3秒关闭（如果不配置，默认是3秒）
            });
            $("#rePassword").css("border","1px solid red");

            return false;
        }
        if(rePassword != password){
            layer.msg("两次密码输入不一致！", {
                icon: 2,
                time: 2000 //3秒关闭（如果不配置，默认是3秒）
            });
            $("#password").css("border","1px solid red");
            $("#rePassword").css("border","1px solid red");

            return false;
        }
        return true;
    }
    function checkEmail(strEmail) {

        if(strEmail == null || strEmail == ""){
            layer.msg("请填写邮箱！", {
                icon: 2,
                time: 2000 //3秒关闭（如果不配置，默认是3秒）
            });
            $("#email").css("border","1px solid red");

            return false;
        }else {
            var email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/i;
            if(strEmail.length < 6 || strEmail.length > 18) {
                layer.msg("请填写6~18的邮箱！", {
                    icon: 2,
                    time: 2000 //3秒关闭（如果不配置，默认是3秒）
                });
                $("#email").css("border","1px solid red");
                return false;
            } else {
                if(!email.test(strEmail)) {
                    layer.msg("邮箱不合法，请填写合法的邮箱！", {
                        icon: 2,
                        time: 2000 //3秒关闭（如果不配置，默认是3秒）
                    });
                    $("#email").css("border","1px solid red");
                    return false;
                }else{
                    return true;
                }
            }
        }
    }
    
    function addAdmin(name,password,nickname,email) {
        var data = {
            name: name,
            password: password,
            nickname : nickname,
            email : email
        }
        $.ajax({
            url: "${pgContext}/adminAdd",
            type: "post",
            data: data,
            dataType: "json",
            success: function (jsonObj) {
                var jsonData = eval(jsonObj);
                var res = jsonData.res;

                if (res == 1) {
                    layer.msg('添加成功！', {
                        icon: 1,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                } else if (res == 0) {
                    layer.msg('管理员信息已更新！', {
                        icon: 1,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                }else {
                    layer.msg("添加失败，账号或密码或邮箱为空！", {
                        icon: 2,
                        time: 3000 //3秒关闭（如果不配置，默认是3秒）
                    });
                    return false;
                }
            }
        });
    }
    
    $("#reset").click(function () {
        $("#adminName").val("");
        $("#password").val("");
        $("#rePassword").val("");
        $("#nickname").val("");
        $("#email").val("");
    });
</script>
</html>
