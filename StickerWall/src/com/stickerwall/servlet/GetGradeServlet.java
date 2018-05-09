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

public class GetGradeServlet extends HttpServlet {
    private UserInfoService userInfoService = new UserInfoService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        int grade = 0;
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();

        if (!userId.equals(null) && !userId.equals("")){
            grade = userInfoService.getUserInfoByUserId(Long.valueOf(userId)).getGrade();

            jsonObject.put("res", 1);
            jsonObject.put("grade", grade);
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
