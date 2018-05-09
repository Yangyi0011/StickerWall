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
    <title>用户列表</title>
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
        <span class="modular fl"><i></i><em>用户列表</em></span>
    </div>
    <div class="operate row">
        <div class="col-sm-5">
            <strong>选择排序：</strong>

            <select class="inline-select" id="order">
                <option value="grade">按用户等级排</option>
                <option value="lastLoginTime">按最后登录时间排</option>
            </select>
        </div>
        <div class="col-sm-2"></div>
        <div class="col-sm-5">
            <input id="searchContent" type="text" class="textBox length-long" placeholder="请输入用户账号/昵称进行查询……"/>
            <input type="button" value="查询" class="tdBtn" id="searchBtn"/>
        </div>
    </div>
    <table class="list-style Interlaced" id="tableDate">
        <tr>
            <%--<th class="center" style="width: 40px">序号</th>--%>
            <%--<th class="center" style="width: 40px">id</th>--%>
            <%--<th class="center" style="width: 100px">账号</th>--%>
            <%--<th class="center" style="width: 100px">昵称</th>--%>
            <%--<th class="center" style="width: 50px">性别</th>--%>
            <%--<th class="center" style="width: 100px">生日</th>--%>
            <%--<th class="center" style="width: 150px">个性签名</th>--%>
            <%--<th class="center">经验</th>--%>
            <%--<th class="center">等级</th>--%>
            <%--<th class="center" style="width: 150px">注册时间</th>--%>
            <%--<th class="center" style="width: 150px">最后登录时间</th>--%>
            <%--<th class="center" style="width: 40px">状态</th>--%>
            <th class="center">序号</th>
            <th class="center">id</th>
            <th class="center">账号</th>
            <th class="center">昵称</th>
            <th class="center">性别</th>
            <th class="center">生日</th>
            <th class="center">地址</th>
            <th class="center">个性签名</th>
            <th class="center">经验</th>
            <th class="center">等级</th>
            <th class="center">注册时间</th>
            <th class="center">最后登录时间</th>
            <th class="center">状态</th>
            <th class="center">操作</th>
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
            url: "${pgContext}/getUsers",
            data: data,
            dataType: "json",
            success: function (jsonObj) {
                var jsonData = eval(jsonObj);

                var userInfoList = jsonData.list;    //提取用户数据
                getPagination(jsonData);    //处理分页
                getUserData(userInfoList);      //处理数据
            }
        });
    }

    //获取用户数据
    function getUserData(jsonData) {
        var userInfoList = eval(jsonData);
        $.each(userInfoList, function (index, userInfo) {
            var Nob = index + 1;     //序号

            var userInfoId = userInfo.id;
            var userName = userInfo.userName;
            var nickName = userInfo.nickName;
            var userId = userInfo.userId;
            var headPortrait = userInfo.headPortrait;
            var sex = userInfo.sex;
            var birthday_date = userInfo.birthday;
            var birthday = null;
            if (birthday_date != null){
                birthday = new Date(birthday_date.time).toLocaleDateString();
            }
            var address = userInfo.address;
            var EXP = userInfo.EXP;
            var grade = userInfo.grade;
            var motto = userInfo.motto;
            var lastLoginTime = new Date(userInfo.lastLoginTime.time).toLocaleString('chinese', {hour12: false});
            var registrationTime = new Date(userInfo.registrationTime.time).toLocaleString('chinese', {hour12: false});
            var state = userInfo.state;

            var stateStr = "";
            if (state == 1) {
                stateStr = "启用";
            } else {
                stateStr = "禁用";
            }

            $("#tableDate").append(
                "<tr>" +
                "   <th class= 'center' style='width: 40px'>" + Nob + "</th>" +
                "   <th class= 'center' style='width: 60px'>" + userInfoId + "</th>" +
                "   <th class= 'center' style='width: 120px'>" + userName + "</th>" +
                "   <th class= 'center' style='width: 120px'>" + nickName + "</th>" +
                "   <th class= 'center' style='width: 40px'>" + sex + "</th>" +
                "   <th class= 'center' style='width: 120px'>" + birthday + "</th>" +
                "   <th class= 'center' style='width: 150px'>" + address + "</th>" +
                "   <th class= 'center' style='width: 200px'>" + motto + "</th>" +
                "   <th class= 'center' style='width: 40px'>" + EXP + "</th>" +
                "   <th class= 'center' style='width: 40px'>" + grade + "</th>" +
                "   <th class= 'center' style='width: 120px'>" + registrationTime + "</th>" +
                "   <th class= 'center' style='width: 120px'>" + lastLoginTime + "</th>" +
                "   <th class= 'center' style='width: 60px'>" + stateStr + "</th>" +
                "   <th class= 'center' style='width: 120px'>" +
                "       <a href= 'javascript:void(0);' title= '查看'onclick= 'lookUserInfo(" + userId + ")'>" +
                "           <img src= 'images/icon_view.gif'/></a>" +
                "       <a href= 'javascript:void(0);' title='启用' onclick= 'enableUser(" + userId + ")'>" +
                "           <img src= 'images/icon_edit.gif'/></a>" +
                "       <a href= 'javascript:void(0);' title='禁用' onclick= 'disableUser(" + userId + ")'>" +
                "           <img src= 'images/icon_drop.gif'/></a>" +
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
            url: "${pgContext}/getUsers",
            data: data,
            dataType: "json",
            success: function (jsonObj) {
                var jsonData = eval(jsonObj);

                var userList = jsonData.list;    //提取用户数据
                getPagination(jsonData);    //处理分页
                refreshUserData(userList);      //刷新用户数据
            }
        });
    }

    //刷新用户数据
    function refreshUserData(jsonData) {

        $("#tableDate").html(
            "<tr>" +
            "   <th class= 'center' style='width: 40px'>序号</th>" +
            "   <th class= 'center' style='width: 60px'>id</th>" +
            "   <th class= 'center' style='width: 120px'>账号</th>" +
            "   <th class= 'center' style='width: 120px'>昵称</th>" +
            "   <th class= 'center' style='width: 40px'>性别</th>" +
            "   <th class= 'center' style='width: 120px'>生日</th>" +
            "   <th class= 'center' style='width: 150px'>地址</th>" +
            "   <th class= 'center' style='width: 200px'>个性签名</th>" +
            "   <th class= 'center' style='width: 40px'>经验</th>" +
            "   <th class= 'center' style='width: 40px'>等级</th>" +
            "   <th class= 'center' style='width: 120px'>注册时间</th>" +
            "   <th class= 'center' style='width: 120px'>最后登录时间</th>" +
            "   <th class= 'center' style='width: 60px'>状态</th>" +
            "   <th class= 'center' style='width: 120px'>操作</th>" +
            "</tr>"
        );

        getUserData(jsonData);
    }

    //查询
    $("#searchBtn").click(function () {
        searchContent = $("#searchContent").val();

        refreshPageData();
    });

    //查看用户信息
    function lookUserInfo(userId) {
        layer.open({        //在当前页面弹窗
            title: '查看用户信息',
            type: 2,
            area: ['800px', '400px'],
            fix: false, //不固定
            maxmin: true,
            content: '${pgContext}/lookUserInfo?userId=' + userId,
            success: function (layero, index) {
//                AddLayero = layero;
//				        alert(AddLayero);
            },
            end: function () {
                location.reload();
            }
        });
    }

    //封禁用户
    function disableUser(userId) {
        var data = {
            userId: userId,
            operation : 0
        };
        layer.confirm("确认要封禁此用户吗？", {title: "封禁确认"},
            function (index) {
                layer.close(index);
                $.ajax({
                    url: "${pgContext}/operationUser",
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
                            layer.msg("禁用失败，用户不存在或已被删除！", {
                                icon: 2,
                                time: 3000 //3秒关闭（如果不配置，默认是3秒）
                            });
                        }
                    }
                });
            }
        );
    }

    //解封用户
    function enableUser(userId) {
        var data = {
            userId: userId,
            operation : 1
        };
        layer.confirm("确认要解封此用户吗？", {title: "封禁确认"},
            function (index) {
                layer.close(index);
                $.ajax({
                    url: "${pgContext}/operationUser",
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
                            layer.msg("解封失败，用户不存在或已被删除！", {
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
</script>
</html>
