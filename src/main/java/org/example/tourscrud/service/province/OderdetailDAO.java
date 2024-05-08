package org.example.tourscrud.service.province;

import org.example.tourscrud.model.Customers;
import org.example.tourscrud.model.DTO.IOderdetail;
import org.example.tourscrud.model.DTO.OderdetailDTO;
import org.example.tourscrud.service.ConnectionDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OderdetailDAO implements IOderdetail {

    ConnectionDAO cs = new ConnectionDAO();
    public OderdetailDAO() {

    }
//    private String jdbcURL = "jdbc:mysql://localhost:3306/tour_manager?allowPublicKeyRetrieval=true&useSSL  =false";
//    private String jdbcUsername = "root";
//    private String jdbcPassword = "ducle211201";
//    protected Connection getConnection() {
//        Connection connection = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
    @Override
    public OderdetailDTO searchCusById(int id) {
        return null;
    }

    @Override
    public List<OderdetailDTO> showAllOderdetail() {
        List<OderdetailDTO> oder = new ArrayList<>();
        Connection connection = cs.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select t.code, o.name, o.address, o.phone, o.member, t.price, (price*member) as total from orderdetails o join tour t on o.tour_id = t.id;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String code = rs.getString("code");
                String name = rs.getString("name");
                String add = rs.getString("address");
                String phone = rs.getString("phone");
                int member = rs.getInt("member");
                int price = rs.getInt("price");
                int total = rs.getInt("total");
                OderdetailDTO odt = new OderdetailDTO(code,name,add,phone,member,price,total);
                oder.add(odt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return oder;
    }

    @Override
    public boolean deleteCustomer(int id) throws SQLException {
        return false;
    }
}
