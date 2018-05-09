<%--
  Created by IntelliJ IDEA.
  User: YangYi
  Date: 2017/12/31
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ include file="taglib.jsp" %>
<html>
<head>
    <title>查看用户信息</title>

    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>

    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
</head>
<body>
<div class="row">
    <div class="col-sm-3"></div>
    <div class="text-center col-sm-6 panel panel-primary">
        <div class="row">
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">账号：</div>
                <input type="text" id="userName" value="${userInfo.userName}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">昵称：</div>
                <input type="text" id="nickName" value="${userInfo.nickName}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">性别：</div>
                <input type="text" id="sex" value="${userInfo.sex}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">生日：</div>
                <input type="text" id="birthday" value="${userInfo.birthday}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">地址：</div>
                <input type="text" id="address" value="${userInfo.address}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">个性签名：</div>
                <input type="text" id="motto" value="${userInfo.motto}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">经验：</div>
                <input type="text" id="EXP" value="${userInfo.EXP}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">等级：</div>
                <input type="text" id="grade" value="${userInfo.grade}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>

            <div class="row">
                <div class="col-sm-3"></div>
                <div class="col-sm-6">
                    <input type="button" id="ok" value="确定" class="btn-primary form-control ">
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

    $("#ok").click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });
</script>
</html>
