<%--
  Created by IntelliJ IDEA.
  User: YangYi
  Date: 2017/11/17
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ include file="WEB-INF/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>贴纸墙</title>

    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/local.css"/>

    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>

    <style>
        img {
            filter: gray; /* IE6-9 */
            -webkit-filter: grayscale(1); /* Google Chrome, Safari 6+ & Opera 15+ */
            -webkit-box-shadow: 0px 2px 6px 2px rgba(0, 0, 0, 0.75);
            -moz-box-shadow: 0px 2px 6px 2px rgba(0, 0, 0, 0.75);
            box-shadow: 0px 2px 6px 2px rgba(0, 0, 0, 0.75);
            margin-bottom: 20px;
        }

        img:hover {
            filter: none; /* IE6-9 */
            -webkit-filter: grayscale(0); /* Google Chrome, Safari 6+ & Opera 15+ */
        }

        a:focus {
            text-decoration: none
        }
    </style>
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
        <nav class="navbar navbar-default">
            <div class="container-fluid col-sm-9">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-send"></span> 贴纸墙</a>
                </div>
                <div>
                    <ul class="nav navbar-nav">
                        <li><a href="javascript:void(0);" onclick="stickerOrder('releaseTime')">最新发布</a></li>
                        <li><a href="javascript:void(0);" onclick="stickerOrder('clickCount')">最多点击</a></li>
                        <li><a href="javascript:void(0);" onclick="stickerOrder('praiseCount')">最多点赞</a></li>
                        <li><a href="javascript:void(0);" onclick="stickerOrder('replyCount')">最多回复</a></li>
                    </ul>
                </div>
            </div>

            <form class="navbar-form navbar-left col-sm-3" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" id="searchContent" placeholder="搜索贴纸">
                </div>
                <button type="submit" class="btn btn-default" id="searchBtn">搜索</button>
            </form>
        </nav>
    </div>
    <div class="row">
        <%--首页内容--%>
        <div id="content">
        </div>
        <%--首页内容结束--%>
    </div>

    <div class="row">
        <%--分页导航栏开始--%>
        <div class="text-center">
            <ul class="pagination" id="pagination">

            </ul>
        </div>
        <%--分页导航结束--%>
    </div>

    <div class="row">
        <%--发帖框开始--%>
        <div class="well">
            <h4>我也写一个:</h4>
            <div>
                <%--隐藏域--%>
                <input type="hidden" id="userId" name="userId" value="${userInfo.userId}">
                <%--隐藏域结束--%>
                <div class="form-group">
                    <input type="text" class="form-control" name="stickerTitle" id="stickerTitle" style="font-size: large;color: #0033FF" placeholder="标题"><br>
                    <span id="errorTips"></span>
                    <textarea class="form-control" rows="3" name="stickerContent" id="stickerContent" style="font-size: large;color: #0033FF"
                              placeholder="在这里写下你的美言吧~"></textarea>
                </div>
                <button type="button" id="submit" class="btn btn-primary">发表</button>
            </div>
        </div>
        <%--发帖框结束--%>
    </div>

    <hr>
    <footer>
        此处是页脚
    </footer>

</div>

