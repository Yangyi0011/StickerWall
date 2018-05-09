package com.stickerwall.servlet;

import com.stickerwall.entity.User;
import com.stickerwall.entity.UserInfo;
import com.stickerwall.service.UserInfoService;
import com.stickerwall.service.UserService;
import com.stickerwall.util.DateAndTime;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserInfoUpdateServlet extends HttpServlet {
    private UserInfoService userInfoService = new UserInfoService();
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String nickName = request.getParameter("nickName");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");

        java.sql.Date newBirthday = null;
        JSONObject jsonObject = new JSONObject();
        int res = 0;
        if(userName != null && !userName.equals("")){

            User user = userService.getUserByName(userName);
            UserInfo userInfo = userInfoService.getUserInfoByUserId(user.getId());
            userInfo.setNickName(nickName);
            userInfo.setSex(sex);

            if (birthday != null && !birthday.equals("")){
                newBirthday = DateAndTime.strToDate(birthday);
            }
            userInfo.setBirthday(newBirthday);
            userInfo.setAddress(address);

            userInfoService.updateUserInfo(user.getId(),userInfo);

            res = 1;
        }else {
            res = 0;
        }

        jsonObject.put("res",res);
        PrintWriter out = response.getWriter();
        out.print(jsonObject);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
