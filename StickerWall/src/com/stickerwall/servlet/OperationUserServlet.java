package com.stickerwall.servlet;

import com.stickerwall.entity.UserInfo;
import com.stickerwall.service.UserInfoService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OperationUserServlet extends HttpServlet {
    UserInfoService userInfoService = new UserInfoService();
    private final int Disable = 0;  //禁用
    private final int Enable = 1;   //启用

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String userIdStr = request.getParameter("userId");
        JSONObject jsonObject = new JSONObject();

        if (userIdStr != null && !userIdStr.equals("")){
            if(operation != null && !operation.equals("")){

                long userId = Long.valueOf(userIdStr);
                UserInfo userInfo = userInfoService.getUserInfoByUserId(userId); //获取用户信息

                if (Integer.valueOf(operation) == Disable){     //禁用

                    userInfo.setState(Disable);   //禁用
                    userInfoService.updateUserInfo(userId,userInfo);

                    jsonObject.put("res",1);    //操作成功
                }else if (Integer.valueOf(operation) == Enable){    //启用

                    userInfo.setState(Enable);   //启用
                    userInfoService.updateUserInfo(userId,userInfo);

                    jsonObject.put("res",1);    //操作成功
                }else {
                    jsonObject.put("res",0);    //操作失败，请重新操作
                }
            }else {
                jsonObject.put("res",0);    //操作失败，请重新操作
            }
        }else {
            jsonObject.put("res",-1);    //操作失败，用户不存在或已被删除
        }

        PrintWriter out = response.getWriter();
        out.print(jsonObject);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
