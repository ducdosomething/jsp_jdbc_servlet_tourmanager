package org.example.tourscrud.service;

import org.example.tourscrud.model.ITourDAO;
import org.example.tourscrud.model.Tour;
import org.example.tourscrud.model.Type;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDAO implements ITourDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/tour_manager?allowPublicKeyRetrieval=true&useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "ducle211201";

    private static final String SELECT_ALL_TOURS = "SELECT tour.*, t_type.type as type_name  FROM tour join t_type on tour.type_id = t_type.id order by tour.id;";
    private static final String INSERT_TOUR_SQL = "INSERT INTO tour (id, code, destination, price, img, type_id) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_TOURS_SQL = "update tour set id=?,  code = ?,destination= ?, price =?, img = ?, type_id = ? where id = ?;";
    private static final String SELECT_TOUR_BY_ID = "select tour.*, t_type.id as t_type_id, t_type.type as t_type_type from tour join t_type on tour.type_id = t_type.id  where tour.id = ?;";
    private static final String SELECT_TYPE_BY_ID = "select * from t_type where id =?";
    private static final String DELETE_TOUR_SQL = "delete from tour where id = ?;";
    private static final String UPLOADS_PIC_SQL = "INSERT INTO tour (img) VALUES (?);";

    ConnectionDAO cs = new ConnectionDAO();

    public TourDAO(){

    }

    @Override
    public void addNewTour(Tour tour) throws SQLException {
        System.out.println(INSERT_TOUR_SQL);
        try (Connection connection = cs.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TOUR_SQL)) {
            preparedStatement.setInt(1, tour.getId());
            preparedStatement.setString(2, tour.getCode());
            preparedStatement.setString(3, tour.getDestination());
            preparedStatement.setDouble(4, tour.getPrice());
            preparedStatement.setString(5, tour.getImg());
            preparedStatement.setInt(6, tour.getType().getTypeId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            cs.printSQLException(e);
        }
    }

    @Override
    public Tour searchTourById(int id) {
        Tour tour = null;
        try (Connection connection = cs.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOUR_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String code = rs.getString("code");
                String destination = rs.getString("destination");
                double price = rs.getDouble("price");
                String img = rs.getString("img");
                int typeId = rs.getInt("t_type_id");
                String typeName = rs.getString("t_type_type");
                Type type = new Type(typeId, typeName);

                tour = new Tour(id, code, destination, price, img, type);
            }
        } catch (SQLException e) {
            cs.printSQLException(e);
        }
        return tour;
    }

    @Override
    public List<Tour> showAllTours() {
        List<Tour> tours = new ArrayList<>();

        try {
            Connection connection = cs.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_ALL_TOURS);

            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String destination = rs.getString("destination");
                Double price = rs.getDouble("price");
                String img = rs.getString("img");

                int typeId = rs.getInt("type_id");
                String typeName = rs.getString("type_name");
                Type type = new Type(typeId, typeName);

                Tour tour = new Tour(id, code, destination, price, img, type);
                tours.add(tour);
            }
        } catch (SQLException sqlException) {
            cs.printSQLException(sqlException);
        }

        return tours;
    }

    @Override
    public boolean deleteTour(int id) throws SQLException {
        boolean rowDelete;
        try (Connection connection = cs.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TOUR_SQL);) {
            statement.setInt(1, id);
            rowDelete = statement.executeUpdate() >0;
        }
        return rowDelete;
    }

    @Override
    public boolean updateTour(Tour tour) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = cs.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TOURS_SQL);) {
            statement.setInt(1, tour.getId());
            statement.setString(2, tour.getCode());
            statement.setString(3, tour.getDestination());
            statement.setDouble(4, tour.getPrice());
            statement.setString(5, tour.getImg());
            statement.setInt(6,tour.getType().getTypeId());
            statement.setInt(7,tour.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public void saveImagePathToDatabase(String imagePath) throws ServletException {
        System.out.println(UPLOADS_PIC_SQL);
        try (Connection connection = cs.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPLOADS_PIC_SQL)) {
            preparedStatement.setString(1, imagePath);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage());
        }
    }

    @Override
    public String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    @Override
    public File getFolderUpload() {
        File folderUpload = new File("D:\\DucFile\\intellij\\module3\\tours-crud\\src\\main\\webapp\\img");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    @Override
    public Type searchByTypeId(int id) {
        Type type = null;
        try (Connection connection = cs.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TYPE_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int typeId = Integer.parseInt(rs.getString("id"));
                String typeName = rs.getString("type");

                type = new Type(typeId, typeName);
            }
        } catch (SQLException e) {
            cs.printSQLException(e);
        }
        return type;
    }
    public List<Type> showAllType() {
        List<Type> ty = new ArrayList<>();

        try {
            Connection connection = cs.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from t_type");

            while (rs.next()) {
                int id = rs.getInt("id");
                String typ = rs.getString("type");

                Type ty1 = new Type(id,typ);
                ty.add(ty1);
            }
        } catch (SQLException sqlException) {
            cs.printSQLException(sqlException);
        }

        return ty;
    }
}
