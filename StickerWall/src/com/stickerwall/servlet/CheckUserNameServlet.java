package com.stickerwall.servlet;

import com.stickerwall.service.CheckUserService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckUserNameServlet extends HttpServlet {
    private CheckUserService checkUserService = new CheckUserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");

        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();

        if(userName != null && userName != ""){

            boolean isExis = checkUserService.CheckUserName(userName);

            if(isExis){
                res.put("res", 0);
                out.print(res);
                out.close();
            }else {
                res.put("res", 1);
                out.print(res);
                out.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
