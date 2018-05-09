<%--
  Created by IntelliJ IDEA.
  User: YangYi
  Date: 2017/11/17
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ include file="WEB-INF/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>修改密码</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/local.css"/>

    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>

</head>
<body>
<div class="wrap">
    <div class="page-title">
        <span class="modular fl"><i class="user"></i><em>修改密码</em></span>
    </div>
    <table class="noborder">
        <tr>
            <td width="15%" style="text-align:right;">账号：</td>
            <td><input type="text" class="textBox length-middle"/></td>
        </tr>
        <tr>
            <td style="text-align:right;">邮箱：</td>
            <td><input type="text" class="textBox length-middle"/></td>
        </tr>
        <tr>
            <td style="text-align:right;">旧密码：</td>
            <td><input type="password" class="textBox length-middle"/></td>
        </tr>
        <tr>
            <td style="text-align:right;">新密码：</td>
            <td><input type="password" class="textBox length-middle"/></td>
        </tr>
        <tr>
            <td style="text-align:right;">再次确认密码：</td>
            <td><input type="password" class="textBox length-middle"/></td>
        </tr>
        <tr>
            <td style="text-align:right;"></td>
            <td><input type="submit" class="tdBtn" value="保存"/></td>
        </tr>
    </table>
</div>
</body>
</html>
