<%--
  Created by IntelliJ IDEA.
  User: YangYi
  Date: 2017/12/26
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>贴纸列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="style/adminStyle.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>

    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <style>
        html,
        body {
            width: 100%;
            height: 100%;
        }
    </style>
    <script src="js/jquery.js"></script>
    <script src="js/public.js"></script>

</head>
<body>
<div class="wrap">
    <div class="page-title">
        <span class="modular fl"><i></i><em>贴纸列表</em></span>
    </div>
    <div class="operate row">
        <div class="col-sm-3">
            <strong>选择排序：</strong>

            <select class="inline-select" id="order">
                <option value="releaseTime">按发帖时间排</option>
                <option value="clickCount">按点击量排</option>
                <option value="praiseCount">按点赞量排</option>
                <option value="replyCount">按回复量排</option>
                <option value="lastReplyTime">按最后回复时间排</option>
            </select>
        </div>
        <div class="col-sm-4">
        </div>
        <div class="col-sm-5">
            <input id="searchContent" type="text" class="textBox length-long" placeholder="请输入贴纸标题/内容进行查询……"/>
            <input type="button" value="查询" class="tdBtn" id="searchBtn"/>
        </div>
    </div>
    <table class="list-style Interlaced" id="tableDate">
        <tr>
            <th class="center" style="width: 40px">序号</th>
            <th class="center" style="width: 50px">id</th>
            <th class="center" style="width: 120px">贴纸标题</th>
            <th class="center" style="width: 200px">贴纸内容</th>
            <th class="center" style="width: 120px">发帖人</th>
            <th class="center" style="width: 120px">发帖时间</th>
            <th class="center" style="width: 50px">点击数</th>
            <th class="center" style="width: 50px">点赞数</th>
            <th class="center" style="width: 50px">回复数</th>
            <th class="center" style="width: 120px">最后回复时间</th>
            <th class="center" style="width: 50px">状态</th>
            <th class="center" style="width: 110px">操作</th>
        </tr>
    </table>
</div>
<!-- BatchOperation -->
<div style="overflow:hidden;">
    <!-- Operation -->
    <%--<div class="BatchOperation fl">--%>
    <%--<input type="checkbox" id="del"/>--%>
    <%--<label for="del" class="btnStyle middle">全选</label>--%>
    <%--<input type="button" value="批量删除" class="btnStyle"/>--%>
    <%--</div>--%>

    <!-- turn page -->
    <div class="text-center">
        <div id="dataCount"></div>
        <ul class="pagination" id="pagination">
        </ul>
    </div>
</div>
</body>

