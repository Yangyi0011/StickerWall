package com.stickerwall.servlet;

import com.stickerwall.entity.Sticker;
import com.stickerwall.service.PageBeanService;
import com.stickerwall.service.StickerService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetRepliesByStickerServlet extends HttpServlet {
    PageBeanService pageBeanService = new PageBeanService();
    StickerService stickerService = new StickerService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int thisPage = Integer.valueOf(request.getParameter("thisPage"));
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));
        Long stickerId = Long.valueOf(request.getParameter("stickerId"));

        Sticker sticker = stickerService.getStickerByStickerId(stickerId);

        JSONObject jsonObject = pageBeanService.getRepliesWithPageBySticker(sticker,thisPage,pageSize);

        //向前台的页面输出结果
        PrintWriter out = response.getWriter();
        out.print(jsonObject);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
