package com.stickerwall.servlet;

import com.stickerwall.service.PageBeanService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetUsersServlet extends HttpServlet {
    PageBeanService pageBeanService = new PageBeanService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchContent = request.getParameter("searchContent");
        String order = request.getParameter("order");

        int thisPage = Integer.valueOf(request.getParameter("thisPage"));
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));

        try {
            JSONObject jsonObject = pageBeanService.getUserInfoWithPage(searchContent,order,thisPage,pageSize);

            //向前台的页面输出结果
            PrintWriter out = response.getWriter();
            out.print(jsonObject);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
