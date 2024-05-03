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
//                case "edit":
//                    showEditForm(request, response);
//                    break;
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
}
