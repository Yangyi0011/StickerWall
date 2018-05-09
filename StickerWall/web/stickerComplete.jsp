<%--
  Created by IntelliJ IDEA.
  User: YangYi
  Date: 2017/11/16
  Time: 15:59
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

<div class="container">
    <div class="row">
        <div class="col-sm-9">

            <%--楼主开始--%>
            <div class="row">
                <div class="col-sm-12">
                    <div class="row" id="repliesHead">
                        <div class="text-center col-sm-12"><h3>${sticker.stickerTitle}</h3></div>
                    </div>
                    <hr/>
                    <div class="row" id="repliesBody">
                        <div class="col-sm-12">
                            <div class="text-center col-sm-3 panel panel-primary ">
                                <div id="portrait" class="panel panel-warning ">
                                    <img src="imgs/headPortraitImgs/test.png">${masterInfo.headPortrait}</div>
                                <div id="nickname"><a href="userInfo.jsp">${masterInfo.nickName}</a></div>
                                <div id="grade">等级：<a>${masterInfo.grade}</a></div>
                                <div id="EXP">经验：<a>${masterInfo.EXP}</a></div>
                            </div>
                            <div class="col-sm-9">
                                ${sticker.stickerContent}
                            </div>
                        </div>
                        <div class="text-right col-sm-12" id="repliesFoot">
                            <a href="#"><i class="fa fa-comment"></i>回复 </a>
                            <i> | 1楼 | ${sticker.releaseTime}</i>
                        </div>
                    </div>
                    <hr/>
                </div>
            </div>
            <%--楼主结束--%>

            <%--跟帖开始--%>
            <div id="content">
            </div>
            <%--跟帖结束--%>

            <%--分页导航栏开始--%>
            <div class="text-center">
                <ul class="pagination" id="pagination">

                </ul>
            </div>
            <%--分页导航结束--%>

            <%--输入框开始--%>
            <div class="row" id="commentBox">
                <div class="col-sm-12">
                    <div class="well">
                        <h4>回复一个吧~</h4>
                        <form role="form" action="${pgContext}/replySticker" method="post">
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
            <hr>
            <%--输入框结束--%>

            <footer>
                此处是页脚
            </footer>
        </div>

        <%--最新发布的贴纸推送--%>
        <div class="col-sm-3" id="newStickerContent"></div>

    </div>
</div>

</body>

