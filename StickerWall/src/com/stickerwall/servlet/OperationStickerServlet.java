package com.stickerwall.servlet;

import com.stickerwall.entity.Sticker;
import com.stickerwall.service.StickerService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OperationStickerServlet extends HttpServlet {
    private StickerService stickerService = new StickerService();
    private final int Disable = 0;  //禁用
    private final int Enable = 1;   //启用
    private final int Delete = -1;  //删除

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String stickerIdStr = request.getParameter("stickerId");
        JSONObject jsonObject = new JSONObject();

        if (stickerIdStr != null && !stickerIdStr.equals("")){
            if(operation != null && !operation.equals("")){

                long stickerId = Long.valueOf(stickerIdStr);
                Sticker sticker = stickerService.getStickerByStickerId(stickerId);

                if (Integer.valueOf(operation) == Disable){     //禁用

                    sticker.setState(Disable);   //禁用
                    stickerService.update(sticker);

                    jsonObject.put("res",1);    //操作成功
                }else if (Integer.valueOf(operation) == Enable){    //启用

                    sticker.setState(Enable);   //启用
                    stickerService.update(sticker);

                    jsonObject.put("res",1);    //操作成功
                }else if (Integer.valueOf(operation) == Delete){
                    stickerService.delete(sticker);  //删除

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