<script type="text/javascript">
    var thisPage = 1;   //默认当前页面为首页
    var pageSize = 10;   //设置页面显示数据条数
    var searchContent = ""; //搜索内容
    var order = "";

    //    首页数据加载
    $(document).ready(function () {
        getPageData();
    });

    //获取页面数据
    function getPageData() {
        var data = {
            thisPage: thisPage,
            pageSize: pageSize,
            searchContent: searchContent,
            order: order
        };
        $.ajax({
            type: "post",
            url: "${pgContext}/getStickers",
            data: data,
            dataType: "json",
            success: function (jsonObj) {
                var jsonData = eval(jsonObj);

                var stickerList = jsonData.list;    //提取用户数据
                getPagination(jsonData);    //处理分页
                getStickerData(stickerList);      //处理数据
            }
        });
    }

    //获取贴纸数据
    function getStickerData(jsonData) {
        var stickerList = eval(jsonData);

        $.each(stickerList, function (index, sticker) {
            var Nob = index + 1;     //序号

            //循环获取数据
            var stickerId = sticker.id;
            var userId = sticker.userId;
            var stickerTitle = sticker.stickerTitle;
            var stickerContent = sticker.stickerContent;
            //解析json时间对象
            var releaseTime = new Date(sticker.releaseTime.time).toLocaleString('chinese', {hour12: false});
            var clickCount = sticker.clickCount;
            var praiseCount = sticker.praiseCount;
            var replyCount = sticker.replyCount;
            var lastReplyTime = new Date(sticker.releaseTime.time).toLocaleString('chinese', {hour12: false});
            var state = sticker.state;

            var nickname = getMasterNickname(userId);

            var stateStr = "";
            if (state == 1) {
                stateStr = "启用";
            } else {
                stateStr = "禁用";
            }

            $("#tableDate").append(
                "<tr>" +
                "   <th class= 'center' >" + Nob + "</th>" +
                "   <th class= 'center' >" + stickerId + "</th>" +
                "   <th class= 'center' >" + stickerTitle + "</th>" +
                "   <th class= 'center' >" + stickerContent + "</th>" +
                "   <th class= 'center' >" + nickname + "</th>" +
                "   <th class= 'center' >" + releaseTime + "</th>" +
                "   <th class= 'center' >" + clickCount + "</th>" +
                "   <th class= 'center' >" + praiseCount + "</th>" +
                "   <th class= 'center' >" + replyCount + "</th>" +
                "   <th class= 'center' >" + lastReplyTime + "</th>" +
                "   <th class= 'center' >" + stateStr + "</th>" +
                "   <th class= 'center' >" +
                "       <a href= 'javascript:void(0);' title= '查看'onclick= 'lookStickerInfo(" + stickerId + ")'>" +
                "           <img src= 'images/icon_view.gif'/></a>" +
                "       <a href= 'javascript:void(0);' title='启用' onclick= 'enableSticker(" + stickerId + ")'>" +
                "           <img src= 'images/icon_edit.gif'/></a>" +
                "       <a href= 'javascript:void(0);' title='禁用' onclick= 'disableSticker(" + stickerId + ")'>" +
                "           <img src= 'images/icon_drop.gif'/></a>" +
                "       <a href= 'javascript:void(0);' title='彻底删除' onclick= 'deleteSticker(" + stickerId + ")'>" +
                "           删除</a>" +
                "   </th>" +
                " </tr>"
            );
        });
    }

    //    获取分页信息
    function getPagination(jsonData) {
        var dataTotal = jsonData.dataTotal;     //提取记录总条数
        var start = jsonData.start; //提取从第几页开始显示页码
        var pageTotal = jsonData.pageTotal;     //提取页面总数
        var end = jsonData.end;     //提取页码显示到第几页
        var thisPg = jsonData.thisPage;   //提取当前显示的页面

        if (dataTotal > pageSize) {
            $("#dataCount").html(
                "共：" + dataTotal + " 条记录，本页显示：" + pageSize + " 条"
            );
        }
        if (thisPg > 1) {
            $("#pagination").html(
                "<li><a href='/index.jsp'>首页</a></li>"
            );
            $("#pagination").append(
                "<li><a href='javascript:void(0);' onclick= 'getPageValue(" + (thisPg - 1) + ")'>上一页</a></li>"
            );
        } else {
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

    //某个页面按钮被点击时，获取它所代表页面的页面内容
    function getPageValue(index) {

        thisPage = index;
        if (thisPage != 1) {
            refreshPageData();
        } else {
            getPageData();
        }
    }

    //刷新页面数据
    function refreshPageData() {
        var data = {
            thisPage: thisPage,
            pageSize: pageSize,
            searchContent: searchContent,
            order: order
        };
        $.ajax({
            type: "post",
            url: "${pgContext}/getStickers",
            data: data,
            dataType: "json",
            success: function (jsonObj) {
                var jsonData = eval(jsonObj);

                var stickerList = jsonData.list;    //提取贴纸数据
                getPagination(jsonData);    //处理分页
                refreshStickerData(stickerList);      //刷新贴纸数据
            }
        });
    }

    //刷新贴纸数据
    function refreshStickerData(jsonData) {

        $("#tableDate").html(
            "<tr>" +
            "   <th class= 'center' style='width: 40px'>序号</th>" +
            "   <th class= 'center' style='width: 50px'>id</th>" +
            "   <th class= 'center' style='width: 120px'>贴纸标题</th>" +
            "   <th class= 'center' style='width: 200px'>贴纸内容</th>" +
            "   <th class= 'center' style='width: 120px'>发帖人</th>" +
            "   <th class= 'center' style='width: 120px'>发帖时间</th>" +
            "   <th class= 'center' style='width: 50px'>点击数</th>" +
            "   <th class= 'center' style='width: 50px'>点赞数</th>" +
            "   <th class= 'center' style='width: 50px'>回复数</th>" +
            "   <th class= 'center' style='width: 120px'>最后回复时间</th>" +
            "   <th class= 'center' style='width: 50px'>状态</th>" +
            "   <th class= 'center' style='width: 110px'>操作</th>" +
            "</tr>"
        );

        getStickerData(jsonData);
    }

    //查询
    $("#searchBtn").click(function () {
        searchContent = $("#searchContent").val();

        refreshPageData();
    });

    //查看贴纸信息
    function lookStickerInfo(stickerId) {
        layer.open({        //在当前页面弹窗
            title: '查看贴纸信息',
            type: 2,
            area: ['800px', '400px'],
            fix: false, //不固定
            maxmin: true,
            content: '${pgContext}/lookStickerInfo?stickerId=' + stickerId,
            success: function (layero, index) {
//                AddLayero = layero;
//				        alert(AddLayero);
            },
            end: function () {
                location.reload();
            }
        });
    }

    //封禁贴纸
    function disableSticker(stickerId) {

        var data = {
            stickerId: stickerId,
            operation : 0
        };
        layer.confirm("确认要封禁此贴纸吗？", {title: "封禁确认"},
            function (index) {
                layer.close(index);
                $.ajax({
                    url: "${pgContext}/operationSticker",
                    type: "post",
                    data: data,
                    dataType: "json",
                    success: function (jsonObj) {
                        var jsonData = eval(jsonObj);
                        var res = jsonData.res;

                        if (res == 1) {
                            layer.msg("禁用成功！", {
                                icon: 1,
                                time: 3000 //3秒关闭（如果不配置，默认是3秒）
                            });
                            location.reload();  //更新页面
                        } else if (res == 0) {
                            layer.msg("禁用失败，请重新操作", {
                                icon: 2,
                                time: 3000 //3秒关闭（如果不配置，默认是3秒）
                            });
                        } else {
                            layer.msg("禁用失败，贴纸不存在或已被删除！", {
                                icon: 2,
                                time: 3000 //3秒关闭（如果不配置，默认是3秒）
                            });
                        }
                    }
                });
            }
        );
    }

    //解封贴纸
    function enableSticker(stickerId) {
        var data = {
            stickerId: stickerId,
            operation : 1
        };
        layer.confirm("确认要解封此贴纸吗？", {title: "封禁确认"},
            function (index) {
                layer.close(index);
                $.ajax({
                    url: "${pgContext}/operationSticker",
                    type: "post",
                    data: data,
                    dataType: "json",
                    success: function (jsonObj) {
                        var jsonData = eval(jsonObj);
                        var res = jsonData.res;

                        if (res == 1) {
                            layer.msg("解封成功！", {
                                icon: 1,
                                time: 3000 //3秒关闭（如果不配置，默认是3秒）
                            });
                            location.reload();  //更新页面
                        } else if (res == 0) {
                            layer.msg("解封失败，请重新操作", {
                                icon: 2,
                                time: 3000 //3秒关闭（如果不配置，默认是3秒）
                            });
                        } else {
                            layer.msg("解封失败，贴纸不存在或已被删除！", {
                                icon: 2,
                                time: 3000 //3秒关闭（如果不配置，默认是3秒）
                            });
                        }
                    }
                });
            }
        );
    }

    //删除贴纸
    function deleteSticker(stickerId) {
        var data = {
            stickerId: stickerId,
            operation : -1
        };
        layer.confirm("确认要删除此贴纸吗？", {title: "删除确认"},
            function (index) {
                layer.close(index);
                $.ajax({
                    url: "${pgContext}/operationSticker",
                    type: "post",
                    data: data,
                    dataType: "json",
                    success: function (jsonObj) {
                        var jsonData = eval(jsonObj);
                        var res = jsonData.res;

                        if (res == 1) {
                            layer.msg("删除成功！", {
                                icon: 1,
                                time: 3000 //3秒关闭（如果不配置，默认是3秒）
                            });
                            location.reload();  //更新页面
                        } else if (res == 0) {
                            layer.msg("删除失败，请重新操作", {
                                icon: 2,
                                time: 3000 //3秒关闭（如果不配置，默认是3秒）
                            });
                        } else {
                            layer.msg("删除失败，贴纸不存在或已被删除！", {
                                icon: 2,
                                time: 3000 //3秒关闭（如果不配置，默认是3秒）
                            });
                        }
                    }
                });
            }
        );
    }
    //排序
    $("#order").click(function () {
        order = $("#order").val();
        refreshPageData();
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
