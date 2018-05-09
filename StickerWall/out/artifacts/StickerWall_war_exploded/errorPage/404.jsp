<%--
  Created by IntelliJ IDEA.
  User: YangYi
  Date: 2017/11/19
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>404页面</title>
</head>

<style type="text/css">
    body{
        color: #66ccff;
        font-size: 14px;
        margin: 20px auto;
    }
    #massage{
        text-align: center;
        color: red;
    }
</style>

<body>
<div id="massage">
    <h1>错误404！哇，怎么也找不到了！</h1>
    <h2>可能的原因：${errorMsg}</h2>
</div>
</body>
</html>
