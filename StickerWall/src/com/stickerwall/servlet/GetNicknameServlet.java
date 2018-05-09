package com.stickerwall.servlet;

import com.stickerwall.service.UserInfoService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetNicknameServlet extends HttpServlet {
    private UserInfoService userInfoService = new UserInfoService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");

        String nickname = null;
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();

        if (!userIdStr.equals(null) && !userIdStr.equals("")){
            long userId = Long.valueOf(userIdStr);
            nickname = userInfoService.getUserInfoByUserId(userId).getNickName();

            jsonObject.put("res", 1);
            jsonObject.put("nickname", nickname);
            out.print(jsonObject);
            out.close();
        }else {

            jsonObject.put("res", 0);   //获取用户昵称出错
            out.print(jsonObject);
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
