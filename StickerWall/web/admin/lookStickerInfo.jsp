<%--
  Created by IntelliJ IDEA.
  User: YangYi
  Date: 2018/1/1
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ include file="taglib.jsp" %>
<html>
<head>
    <title>查看贴纸信息</title>

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
                <div class="text-center col-sm-4 ">发帖人：</div>
                <input type="hidden" id="userId" value="${sticker.userId}">
                <input type="text" id="nickname" disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">贴纸标题：</div>
                <input type="text" id="stickerTitle" value="${sticker.stickerTitle}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">贴纸内容：</div>
                <input type="text" id="stickerContent" value="${sticker.stickerContent}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">发帖时间：</div>
                <input type="text" id="releaseTime" value="${sticker.releaseTime}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">点击量：</div>
                <input type="text" id="clickCount" value="${sticker.clickCount}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">点赞量：</div>
                <input type="text" id="praiseCount" value="${sticker.praiseCount}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">回复量：</div>
                <input type="text" id="replyCount" value="${sticker.replyCount}"
                       disabled="disabled" class="col-sm-7">
            </div>
            <hr>
            <div class="row">
                <div class="text-center col-sm-4 ">最后回复时间：</div>
                <input type="text" id="lastReplyTime" value="${sticker.lastReplyTime}"
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
    //页面加载时便修显示贴纸主人昵称
    $(document).ready(function () {
       setMasterNickname();
    });

    $("#ok").click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });

    function setMasterNickname() {
        var userId = $("#userId").val();
        var nickname = getMasterNickname(userId);

        $("#nickname").val(nickname);
    }

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
</script>
</html>
