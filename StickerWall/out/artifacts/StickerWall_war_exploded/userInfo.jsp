<%--
  Created by IntelliJ IDEA.
  User: YangYi
  Date: 2017/11/24
  Time: 10:05
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
    <title>我的信息</title>

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
                                <a href="/logoutServlet"><i class="fa fa-power-off"></i> 退出登录</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </c:if>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-8">
            <div class="row">
                <hr>
                <div class="row">
                    <span class="col-lg-2" style="font-size: 20px; color: #5bc0de">账号：</span>
                    <input id="userName" type="hidden" value="${userInfo.userName}">
                    <div class="col-sm-8" style="height: 30px; color: red; font-size: 20px">${userInfo.userName}</div>
                    <div class="col-sm-2"></div>
                </div>

                <hr>
                <div class="row">
                    <span class="col-lg-2" style="font-size: 20px; color: #5bc0de">昵称：</span>
                    <input id="nickName" class="col-sm-8" style="height: 30px; color: red;font-size: large;" type="text" value="${userInfo.nickName}">
                    <div class="col-sm-2"></div>
                </div>

                <hr>
                <div class="row">
                    <span class="col-lg-2" style="font-size: 20px; color: #5bc0de">性别：</span>
                    <select id="sex" class="col-sm-8" style="height: 30px; color: red;font-size: large;">
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                    <div class="col-sm-2"></div>
                </div>

                <hr>
                <div class="row">
                    <span class="col-lg-2 " style="font-size: 20px; color: #5bc0de">生日：</span>

                    <c:if test="${empty userInfo.birthday}">
                    <input id="birthday" class="col-sm-8" style="height: 30px; color: red;font-size: large; vertical-align:middle; line-height:30px;"
                           type="date">
                    </c:if>
                    <c:if test="${not empty userInfo.birthday}">
                        <input id="birthday" class="col-sm-8" style="height: 30px; color: red;font-size: large; vertical-align:middle; line-height:30px;"
                               type="date" value="${userInfo.birthday}">
                    </c:if>
                    <div class="col-sm-2"></div>
                </div>

                <hr>
                <div class="row">
                    <span class="col-lg-2" style="font-size: 20px; color: #5bc0de">地址：</span>
                    <input id="address" class="col-sm-8"style="height: 30px; color: red; font-size: large;" type="text" value="${userInfo.address}">
                    <div class="col-sm-2"></div>
                </div>

                <hr>
                <div class="row ">
                    <div class="col-sm-4"></div>
                    <input type="button" class="btn-sm btn-primary col-sm-4" style="font-size: 15px;" id="submit" value="保存">
                    <div class="col-sm-4"></div>
                </div>
            </div>
        </div>

        <%--最新发布的贴纸推送--%>
        <div class="col-sm-3" id="newStickerContent"></div>
    </div>
</div>

<%-- 隐藏域，用以获取传过来的用户Id--%>
<span>
    <input type="hidden" value="${userId}">
</span>

</body>

<script type="text/javascript">
    $(document).ready(function () {
        getNewSticker();
    });
    $("#submit").click(function () {
        var userName = $("#userName").val();
        var nickName = $("#nickName").val();
        var sex = $("#sex").val();
        var birthday = $("#birthday").val();
        var address = $("#address").val();

        var data = {
            userName: userName,
            nickName: nickName,
            sex: sex,
            birthday : birthday,
            address : address
        };
        $.ajax({
            url: "${pgContext}/userInfoUpdate",
            type: "post",
            data: data,
            dataType: "json",
            success: function (jsonObj) {
                var jsonData = eval(jsonObj);
                var res = jsonData.res;

                if (res == 1) {
                    errorMsg = "<span style='color: red'>信息更新成功~</span>";
                    layer.msg(errorMsg, {
                        icon: 1,
                        time: 2000 //3秒关闭（如果不配置，默认是3秒）
                    });
                    location.reload();  //刷新页面
                } else {
                    errorMsg = "<span style='color: red'>信息更新失败了，请登录后重新操作~</span>";
                    layer.msg(errorMsg, {
                        icon: 2,
                        time: 3000 //3秒关闭（如果不配置，默认是3秒）
                    });
                }
            }
        });
    });

    <%--获取推送的最新贴纸--%>
    function getNewSticker() {
        var thisPage = 1;
        var pageSize = 3;   <%--只获取3个最新的--%>
        var order = "releaseTime"; <%--按最新排序--%>
        var searchContent = "";

        var data = {
            thisPage: thisPage,
            pageSize: pageSize,
            searchContent: searchContent,
            order: order,
        };
        $.ajax({
            type: "post",
            url: "${pgContext}/getIndexDataServlet",
            data: data,
            async:false,
            dataType: "json",
            success: function (jsonObj) {
                var jsonData = eval(jsonObj);
                var listData = jsonData.list;
                getStickerData(listData);
            }
        });
    }
    //获取贴纸数据
    function getStickerData(listData) {
        //解析json数据
        var stickers = eval(listData);
        var appendIndex = 0;
        var rowSize = 3;    <%--设置推送贴纸的数量，需小于等于上面获取的数量总数--%>

        $.each(stickers, function (index, sticker) {
            //循环获取数据
            var stickerId = sticker.id;
            var stickerTitle = sticker.stickerTitle;
            var stickerContent = sticker.stickerContent;
            var stickerContentPreview = '';
            if(stickerContent.size > 15 ){
                stickerContentPreview = stickerContent.substring(0,15) + "……";     <%--预览显示--%>
            }else {
                stickerContentPreview = stickerContent;
            }

//            设置每页显示数据的条数，翻页后覆盖
            if (index % rowSize == 0) {

                appendIndex = index;
                $("#newStickerContent").html(
                    "<div class='row' >"+
                    "<div class= 'col-xs-12' id='" + appendIndex + "'> <h2>最新发布</h2> </div>"+
                    "</div>"
                );
            }
            $('#' + appendIndex).append(
                "<div class='panel panel-default'>" +
                "   <div class='panel-heading'>"+stickerTitle+"</div>" +
                "    <div class='panel-body'>" +stickerContentPreview+ "</div>" +
                "    <div class='text-center'>" +
                "       <a href= 'javascript:void(0);' onclick= 'reply("+stickerId+")'>" +
                "           <i class='fa fa-plus'></i>查看详情</a>" +
                "    </div>" +
                "</div>" +
                "<hr/>"
            )
        });
    }
    <%-- 查看贴纸详情（进行回复）--%>
    function reply(stickerId) {
        var stickerId = stickerId;      //被查看的贴纸
        $.ajax({
            type: "post",
            url: "${pgContext}/stickerDetailsServlet",
            traditional: true,
            data: {stickerId: stickerId},
            dataType: "json",
            success: function (data) {

                if (data) {
                    location.href = "${pgContext}/stickerComplete.jsp";
                } else {
                    location.href = "${pgContext}/errorPage/404.jsp";
                }
            }
        })
    }
</script>
</html>

