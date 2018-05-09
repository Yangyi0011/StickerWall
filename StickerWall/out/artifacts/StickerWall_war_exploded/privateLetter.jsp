<%--
  Created by IntelliJ IDEA.
  User: YangYi
  Date: 2017/11/24
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ include file="WEB-INF/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>贴纸墙</title>

    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/local.css"/>

    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>

</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp"><span class="glyphicon glyphicon-send"></span> 贴纸墙</a>
        </div>
        <div>
            <c:if test="${ empty login_user || login_user == 'error'}">
                <ul class="nav navbar-nav navbar-right">
                    <li class="btn-default"><a href="javascript:void(0);" id="login"> <span
                            class="glyphicon glyphicon-user"></span> 登录 </a>
                    </li>
                    <li class="btn-default"><a href="register.jsp"> <span class="glyphicon glyphicon-log-in"></span> 注册
                    </a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            更多
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="aboutUs.jsp">关于我们</a></li>
                            <li class="divider"></li>
                            <li><a href="contactUs.jsp">联系我们</a></li>
                        </ul>
                    </li>
                </ul>
            </c:if>
            <c:if test="${login_user != null && login_user == 'success'}">
                <ul class="nav navbar-nav navbar-right navbar-user">
                    <li class="dropdown messages-dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> 动态
                            <span
                                    class="badge">2</span> <b class="caret"></b></a>
                        <ul class="dropdown-menu">

                            <li class="dropdown-header">2 条新信息</li>
                            <li class="message-preview">

                                <a href="replyReceived.jsp">
                                    <span class="avatar"><i class="fa fa-bell"></i></span>
                                    <span class="message">收到的回复<span class="badge">3</span></span>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li class="message-preview">
                                <a href="privateLetter.jsp">
                                    <span class="avatar"><i class="fa fa-send"></i></span>
                                    <span class="message">收到的私信<span class="badge">4</span></span>
                                </a>
                            </li>

                            <li class="divider"></li>
                            <li>
                                <a href="messageCenter.jsp">进入消息中心 <span class="badge">2</span></a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown user-dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-user">${userInfo.nickName}</i>
                            <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="userInfo.jsp"><i class="fa fa-user"></i> 我的信息</a>
                            </li>
                            <li>
                                <a href="userSpace.jsp"><i class="fa fa-gear"></i> 我的空间</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="${pgContext}/logoutServlet"><i class="fa fa-power-off"></i> 退出登录</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </c:if>
        </div>
    </div>
</nav>
<%--私信开始--%>
<div class="row col-sm-12">
    <div class="col-sm-2"></div>
    <div id="content" class="col-sm-8 text-center">
        <h1 class="text-center" style="color: #66ccff">私信内容:抱歉，此功能尚未实现~</h1>
        <hr>
    </div>
</div>
<%--私信结束--%>
<%--输入框开始--%>
<div class="row " id="commentBox">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
        <div class="well">
            <h4>回复一个吧~</h4>
            <form role="form" action="/replySticker" method="post">
                <%--隐藏域--%>
                <input type="hidden" id="userId" name="userId" value="${userInfo.userId}">
                <input type="hidden" id="stickerId" name="stickerId" value="${sticker.id}">
                <%--隐藏域结束--%>
                <div class="form-group">
                        <textarea class="form-control" rows="3" name="replyContent" id="replyContent"
                                  placeholder="在这里写下你的美言吧~"></textarea>
                </div>
                <button type="button" id="submit" class="btn btn-primary">发表</button>
            </form>
        </div>
    </div>
</div>
<%--输入框结束--%>
</body>
</html>
