package org.example.tourscrud.service;

import org.example.tourscrud.model.ITourDAO;
import org.example.tourscrud.model.Tour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDAO implements ITourDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/tour_manager?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "ducle211201";

    private static final String SELECT_ALL_TOURS = "select * from tour";

    public TourDAO(){

    }
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    @Override
    public void addNewTour(Tour tour) throws SQLException {

    }

    @Override
    public Tour searchTourById(int id) {
        return null;
    }

    @Override
    public List<Tour> showAllTours() {
        List<Tour> tours = new ArrayList<>();

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_ALL_TOURS);

            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String destination = rs.getString("destination");
                Double price = rs.getDouble("price");
                String img = rs.getString("img");

                Tour tour = new Tour(id, code, destination, price, img);
                tours.add(tour);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

        return tours;
    }

    @Override
    public boolean deleteTour(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateUser(Tour tour) throws SQLException {
        return false;
    }
}
