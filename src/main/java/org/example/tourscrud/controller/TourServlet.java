package org.example.tourscrud.controller;

import org.example.tourscrud.model.Tour;
import org.example.tourscrud.service.TourDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "TourServlet", urlPatterns = "/tours")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
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
                case "create":
                    insertTour(req, resp);
                    break;
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
                case "create":
                    showNewForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteTour(req, resp);
                    break;
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

    private void insertTour(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            fileName = new File(fileName).getName();


            if (part.getName().equals("img")) {
                String imagePath = this.getFolderUpload().getAbsolutePath() + File.separator + fileName;
                part.write(imagePath);

                part.write(request.getServletContext().getRealPath("/") + "img" + File.separator + fileName);

                int id = Integer.parseInt(request.getParameter("id"));
                String code = request.getParameter("code");
                String destination = request.getParameter("destination");
                double price = Double.parseDouble(request.getParameter("price"));

                Tour tour = new Tour(id, code, destination, price, "/img/" + fileName);
                try {
                    tourDAO.addNewTour(tour);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("message", "Upload File Success!");
                System.out.println(request.getServletContext().getContextPath());
                List<Tour> tours = tourDAO.showAllTours();
                request.setAttribute("listTour", tours);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/list.jsp");
                dispatcher.forward(request, response);
            }
        }
        getServletContext().getRequestDispatcher("/view/list.jsp").forward(request, response);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
    public File getFolderUpload() {
        File folderUpload = new File("D:\\DucFile\\intellij\\module3\\tours-crud\\src\\main\\webapp\\img");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteTour(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        tourDAO.deleteTour(id);
        List<Tour> tours = tourDAO.showAllTours();
        req.setAttribute("listTour", tours);
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/list.jsp");
        dispatcher.forward(req, resp);
    }
}
