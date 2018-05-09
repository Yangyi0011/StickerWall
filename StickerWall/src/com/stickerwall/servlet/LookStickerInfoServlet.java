package com.stickerwall.servlet;

import com.stickerwall.entity.Sticker;
import com.stickerwall.service.StickerService;
import net.sf.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LookStickerInfoServlet extends HttpServlet {
    private StickerService stickerService = new StickerService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stickerIdStr = request.getParameter("stickerId");

        if (stickerIdStr != null && !stickerIdStr.equals("")){
            long stickerId = Long.valueOf(stickerIdStr);
            Sticker sticker = stickerService.getStickerByStickerId(stickerId);

            request.setAttribute("sticker",sticker);

            String forward = "admin/lookStickerInfo.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
            requestDispatcher.forward(request,response);
        }else {
            System.out.println("Admin:查看贴纸时，stickerId为空！！！！！！");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
