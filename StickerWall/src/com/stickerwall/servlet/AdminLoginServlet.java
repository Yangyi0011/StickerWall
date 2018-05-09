package com.stickerwall.servlet;

import com.stickerwall.entity.Admin;
import com.stickerwall.service.AdminService;
import com.stickerwall.util.DateAndTime;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminLoginServlet extends HttpServlet {
    AdminService adminService = new AdminService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("userName");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        String login_admin = null;
        int res = 0;

        if (name != null && !name.equals("") && password != null && !password.equals("")) {
            Admin admin = new Admin();
            admin.setName(name);
            admin.setPassword(password);

            boolean bool = adminService.checkAdmin(admin);

            if (bool) {
                login_admin = "success";

                //更新这个管理员的最后登录时间
                Admin thisAdmin = adminService.getAdminByName(name);
                thisAdmin.setLastLoginTime(DateAndTime.getDateTimeForSql());
                adminService.update(thisAdmin);

                System.out.println("管理员类型：+++++++++++++++ " + thisAdmin.getType());
                request.getSession().setAttribute("admin", thisAdmin);    //存入管理员信息
                res = 1;    //登录成功
            } else {
                login_admin = "error";
                res = 0;
            }
        }else {
            login_admin = "error";
            res = 0;
        }
        request.getSession().setAttribute("login_admin", login_admin);
        jsonObject.put("res", res);
        out.print(jsonObject);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