</body>
<script type="text/javascript">
    var thisPage = 1;   //默认当前页面为首页
    var pageSize = 6;   //设置页面显示数据条数
    var searchContent = ""; //搜索内容
    var order = "releaseTime"; //默认按最新发布排序

    //    首页数据加载
    $(document).ready(function () {
        getPageData();  //获取页面数据
    });
    function getPageData() {
        var data = {
            thisPage : thisPage,
            pageSize : pageSize,
            searchContent : searchContent,
            order: order,
        }
        $.ajax({
            type: "post",
            url: "${pgContext}/getIndexDataServlet",
            data: data,
            dataType: "json",
            success: function (jsonObj) {
                var jsonData = eval(jsonObj);
                var stickerList = jsonData.list;

                getStickerData(stickerList);    //贴纸数据处理
                getPagination(jsonData);    //分页处理
            }
        });
    }

    //    获取分页信息
    function getPagination(pageData) {
        var start = pageData.start; //提取从第几页开始显示页码
        var pageTotal = pageData.pageTotal;     //提取页面总数
        var end = pageData.end;     //提取页码显示到第几页
        var thisPg = pageData.thisPage;   //提取当前显示的页面

        if (thisPg > 1) {
            $("#pagination").html(
                "<li><a href='javascript:void(0);' onclick= 'getPageValue(" + 1 + ")'>首页</a></li>"
            );
            $("#pagination").append(
                "<li><a href='javascript:void(0);' onclick= 'getPageValue(" + (thisPg - 1) + ")'>上一页</a></li>"
            );
        }else {
            $("#pagination").html("");
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
        if (thisPage != 1) {
            refreshPageData();
        } else {
            getPageData();
        }
    }

    //    获取首页数据
    function getStickerData(listData) {
        //解析json数据
        var stickers = eval(listData);
        var appendIndex = 0;
        var rowSize = 6;    <%--设置每行显示的贴纸数量--%>

        $.each(stickers, function (index, sticker) {
            //循环获取数据
            var stickerId = sticker.id;
            var userId = sticker.userId;
            var stickerTitle = sticker.stickerTitle;
            var stickerContent = sticker.stickerContent;
            var stickerContentPreview = '';     <%--预览显示的内容--%>
            if(stickerContent.size > 15 ){
                stickerContentPreview = stickerContent.substring(0,15) + "……";     <%--预览显示的长度--%>
            }else {
                stickerContentPreview = stickerContent;
            }

            //解析json时间对象
            var releaseTime = new Date(sticker.releaseTime.time).toLocaleString('chinese', {hour12: false});
            var clickCount = sticker.clickCount;
            var praiseCount = sticker.praiseCount;
            var replyCount = sticker.replyCount;
            var lastReplyTime = new Date(sticker.releaseTime.time).toLocaleString('chinese', {hour12: false});
            var state = sticker.state;

            var nickname = getMasterNickname(userId);

//            设置每页显示数据的条数，翻页后覆盖
            if (index % rowSize == 0) {

                appendIndex = index;
                $("#content").html("<div class= 'row' id='" + appendIndex + "'></div> ");
            }
            $('#' + appendIndex).append(
                "<div class= 'col-md-4'> " +
                "<div class='well'>" +
                "<a href= 'javascript:void(0);' onclick= 'reply(" + stickerId + ")'>" +
                "<img class='thumbnail img-responsive' alt='Bootstrap template' src='/imgs/contentImgs/1.jpeg' /></a>" +
                "<h3>" + stickerTitle + "</h3>" +
                "发布：<i>" + releaseTime + "</i>" +
                "<hr>" +
                "<a href= 'javascript:void(0);' onclick= 'reply(" + stickerId + ")'>" + stickerContentPreview + "</a>" +
                "<hr>" +
                "<div class='text-right'>" +
                "<i>" + clickCount + " 点击 | </i>" +
                "<a href= 'javascript:void(0);' onclick= 'stickerDetails(" + stickerId + ")'>" +
                "<span id='" + stickerId + "'><i class='fa fa-thumbs-o-up'>" + praiseCount + "</i></span></a> 赞 | " +
                "<a href= 'javascript:void(0);' onclick= 'reply(" + stickerId + ")'>" +
                "<i class='fa fa-comment'>" + replyCount + "</i></a> 回复" +
                "</div>" +
                "<div class='text-left'><a href= 'userInfo.jsp'>" + nickname + "</a></div>" +
                "<div class='text-right'>最后回复：<i>" + lastReplyTime + "</i></div>" +
                "</div>" +
                "</div>"
            )
        });
    }
    //刷新页面数据
    function refreshPageData() {
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
                getPagination(jsonData);
            }
        });
    }

    //发帖
    $("#submit").click(
        function releaseSticker() {
            var userId = $("#userId").val();
            var stickerTitle = $("#stickerTitle").val();
            var stickerContent = $("#stickerContent").val();

            var errorMsg = "";      //发帖错误提示
            if (userId == null || userId == "") {
                errorMsg = "<span style='color: red'>亲，您尚未登录，请先登录哟~</span>";
                layer.msg(errorMsg, {
                    icon: 2,
                    time: 3000 //3秒关闭（如果不配置，默认是3秒）
                });
                return false;
            }
            if (stickerTitle == "" || stickerTitle == null ||
                stickerContent == "" || stickerContent == null) {

                errorMsg = "<span style='color: red'>亲，多少也请写一点儿吧~</span>";
                layer.msg(errorMsg, {
                    icon: 2,
                    time: 3000 //3秒关闭（如果不配置，默认是3秒）
                });
                $("#stickerTitle").css("border","1px solid red");
                $("#errorTips").html(errorMsg);
                $("#stickerTitle").focus();
                return false;
            } else {
                $("#errorTips").html("");

                var data = {
                    userId: userId,
                    stickerTitle: stickerTitle,
                    stickerContent: stickerContent
                }
                $.ajax({
                    url: "${pgContext}/releaseSticker",
                    type: "post",
                    data: data,
                    dataType: "json",
                    success: function (json) {
                        var jsonDate = eval(json);
                        var res = jsonDate.res;
                        if (res) {
                            location.href = "${pgContext}/index.jsp";
                        } else {
                            errorMsg = "<span style='color: red'>亲，发帖失败了~,重新再试一次吧</span>";
                            layer.msg(errorMsg, {
                                icon: 2,
                                time: 3000 //3秒关闭（如果不配置，默认是3秒）
                            });
                        }
                    }
                })
            }
        }
    );

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
                    layer.msg("错误111，获取贴纸主人昵称失败！", {
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

    //    点赞
    function stickerDetails(stickerId) {
        var stickerId = stickerId;      //被点赞的贴纸
        var praiseUserId = $("#userId").val();     //获取当前登录人的Id,即执行点赞的人

        if (praiseUserId == "" || praiseUserId == null) {
            var tip = "<span style='color:red'> 要登录才能给喜欢的贴纸点赞哟，您现在要登录吗？</span>";
            layer.confirm(tip, {title: "登录确认"},
                function (index) {
                    layer.close(index);
                    location.href = "${pgContext}/login.jsp";
                }
            );
        } else {
            var submitDate = {
                stickerId: stickerId,
                userId: praiseUserId
            }
            $.ajax({
                type: "post",
                url: "${pgContext}/praiseServlet",
                data: submitDate,
                dataType: "json",
                success: function (json) {
                    //可以只替换一行？
                    var jsonDate = eval(json);
                    var praiseCount = jsonDate.praiseCount;
                    var res = jsonDate.res;

                    if (res) {
                        $('#' + stickerId).html(
                            "<i class='fa fa-thumbs-up'>" + praiseCount + "</i>"
                        );
                    } else {
                        $('#' + stickerId).html(
                            "<i class='fa fa-thumbs-o-up'>" + praiseCount + "</i>"
                        );
                    }
                }
            })
        }
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
    //登录
    $("#login").click(function () {
        //获取当前页面URL
        var return_url = window.location.href;
        window.localStorage.setItem("return_url", return_url);
        location.href = "${pgContext}/login.jsp";
    });

    //排序
    function stickerOrder(thisOrder) {
        order = thisOrder;
        refreshPageData();
    }

    //查询
    $("#searchBtn").click(function () {
        searchContent = $("#searchContent").val();

        refreshPageData();
    });

</script>

</html>

