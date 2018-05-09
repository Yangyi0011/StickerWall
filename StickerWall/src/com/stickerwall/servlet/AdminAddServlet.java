package com.stickerwall.servlet;

import com.stickerwall.entity.Admin;
import com.stickerwall.service.AdminService;
import com.stickerwall.util.DateAndTime;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminAddServlet extends HttpServlet {
    private AdminService adminService = new AdminService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");

        JSONObject jsonObject = new JSONObject();
        int res = 0;

        if(name != null && !name.equals("")
                && password != null && !password.equals("")
                && email != null && !email.equals("")){

            boolean isExist = adminService.checkAdminName(name);

            if( !isExist){
                Admin admin = new Admin();
                admin.setName(name);
                admin.setPassword(password);
                admin.setNickname(nickname);
                admin.setEmail(email);

                adminService.save(admin);
                res = 1;  //添加成功！
            }else {

                Admin thisAdmin = adminService.getAdminByName(name);
                thisAdmin.setName(name);
                thisAdmin.setPassword(password);
                thisAdmin.setNickname(nickname);
                thisAdmin.setEmail(email);

                adminService.update(thisAdmin);

                res = 0;   //管理员已存在,则更新管理员信息
            }
        }else {
            res = -1;   //账号或密码或邮箱为空！
        }
        jsonObject.put("res",res);

        //向前台输出数据
        PrintWriter out = response.getWriter();
        out.print(jsonObject);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
