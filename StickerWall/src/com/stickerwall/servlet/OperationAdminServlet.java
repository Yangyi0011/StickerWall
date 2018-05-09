package com.stickerwall.servlet;

import com.stickerwall.entity.Admin;
import com.stickerwall.service.AdminService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OperationAdminServlet extends HttpServlet {
    private AdminService adminService = new AdminService();
    private final int Disable = 0;  //禁用
    private final int Enable = 1;   //启用
    private final int Delete = -1;  //删除

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String adminIdStr = request.getParameter("adminId");
        JSONObject jsonObject = new JSONObject();

        if (adminIdStr != null && !adminIdStr.equals("")){
            if(operation != null && !operation.equals("")){

                long adminId = Long.valueOf(adminIdStr);
                Admin admin = adminService.getAdminById(adminId);

                if (Integer.valueOf(operation) == Delete){
                    adminService.delete(admin);  //删除

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
