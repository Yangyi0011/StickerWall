<%--
  Created by IntelliJ IDEA.
  User: YangYi
  Date: 2017/11/17
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>左侧导航</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="style/adminStyle.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/public.js"></script>
</head>
<body style="background:#313131">
<div class="menu-list">
    <a href="main.jsp" target="mainCont" class="block menu-list-title center"
       style="border:none;margin-bottom:8px;color:#fff;">起始页</a>
    <ul>
        <c:if test="${login_admin != null && login_admin == 'success'}">

            <li class="menu-list-title">
                <span>贴纸管理</span>
                <i>◢</i>
            </li>
            <li>
                <ul class="menu-children">
                    <li><a href="sticker_list.jsp" title="贴纸列表" target="mainCont">贴纸列表</a></li>
                        <%--<li><a href="sticker_examine.jsp" title="商品分类" target="mainCont">待审核</a></li>--%>
                        <%--<li><a href="sticker_disable.jsp" title="被封的贴纸" target="mainCont">被封贴纸</a></li>--%>
                </ul>
            </li>

            <li class="menu-list-title">
                <span>用户管理</span>
                <i>◢</i>
            </li>
            <li>
                <ul class="menu-children">
                    <li><a href="user_list.jsp" title="用户列表" target="mainCont">用户列表</a></li>
                        <%--<li><a href="user_disable.jsp" title="被封用户" target="mainCont">被封用户</a></li>--%>
                        <%--<li><a href="user_message.jsp" title="用户留言" target="mainCont">用户留言</a></li>--%>
                </ul>
            </li>
            <c:if test="${admin.type eq 1}">
                <li class="menu-list-title">
                    <span>管理员</span>
                    <i>◢</i>
                </li>
                <li>
                    <ul class="menu-children">
                        <li><a href="admin_list.jsp" title="管理员列表" target="mainCont">管理员列表</a></li>
                        <li><a href="admin_add.jsp" title="添加管理员" target="mainCont">添加管理员</a></li>
                    </ul>
                </li>
            </c:if>
        </c:if>
        <%--<li class="menu-list-title">--%>
        <%--<span>系统设置</span>--%>
        <%--<i>◢</i>--%>
        <%--</li>--%>
        <%--<li>--%>
        <%--<ul class="menu-children">--%>
        <%--<li><a href="basic_settings.jsp" title="站点基本设置" target="mainCont">站点基本设置</a></li>--%>
        <%--</ul>--%>
        <%--</li>--%>
    </ul>
</div>
</body>
</html>