<script type="text/javascript">
    var thisPage = 1;   //默认当前页面为首页
    var pageSize = 10;   //设置页面显示数据条数
    var tipMsg = "";
    $(document).ready(function () {
        var stickerId = $("#stickerId").val();

        var data = {
            stickerId: stickerId,
            thisPage: thisPage,
            pageSize: pageSize
        };
        $.ajax({
            url: "${pgContext}/getRepliesBySticker",
            type: "post",
            data: data,
            dataType: "json",
            success: function (jsonObj) {
                var jsonData = eval(jsonObj);
                var listData = jsonData.list;

                getRepliesData(listData);
                getPagination(jsonData);
            }
        });
        getNewSticker();    //获取推送
    });

    //获取回帖数据
    function getRepliesData(listData) {
        //        解析json数据
        var replies = eval(listData);
        var appendIndex = 0;

//                循环获取数据
        $.each(replies, function (index, reply) {
            var replyId = reply.id;
            var stickerId = reply.stickerId;
            var userId = reply.userId;
            var repliesContent = reply.repliesContent;
            var repliesTime = new Date(reply.repliesTime.time).toLocaleString('chinese', {hour12: false});
            var state = reply.state;

            var nickname = getMasterNickname(userId);
            var grade = getMasterGrade(userId);
            var EXP = getMasterEXP(userId);
            var floor = index + 2;

//            设置每页显示数据的条数，翻页后覆盖
            if (index % pageSize == 0) {

                appendIndex = index;
                $("#content").html("<div class= 'row' id='" + appendIndex + "'></div> ");
            }

            $('#' + appendIndex).append(
                "<div class= 'row' id= 'repliesBody' >" +
                "<div class= 'col-sm-12' >" +
                "<div class= 'text-center col-sm-3 panel panel-primary ' >" +
                "<div class= 'panel panel-warning ' >" +
                "<img src= 'imgs/headPortraitImgs/test.png' ></div>" +
                "<div><a href= 'userInfo.jsp'>" + nickname + "</a></div>" +
                "<div>等级：<a>" + grade + "</a></div>" +
                "<div>经验：<a>" + EXP + "</a></div>" +
                "</div>" +
                "<div class= 'col-sm-9' >" + repliesContent + "</div>" +
                "</div>" +
                "<div class= 'text-right col-sm-12' >" +
                "<a href= '#'><i class= 'fa fa-comment'></i>回复 </a>" +
                "<i> | " + floor + "楼 | " + repliesTime + "</i>" +
                "</div>" +
                "</div>" +
                "<hr/>"
            );
        });
    }

    //    获取分页信息
    function getPagination(pageData) {
        var pageTotal = pageData.pageTotal; //页面总数
        var start = pageData.start;
        var end = pageData.end;
        var thisPg = pageData.thisPage;

        if (thisPg > 1) {
            $("#pagination").html(
                "<li><a href='${pgContext}/stickerComplete.jsp'>首页</a></li>"
            );
            $("#pagination").append(
                "<li><a href='javascript:void(0);' onclick= 'getPageValue(" + (thisPg - 1) + ")'>上一页</a></li>"
            );
        }
        for (var i = start; i <= end; i++) {

            $("#pagination").append(
                "<li><a href='javascript:void(0);' onclick= 'getPageValue(" + i + ")'>" + i + "</a></li>"
            );
        }

        if (thisPg < pageTotal) {
            $("#pagination").append(
                "<li ><a href='javascript:void(0);' onclick= 'getPageValue(" + (thisPg + 1) + ")'>下一页</a></li>"
            );
            $("#pagination").append(
                "<li><a href='javascript:void(0);' onclick= 'getPageValue(" + pageTotal + ")'>尾页</a></li>"
            );
        }

//        $("#pagination li a").click(function () {
//            $(this).parent().addClass("active");
//        })
    }

    //    某个页面按钮被点击时，获取它所代表页面的页面内容
    function getPageValue(index) {

        thisPage = index;

        var stickerId = $("#stickerId").val();
        var data = {
            stickerId: stickerId,
            thisPage: thisPage,
            pageSize: pageSize
        };

        if (thisPage != 1) {
            $.ajax({
                type: "post",
                url: "${pgContext}/getRepliesBySticker",
                data: data,
                dataType: "json",
                success: function (jsonObj) {
                    var jsonData = eval(jsonObj);
                    var listData = jsonData.list;

                    getRepliesData(listData);
                    getPagination(jsonData);
                }
            });
        } else {
            location.href = "${pgContext}/stickerComplete.jsp";
        }
    }

    //    回帖
    $("#submit").click(function replySticker() {
        var userId = $("#userId").val();
        var stickerId = $("#stickerId").val();
        var replyContent = $("#replyContent").val();

        if (userId == null || userId == "") {
            tipMsg = "<span style='color: red'>亲，您尚未登录，请先登录哟~</span>";
            layer.msg(tipMsg, {
                icon: 2,
                time: 3000 //3秒关闭（如果不配置，默认是3秒）
            });
            return false;
        } else if (stickerId != null && stickerId != "") {
            var data = {
                userId: userId,
                stickerId: stickerId,
                replyContent: replyContent,
            };

            $.ajax({
                url: "${pgContext}/replyServlet",
                type: "post",
                data: data,
                dataType: "json",
                success: function (json) {
                    var jsonData = eval(json);
                    var res = jsonData.res;

                    if (res) {
                        tipMsg = "<span style='color: red'>回帖成功~</span>";
                        layer.msg(tipMsg, {
                            icon: 1,
                            time: 2000 //3秒关闭（如果不配置，默认是3秒）
                        });
                        var URL = window.location.href;
                        location.href = URL;
                    } else {
                        tipMsg = "<span style='color: red'>亲，回帖失败了，请重新操作哟~</span>";

                        layer.msg(tipMsg, {
                            icon: 2,
                            time: 2000 //3秒关闭（如果不配置，默认是3秒）
                        });
                    }
                }
            });
        } else {
            var msg = "贴纸不存在或是已被它的主人删除~";
            location.href = "${pgContext}/errorPage/404.jsp?errorMsg=" + msg;
        }
    });


    //获取贴纸主人昵称
    function getMasterNickname(userId) {
        var nickname = "";
        $.ajax({
            url: "${pgContext}/getNicknameServlet",
            type: "post",
            data: {userId: userId},
            dataType: "json",
            "async": false,    //关闭异步处理
            success: function (json) {
                var jsonData = eval(json);
                var res = jsonData.res;
                if (res) {
                    nickname = jsonData.nickname;
                } else {
                    layer.msg("错误，获取贴纸主人昵称失败！", {
                        icon: 2,
                        time: 3000 //3秒关闭（如果不配置，默认是3秒）
                    });
                }
            }
        });
//        要返回获取的数据需关闭异步处理
//        否则会先return再执行ajax
        return nickname;
    }

    //获取贴纸主人等级
    function getMasterGrade(userId) {
        var grade = "";
        $.ajax({
            url: "${pgContext}/getGradeServlet",
            type: "post",
            data: {userId: userId},
            dataType: "json",
            "async": false,    //关闭异步处理
            success: function (json) {
                var jsonData = eval(json);
                var res = jsonData.res;
                if (res) {
                    grade = jsonData.grade;
                } else {
                    layer.msg("错误，获取贴纸主人等级失败！", {
                        icon: 2,
                        time: 3000 //3秒关闭（如果不配置，默认是3秒）
                    });
                }
            }
        });
//        要返回获取的数据需关闭异步处理
//        否则会先return再执行ajax
        return grade;
    }

    //获取贴纸主人经验
    function getMasterEXP(userId) {
        var EXP = "";
        $.ajax({
            url: "${pgContext}/getEXPServlet",
            type: "post",
            data: {userId: userId},
            dataType: "json",
            "async": false,    //关闭异步处理
            success: function (json) {
                var jsonData = eval(json);
                var res = jsonData.res;
                if (res) {
                    EXP = jsonData.EXP;
                } else {
                    layer.msg("错误，获取贴纸主人经验失败！", {
                        icon: 2,
                        time: 3000 //3秒关闭（如果不配置，默认是3秒）
                    });
                }
            }
        });
//        要返回获取的数据需关闭异步处理
//        否则会先return再执行ajax
        return EXP;
    }

    $("#login").click(function () {
        //获取当前页面URL
        var return_url = window.location.href;
        window.localStorage.setItem("return_url", return_url);
        location.href = "${pgContext}/login.jsp";
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
