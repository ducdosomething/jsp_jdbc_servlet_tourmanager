package org.example.tourscrud.controller;

import org.example.tourscrud.model.DTO.OderdetailDTO;
import org.example.tourscrud.model.DTO.QuanityDTO;
import org.example.tourscrud.model.Tour;
import org.example.tourscrud.service.ConnectionDAO;
import org.example.tourscrud.service.TourDAO;
import org.example.tourscrud.service.province.OderdetailDAO;
import org.example.tourscrud.service.province.QuanityDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet (name = "AdminServlet", urlPatterns = "/viewcustomer")
public class AdminServlet extends HttpServlet {
    private OderdetailDAO oderdetailDAO;
    private QuanityDAO quanityDAO;
    public void init() {
        oderdetailDAO = new OderdetailDAO();
        quanityDAO = new QuanityDAO();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action){
            case "quanity":
                showAllQuanityView(req,resp);
                break;
            default:
            showAllOrder(req,resp);
            break;
        }
    }

    private void showAllQuanityView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<QuanityDTO> listquanity = quanityDAO.showAllQuanity();
        req.setAttribute("listq", listquanity);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/adminviewQuanity.jsp");
        dispatcher.forward(req, resp);
    }

    private void showAllOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OderdetailDTO> listoder = oderdetailDAO.showAllOderdetail();
        req.setAttribute("listoder", listoder);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/adminviewOder.jsp");
        dispatcher.forward(req, resp);
    }
}
