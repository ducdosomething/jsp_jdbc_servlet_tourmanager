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
    private static final String INSERT_TOUR_SQL = "INSERT INTO tour (id, code, destination, price, img) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE_TOURS_SQL = "update tour set code = ?,destination= ?, price =? where id = ?;";
    private static final String SELECT_TOUR_BY_ID = "select id,code,destination,price from tour where id =?";
    private static final String DELETE_TOUR_SQL = "delete from tour where id = ?;";

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
        System.out.println(INSERT_TOUR_SQL);
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TOUR_SQL)) {
            preparedStatement.setInt(1, tour.getId());
            preparedStatement.setString(2, tour.getCode());
            preparedStatement.setString(3, tour.getDestination());
            preparedStatement.setDouble(4, tour.getPrice());
            preparedStatement.setString(5, tour.getImg());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Tour searchTourById(int id) {
        Tour tour = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOUR_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String code = rs.getString("code");
                String destination = rs.getString("destination");
                double price = rs.getDouble("price");
                String img = rs.getString("img");
                tour = new Tour(id, code, destination, price, img);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return tour;
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
        boolean rowDelete;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_TOUR_SQL);) {
            statement.setInt(1, id);
            rowDelete = statement.executeUpdate() >0;
        }
        return rowDelete;
    }

    @Override
    public boolean updateTour(Tour tour) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_TOURS_SQL);) {
            statement.setString(1, tour.getCode());
            statement.setString(2, tour.getDestination());
            statement.setDouble(3, tour.getPrice());
            statement.setInt(4, tour.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
