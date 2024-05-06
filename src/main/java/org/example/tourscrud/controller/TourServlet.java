package org.example.tourscrud.controller;

import org.example.tourscrud.model.Tour;
import org.example.tourscrud.model.Type;
import org.example.tourscrud.service.TourDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
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
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
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
                case "delete":
                    deleteTour(req,resp);
                    break;
                default:
                    listTour(req,resp);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
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

        try {
            switch (action) {
                case "create":
                    showNewForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    showFormDelete(req, resp);
                    break;
                default:
                    listTour(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showFormDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Tour tour = tourDAO.searchTourById(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/delete.jsp");
        req.setAttribute("listdelete", tour);
        dispatcher.forward(req, resp);
    }

    private void listTour(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        List<Tour> listTour = tourDAO.showAllTours();
        req.setAttribute("listTour", listTour);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/list.jsp");
        dispatcher.forward(req, resp);
    }

    private void updateTour(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String code = req.getParameter("code");
        String destination = req.getParameter("destination");
        double price = Double.parseDouble(req.getParameter("price"));
        String img = req.getParameter("img");
        int id_type = Integer.parseInt(req.getParameter("type"));
        Type type = tourDAO.searchByTypeId(id_type);
        Tour editTour = new Tour(id, code, destination, price,"/img/"+ img,type);
        tourDAO.updateTour(editTour);
        List<Tour> listTour = tourDAO.showAllTours();
        req.setAttribute("listTour", listTour);

        RequestDispatcher dispatcher = req.getRequestDispatcher("view/list.jsp");
        dispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Tour existingTour = tourDAO.searchTourById(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/edit.jsp");
        req.setAttribute("tour", existingTour);

        List<Type> types = tourDAO.showAllType();
        req.setAttribute("t_types", types);
        dispatcher.forward(req, resp);

    }

//    private void insertTour(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//        for (Part part : request.getParts()) {
//            String fileName = extractFileName(part);
//            fileName = new File(fileName).getName();
//
//
//            if (part.getName().equals("img")) {
//                String imagePath = this.getFolderUpload().getAbsolutePath() + File.separator + fileName;
//                part.write(imagePath);
//
//                part.write(request.getServletContext().getRealPath("/") + "img" + File.separator + fileName);
//
//                int id = Integer.parseInt(request.getParameter("id"));
//                String code = request.getParameter("code");
//                String destination = request.getParameter("destination");
//                double price = Double.parseDouble(request.getParameter("price"));
//                int type = Integer.parseInt(request.getParameter("type"));
//
//                Type type1 = tourDAO.searchByTypeId(type);
//                Tour tour = new Tour(id, code, destination, price, "/img/" + fileName, type1);
//                try {
//                    tourDAO.addNewTour(tour);
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//                request.setAttribute("message", "Upload File Success!");
//                System.out.println(request.getServletContext().getContextPath());
//                List<Tour> tours = tourDAO.showAllTours();
//                request.setAttribute("listTour", tours);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/list.jsp");
//                dispatcher.forward(request, response);
//            }
//        }
//        getServletContext().getRequestDispatcher("/view/list.jsp").forward(request, response);
//    }

    private void insertTour(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            fileName = new File(fileName).getName();


            if (part.getName().equals("img")) {

                InputStream inputStream = part.getInputStream();
                writeToFile(this.getFolderUpload().getAbsolutePath() + File.separator + fileName, inputStream);
//                String imagePath = this.getFolderUpload().getAbsolutePath() + File.separator + fileName;

                // write len folder cua du an -> de redeploy thi server co anh san
//                part.write(imagePath);


                File folderImg = new File(request.getServletContext().getRealPath("/") + "img" );
                if (!folderImg.exists()) {
                    folderImg.mkdirs();
                }

                InputStream inputStream1 = part.getInputStream();
                writeToFile(request.getServletContext().getRealPath("/") + "img" + File.separator + fileName, inputStream1);

                // write len folder cua server
//                part.write(request.getServletContext().getRealPath("/") + "img" + File.separator + fileName);

                int id = Integer.parseInt(request.getParameter("id"));
                String code = request.getParameter("code");
                String destination = request.getParameter("destination");
                double price = Double.parseDouble(request.getParameter("price"));
                int type = Integer.parseInt(request.getParameter("type"));

                Type type1 = tourDAO.searchByTypeId(type);
                Tour tour = new Tour(id, code, destination, price, "/img/" + fileName, type1);
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

    public void writeToFile(String fileName, InputStream inputStream) {


        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            File file = new File(fileName);
            int bRead = -1;
            while ((bRead = inputStream.read()) != -1) {
                    fileOutputStream.write(bRead);
            }
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create.jsp");

        List<Type> types = tourDAO.showAllType();

        request.setAttribute("t_types", types);
        dispatcher.forward(request, response);
    }

    private void deleteTour(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        tourDAO.deleteTour(id);
        List<Tour> tours = tourDAO.showAllTours();
        req.setAttribute("listTour", tours);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/list.jsp");
        dispatcher.forward(req, resp);
    }
}
