package com.stickerwall.servlet;

import com.stickerwall.entity.User;
import com.stickerwall.entity.UserInfo;
import com.stickerwall.service.CheckUserService;
import com.stickerwall.service.UserInfoService;
import com.stickerwall.service.UserService;
import net.sf.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();
    private CheckUserService checkUserService = new CheckUserService();
    private UserInfoService userInfoService = new UserInfoService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        System.out.println("userName：" + userName + ", password：" + password);
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();

        if (userName != null && !userName.equals("")) {
            boolean isExist = checkUserService.CheckUserName(userName);

            if (!isExist && password != null && !password.equals("")) {
//                    添加新用户
                User user = new User();
                user.setName(userName);

                user.setPassword(password);
                try {
                    userService.save(user);
                    UserInfo userInfo = userInfoService.getUserInfoByUserName(userName);
                    request.getSession().setAttribute("userInfo", userInfo);
                    //注册成功就直接登录
                    request.getSession().setAttribute("login_flag", "success");

                    res.put("res", 1);
                    out.print(res);
                    out.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {     //注册失败！
                res.put("res", 0);
                out.print(res);
                out.close();
            }
        }else {     //注册失败！
            res.put("res", 0);
            out.print(res);
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
