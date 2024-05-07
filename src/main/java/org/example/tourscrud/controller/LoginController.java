package org.example.tourscrud.controller;

import org.example.tourscrud.service.AdminDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminDAO adminDAO;
    public void init() {
        adminDAO = new AdminDAO();
    }
}
