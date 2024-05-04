package org.example.tourscrud.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.example.tourscrud.model.Tour;
import org.example.tourscrud.service.TourDAO;

@WebServlet(name = "UploadFileServlet", urlPatterns = "/UploadFileServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadFileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TourDAO tourDAO;
    public void init() {
        tourDAO = new TourDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
//        File folderUpload = new File("D:\\DucFile\\intellij\\case_study\\demo\\src\\main\\webapp\\img");
        File folderUpload = new File("D:\\DucFile\\intellij\\module3\\tours-crud\\src\\main\\webapp\\img");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }
}

