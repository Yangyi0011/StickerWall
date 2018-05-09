package com.stickerwall.servlet;

import com.stickerwall.entity.Sticker;
import com.stickerwall.entity.User;
import com.stickerwall.entity.UserInfo;
import com.stickerwall.service.StickerService;
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

public class StickerDetailsServlet extends HttpServlet {
    private StickerService stickerService = new StickerService();
    private UserInfoService userInfoService = new UserInfoService();
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String stickerId = request.getParameter("stickerId");   //获取被查看贴纸的Id

            JSONObject object = new JSONObject();

            if(stickerId != null && !stickerId.equals("")){

                Sticker sticker = stickerService.getStickerByStickerId(Long.valueOf(stickerId));
                sticker.setClickCount(sticker.getClickCount() + 1);     //让贴纸点击量+1
                stickerService.update(sticker);

                User stickerMaster = userService.getUserBySticker(sticker);      //获取发帖用户
                UserInfo masterInfo = userInfoService.getUserInfoByUserId(stickerMaster.getId());   //获取发帖用户的信息

                request.getSession().setAttribute("sticker", sticker);
                request.getSession().setAttribute("masterInfo", masterInfo);

                object.put("res",1);
            }else {
                object.put("res",0);   //贴纸不存在或已被删除
            }

            PrintWriter out = response.getWriter();
            out.print(object);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
