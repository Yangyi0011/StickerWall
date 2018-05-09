<%--
  Created by IntelliJ IDEA.
  User: YangYi
  Date: 2017/11/17
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ include file="taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>会员留言</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="style/adminStyle.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script src="js/public.js"></script>
</head>
<body>
<div class="wrap">
    <div class="page-title">
        <span class="modular fl"><i class="user"></i><em>会员留言</em></span>
    </div>
    <table class="list-style Interlaced">
        <tr>
            <th>会员账号</th>
            <th>留言时间</th>
            <th>处理状态</th>
            <th>留言内容</th>
            <th>管理员回复</th>
            <th>操作</th>
        </tr>
        <tr>
            <td class="center">DeathGhost</td>
            <td class="center">2015-04-18 10:39</td>
            <td class="center">未处理</td>
            <td width="300">
                <div style="width:400px;height:50px;overflow:scroll;overflow-x:hidden;">
                    这里是留言内容
                </div>
            </td>
            <td width="300">
                <div style="width:400px;height:50px;overflow:scroll;overflow-x:hidden;">
                    这里是留言内容
                </div>
            </td>
            <td class="center">
                <a href="reply_message.html" class="inline-block" title="回复"><img src="images/icon_title.gif"/></a>
                <a class="inline-block" title="移除"><img src="images/icon_drop.gif"/></a>
            </td>
        </tr>
    </table>
    <!-- BatchOperation -->
    <div style="overflow:hidden;">
        <!-- turn page -->
        <div class="turnPage center fr">
            <a>第一页</a>
            <a>1</a>
            <a>最后一页</a>
        </div>
    </div>
</div>
</body>
</html>