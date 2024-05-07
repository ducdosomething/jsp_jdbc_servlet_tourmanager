package org.example.tourscrud.controller;

import org.example.tourscrud.model.Admin;
import org.example.tourscrud.service.AdminDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminDAO adminDAO;
    public void init() {
        adminDAO = new AdminDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        req.setAttribute("admin", admin);

        if(adminDAO.checkLogin(admin)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect("tours");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect("/view/error.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Chuyển hướng đến trang đăng nhập (login page)
        resp.sendRedirect("/view/login.jsp");
    }

}
