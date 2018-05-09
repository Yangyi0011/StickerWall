package com.stickerwall.filter;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PermissionFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String servletPath = req.getServletPath();      //获取servlet的地址
        HttpSession session = req.getSession();         //获取session对象
        String login_user = (String) session.getAttribute("login_user");    //获取用户登录的session对象的值
        String login_admin = (String) session.getAttribute("login_admin");    //获取管理员登录的session对象的值

        String forward;    //跳转URL

        //  除去访问首页、登录、注册、关于我们、联系我们、贴纸详情页面外，访问其他页面均需进行权限验证
        //  放行样式文件和js文件
        // 如访问路径含有/admin，则只放行样式文件和登录页面
        if (servletPath != null &&
                servletPath.equals("/index.jsp") || servletPath.equals("/login.jsp")
                || servletPath.equals("/loginServlet") || servletPath.equals("/getIndexDataServlet")
                || servletPath.equals("/register.jsp") || servletPath.equals("/aboutUs.jsp")
                || servletPath.equals("/contactUs.jsp") || servletPath.equals("/checkUserNameServlet")
                || servletPath.equals("/registerServlet") || servletPath.equals("/stickerDetailsServlet")
                || servletPath.equals("/stickerComplete.jsp") || servletPath.equals("/getNicknameServlet")
                || servletPath.equals("/getGradeServlet") || servletPath.equals("/getEXPServlet")
                || servletPath.equals("/getRepliesBySticker") || servletPath.equals("/admin/admin_login.jsp")
                || servletPath.equals("/adminLogin")) {

            chain.doFilter(req, resp);  //放行通用页面及其servlet
        } else {

            //用户已登录成功
            if (login_user != null && login_user.equals("success")) {

                //1.用户不访问管理员界面
                if (!servletPath.contains("/admin")) {
                    chain.doFilter(req, resp);  //放行
                } else {
                    //2.用户想要访问管理员界面
                    if (login_admin != null && login_admin.equals("success")) {
                        chain.doFilter(req, resp);  //放行
                    } else {
                        if (!servletPath.endsWith(".jsp") && (servletPath.contains("bootstrap") || servletPath.contains("js")
                                || servletPath.contains("images") || servletPath.contains("layer")
                                || servletPath.contains("style") || servletPath.contains("css"))
                                ) {
                            chain.doFilter(req, resp);  //放行样式文件和js
                        } else {
                            forward = "/admin/admin_login.jsp";
                            RequestDispatcher rd = req.getRequestDispatcher(forward);
                            rd.forward(req, resp);
                        }
                    }
                }
            } else {     //用户未登录或登录失败

                //1.访问的不是管理界面
                if (!servletPath.contains("/admin")) {

                    //确保管理界面的servlet可以通过
                    if (login_admin != null && login_admin.equals("success")) {
                        chain.doFilter(req, resp);  //放行
                    } else {
                        if ( !servletPath.equals("/releaseSticker") && !servletPath.equals("/praiseServlet")
                                && !servletPath.equals("/replyServlet")
                                && (servletPath.contains("bootstrap") || servletPath.contains("js")
                                || servletPath.contains("imgs") || servletPath.contains("layer")
                                 || servletPath.contains("css"))
                                || servletPath.contains("fonts") || servletPath.contains("fonts-awesome")
                                ) {

                            chain.doFilter(req, resp);  //放行样式文件和js
                        }else {
                            //保存用户当前访问的URL，让其去登录
                            req.setAttribute("return_url", servletPath);
                            forward = "/login.jsp";
                            RequestDispatcher rd = req.getRequestDispatcher(forward);
                            rd.forward(req, resp);
                        }
                    }

                } else {
                    //2.访问的是管理员界面
                    if (login_admin != null && login_admin.equals("success")) {
                        chain.doFilter(req, resp);  //放行
                    } else {
                        if (!servletPath.endsWith(".jsp") && (servletPath.contains("bootstrap") || servletPath.contains("js")
                                || servletPath.contains("images") || servletPath.contains("layer")
                                || servletPath.contains("style") || servletPath.contains("css"))
                                ) {
                            chain.doFilter(req, resp);  //放行样式文件和js
                        } else {
                            forward = "/admin/admin_login.jsp";
                            RequestDispatcher rd = req.getRequestDispatcher(forward);
                            rd.forward(req, resp);
                        }
                    }
                }
            }
        }
    }

    public void destroy() {
    }
}
