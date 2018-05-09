package com.stickerwall.servlet;

import com.stickerwall.entity.Admin;
import com.stickerwall.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LookAdminServlet extends HttpServlet {
    private AdminService adminService = new AdminService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminIdStr = request.getParameter("adminId");

        if(adminIdStr != null && !adminIdStr.equals("")){
            long adminId = Long.valueOf(adminIdStr);

            Admin admin = adminService.getAdminById(adminId);
            request.setAttribute("admin", admin);

            String forward = "admin/lookAdmin.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
            requestDispatcher.forward(request,response);
        }else {
            System.out.println("超级管理员:查看Admin时，adminId为空！！！！！！");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
