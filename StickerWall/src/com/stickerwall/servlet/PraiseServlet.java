package com.stickerwall.servlet;

import com.stickerwall.entity.Sticker;
import com.stickerwall.service.PraiseService;
import com.stickerwall.service.StickerService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PraiseServlet extends HttpServlet {
    private StickerService stickerService = new StickerService();
    private PraiseService praiseService = new PraiseService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String stickerId = request.getParameter("stickerId");   //获取被点赞贴纸的Id
            String userId = request.getParameter("userId");     //获取执行点赞人的Id

            Sticker sticker = stickerService.getStickerByStickerId(Long.valueOf(stickerId));
            boolean isPraise = praiseService.checkPraiseUser(sticker,userId);

            PrintWriter out = response.getWriter();
            JSONObject object = new JSONObject();

            if(isPraise){
//            已经点赞过了，点击则取消点赞
                praiseService.cancelPraise(sticker,Long.valueOf(userId));
                object.put("res",0);
                object.put("praiseCount",sticker.getPraiseCount());

                out.print(object);
                out.close();
            }else {
//            没有点赞过，点击则点赞
                praiseService.doPraise(sticker,Long.valueOf(userId));
                object.put("res",1);
                object.put("praiseCount",sticker.getPraiseCount());

                out.print(object);
                out.close();
            }
        }catch ( Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
