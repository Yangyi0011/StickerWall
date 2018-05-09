package com.stickerwall.servlet;

import com.stickerwall.entity.User;
import com.stickerwall.entity.UserInfo;
import com.stickerwall.service.CheckUserService;
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
import java.sql.Date;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private CheckUserService checkUserService = new CheckUserService();
    private UserInfoService userInfoService = new UserInfoService();
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String return_url = request.getParameter("return_url");

        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        String login_user = null;
        int res = 0;

        if (userName != null && !userName.equals("") && password != null && !password.equals("")) {
            User user = new User();
            user.setName(userName);
            user.setPassword(password);
            boolean bool = checkUserService.check(user);

            if (bool) {
                    login_user = "success";

                    //更新用户的最后登录时间
                    User thisUser = userService.getUserByName(userName);
                    UserInfo userInfo = userInfoService.getUserInfoByUserId(thisUser.getId());
                    userInfo.setLastLoginTime(DateAndTime.getDateTimeForSql());

                    userInfoService.updateUserInfo(thisUser.getId(),userInfo);

                    request.getSession().setAttribute("userInfo", userInfo);    //存入用户信息

                    if(!return_url.equals("/releaseSticker") && return_url != null){
                        jsonObject.put("return_url", return_url);
                    }
                    res = 1;
            } else {
                login_user = "error";
                res = 0;
            }
        }else {
            login_user = "error";
            res = 0;
        }
        request.getSession().setAttribute("login_user", login_user);
        jsonObject.put("res", res);
        out.print(jsonObject);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
