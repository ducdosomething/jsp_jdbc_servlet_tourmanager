package org.example.tourscrud.controller;

import org.example.tourscrud.model.Tour;
import org.example.tourscrud.service.TourDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "TourServlet", urlPatterns = "/tours")
public class TourServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TourDAO tourDAO;
    public void init() {
        tourDAO = new TourDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
//                case "create":
//                    insertUser(req, resp);
//                    break;
                case "edit":
                    updateTour(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
//                case "create":
//                    showNewForm(request, response);
//                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
//                case "delete":
//                    deleteUser(request, response);
//                    break;
                default:
                    listTour(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listTour(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        List<Tour> listTour = tourDAO.showAllTours();
        req.setAttribute("listTour", listTour);
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/list.jsp");
        dispatcher.forward(req, resp);
    }

    private void updateTour(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String code = req.getParameter("code");
        String destination = req.getParameter("destination");
        double price = Double.parseDouble(req.getParameter("price"));
        String img = req.getParameter("img");

        Tour editTour = new Tour(id, code, destination, price, img);
        tourDAO.updateTour(editTour);
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/edit.jsp");
        dispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Tour existingTour = tourDAO.searchTourById(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/edit.jsp");
        req.setAttribute("tour", existingTour);
        dispatcher.forward(req, resp);

    }
}
