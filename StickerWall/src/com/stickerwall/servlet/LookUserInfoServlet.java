package com.stickerwall.servlet;

import com.stickerwall.entity.UserInfo;
import com.stickerwall.service.UserInfoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LookUserInfoServlet extends HttpServlet {
    private UserInfoService userInfoService = new UserInfoService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");

        if(userIdStr != null && !userIdStr.equals("")){
            long userId = Long.valueOf(userIdStr);

            UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);

            request.setAttribute("userInfo", userInfo);

            String forward = "admin/lookUserInfo.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
            requestDispatcher.forward(request,response);
        }else {
            System.out.println("Admin:查看用户时，userId为空！！！！！！");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
