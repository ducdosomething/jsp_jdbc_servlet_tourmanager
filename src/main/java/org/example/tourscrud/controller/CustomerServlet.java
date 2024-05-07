package org.example.tourscrud.controller;

import org.example.tourscrud.model.Customers;
import org.example.tourscrud.model.Tour;
import org.example.tourscrud.model.Type;
import org.example.tourscrud.service.CustomerDAO;
import org.example.tourscrud.service.TourDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class CustomerServlet extends HttpServlet {
    CustomerDAO customerDAO;
    TourDAO tourDAO;

    public void init() {
        tourDAO = new TourDAO();
        customerDAO = new CustomerDAO();
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

        switch (action) {
            case "delete":
                try {
                    deleteCustomerTour(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "thankyou":
                thankyouPage(req, resp);
                break;
            case "choose":
                showFormOder(req, resp);
                break;
            default:
                listTourCus(req, resp);
                break;
        }
    }

    private void showFormOder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Tour tour = tourDAO.searchTourById(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/listOder.jsp");
//        req.setAttribute("listoder", tour);
        dispatcher.forward(req, resp);
    }

    private void listTourCus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tour> tours = tourDAO.showAllTours();
        req.setAttribute("listTourC", tours);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/listCustomer.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {


//
            case "choose":
                try {
                    insertCustomer(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                listTourCus(req, resp);
        }
    }

    private void deleteCustomerTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id =Integer.parseInt(req.getParameter("id")) ;
        customerDAO.deleteCustomer(id);
        List<Tour> tours = tourDAO.showAllTours();
        req.setAttribute("listTourC", tours);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/listCustomer.jsp");
        dispatcher.forward(req, resp);
    }

    private void thankyouPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/thankyou.jsp");
        dispatcher.forward(req, resp);
    }

    private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id1"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        int member = Integer.parseInt(request.getParameter("member"));
        int tour_id = Integer.parseInt(request.getParameter("id"));
        Customers customers = new Customers(id, name, age, address, phone, member, tour_id);
        customerDAO.addNewCustomer(customers);
        request.setAttribute("listcus", customers);
        Tour tour = tourDAO.searchTourById(tour_id);
        request.setAttribute("listCustour", tour);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/listTourCustomer.jsp");
        dispatcher.forward(request, response);
    }
}
