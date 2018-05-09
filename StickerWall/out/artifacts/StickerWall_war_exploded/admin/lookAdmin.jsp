<%--
  Created by IntelliJ IDEA.
  User: YangYi
  Date: 2018/1/1
  Time: 21:05
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
    <div class="col-sm-3"></div>
    <div class="text-center col-sm-6 panel panel-primary">
        <div class="row">
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">管理员账号：</div>
                <input type="text" id="adminName" value="${admin.name}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">管理员昵称：</div>
                <input type="text" id="nickName" value="${admin.nickname}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">管理员邮箱：</div>
                <input type="text" id="email" value="${admin.email}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">加入时间：</div>
                <input type="text" id="joinTime" value="${admin.joinTime}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">最后登录时间：</div>
                <input type="text" id="lastLoginTime" value="${admin.lastLoginTime}"
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
