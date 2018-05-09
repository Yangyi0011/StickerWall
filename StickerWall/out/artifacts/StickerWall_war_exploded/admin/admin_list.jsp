<%--
  Created by IntelliJ IDEA.
  User: YangYi
  Date: 2017/11/17
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>管理员列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="style/adminStyle.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript" src="js/public.js"></script>
</head>
<body>
<div class="wrap">
    <div class="page-title">
        <span class="modular fl"><i class="user"></i><em>管理员列表</em></span>
        <span class="modular fr"><a href="javascript:void(0);" onclick="addAdmin()" class="pt-link-btn">+添加管理员</a></span>
    </div>
    <table class="list-style Interlaced" id="tableDate">
        <tr>
            <th class="center">序号</th>
            <th class="center">id</th>
            <th class="center">管理员账号</th>
            <th class="center">管理员昵称</th>
            <th class="center">管理员邮箱</th>
            <th class="center">加入时间</th>
            <th class="center">最后登陆时间</th>
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

<
<script type="text/javascript">
    var thisPage = 1;   //默认当前页面为首页
    var pageSize = 10;   //设置页面显示数据条数

    //    首页数据加载
    $(document).ready(function () {
        getPageData();
    });

    //获取页面数据
    function getPageData() {
        var data = {
            thisPage: thisPage,
            pageSize: pageSize,
        };
        $.ajax({
            type: "post",
            url: "${pgContext}/getAdmins",
            data: data,
            dataType: "json",
            success: function (jsonObj) {
                var jsonData = eval(jsonObj);

                var adminList = jsonData.list;    //提取用户数据
                getPagination(jsonData);    //处理分页
                getAdminData(adminList);      //处理数据
            }
        });
    }

    //获取管理员数据
    function getAdminData(jsonData) {
        var adminList = eval(jsonData);

        $.each(adminList, function (index, admin) {
            var Nob = index + 1;     //序号
            var adminId = admin.id;
            var name = admin.name;
            var nickname = admin.nickname;
            var email = admin.email;
            var joinTime = new Date(admin.joinTime.time).toLocaleString('chinese', {hour12: false});
            var lastLoginTime = new Date(admin.lastLoginTime.time).toLocaleString('chinese', {hour12: false});

            $("#tableDate").append(
                "<tr>" +
                "   <th class= 'center' >" + Nob + "</th>" +
                "   <th class= 'center' >" + adminId + "</th>" +
                "   <th class= 'center' >" + name + "</th>" +
                "   <th class= 'center' >" + nickname + "</th>" +
                "   <th class= 'center' >" + email + "</th>" +
                "   <th class= 'center' >" + joinTime + "</th>" +
                "   <th class= 'center' >" + lastLoginTime + "</th>" +
                "   <th class= 'center' >" +
                "       <a href= 'javascript:void(0);' title= '查看'onclick= 'lookAdmin(" + adminId + ")'>" +
                "           <img src= 'images/icon_view.gif'/></a>" +
                "       <a href= 'javascript:void(0);' title='删除' onclick= 'deleteAdmin(" + adminId + ")'>" +
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
        };
        $.ajax({
            type: "post",
            url: "${pgContext}/getAdmins",
            data: data,
            dataType: "json",
            success: function (jsonObj) {
                var jsonData = eval(jsonObj);

                var adminList = jsonData.list;    //提取管理员数据
                getPagination(jsonData);    //处理分页
                refreshUserData(adminList);      //刷新管理员数据
            }
        });
    }
    //刷新管理员数据
    function refreshUserData(jsonData) {

        $("#tableDate").html(
            "<tr>" +
            "   <th class= 'center' >序号</th>" +
            "   <th class= 'center' >id</th>" +
            "   <th class= 'center' >管理员账号</th>" +
            "   <th class= 'center' >管理员昵称</th>" +
            "   <th class= 'center' >管理员邮箱</th>" +
            "   <th class= 'center' >加入时间</th>" +
            "   <th class= 'center' >最后登陆时间</th>" +
            "   <th class= 'center' >操作</th>" +
            "</tr>"
        );
        getAdminData(jsonData);
    }

    //查看管理员信息
    function lookAdmin(adminId) {
        layer.open({        //在当前页面弹窗
            title: '查看管理员信息',
            type: 2,
            area: ['800px', '400px'],
            fix: false, //不固定
            maxmin: true,
            content: '${pgContext}/lookAdmin?adminId=' + adminId,
            success: function (layero, index) {
//                AddLayero = layero;
//				        alert(AddLayero);
            },
            end: function () {
                location.reload();
            }
        });
    }

    //删除贴纸
    function deleteAdmin(adminId) {
        var data = {
            adminId: adminId,
            operation : -1
        };
        layer.confirm("确认要删除这个管理员吗？", {title: "删除确认"},
            function (index) {
                layer.close(index);
                $.ajax({
                    url: "${pgContext}/operationAdmin",
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
                            layer.msg("删除失败，管理员不存在或已被删除！", {
                                icon: 2,
                                time: 3000 //3秒关闭（如果不配置，默认是3秒）
                            });
                        }
                    }
                });
            }
        );
    }
    
    function addAdmin() {
        layer.open({        //在当前页面弹窗
            title: '添加管理员',
            type: 2,
            area: ['800px', '400px'],
            fix: false, //不固定
            maxmin: true,
            content: '${pgContext}/admin/admin_add.jsp',
            success: function (layero, index) {
//                AddLayero = layero;
//				        alert(AddLayero);
            },
            end: function () {
                location.reload();
            }
        });
    }
</script>
</html>
