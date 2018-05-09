package com.stickerwall.servlet;

import com.stickerwall.entity.User;
import com.stickerwall.service.StickerService;
import com.stickerwall.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ReleaseStickerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        由userInfo获取userId这种情况在用户不登录就进行发帖，然后被强制登录时发生
        //        此处必须注意：从loginServlet里转过来的是setAttribute！
        JSONObject userInfo = JSONObject.fromObject(request.getAttribute("userInfo"));
        String userId = null;

        //此处为避免用户不登录就直接发帖，之后跳转登录后返回而产生的异常。
        if(userInfo.size() > 0){
            userId = userInfo.get("userId").toString();
        }else {
            userId = request.getParameter("userId");
        }
        String title = request.getParameter("stickerTitle");
        String content = request.getParameter("stickerContent");

        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();

        if(!userId.equals(null) && !userId.equals("")){

            UserService userService = new UserService();
            StickerService stickerService = new StickerService();

            User user = userService.getUserById(Long.valueOf(userId));
            stickerService.save(user, title, content);

            jsonObject.put("res", 1);
            out.print(jsonObject);
            out.close();
        }else {
            jsonObject.put("res", 0);
            out.print(jsonObject);
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
