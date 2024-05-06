package org.example.tourscrud.service;

import org.example.tourscrud.model.Customers;
import org.example.tourscrud.model.ICustomer;
import org.example.tourscrud.model.Tour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomer {
    protected Connection getConnect(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour_manager?allowPublicKeyRetrieval=true&useSSL=false","root","quydang123456");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    @Override
    public void addNewCustomer(Customers customers) throws SQLException {
    Connection connection = getConnect();
        PreparedStatement statement = connection.prepareStatement("insert into customer (id,name,age,address,phone,tour_id) values (?,?,?,?,?,?)");
        statement.setInt(1,customers.getId());
        statement.setString(2,customers.getName());
        statement.setInt(3,customers.getAge());;
        statement.setString(4,customers.getAddress());
        statement.setString(5,customers.getPhone());
        statement.setInt(6,customers.getCodeTour());
        System.out.println(statement);
        statement.executeUpdate();

    }


    @Override
    public Customers searchCusById(int id) {
        Customers customers = null;
        Connection connection = getConnect();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from customers where id=?");
            statement.setInt(1,id);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                int age = Integer.parseInt(rs.getString("age"));
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int codetour = Integer.parseInt(rs.getString("tour_id"));
                customers = new Customers(id,name,age,address,phone,codetour);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public List<Customers> showAllCustomer() {
       List<Customers>  customers = new ArrayList<>();
        Connection connection = getConnect();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from customer");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteCustomer(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateCustomer(Tour tour) throws SQLException {
        return false;
    }
}
