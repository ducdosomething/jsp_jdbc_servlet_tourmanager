package org.example.tourscrud.service;

import org.example.tourscrud.model.Admin;
import org.example.tourscrud.model.IAdminDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO implements IAdminDAO {

    private static final String ADMIN_LOGIN_SQL = "SELECT * FROM admins WHERE username = ? AND password = ?";

    ConnectionDAO cs = new ConnectionDAO();
    public AdminDAO() {

    }

    @Override
    public boolean checkLogin(Admin admin) {
        boolean status = false;
        try (Connection connection = cs.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADMIN_LOGIN_SQL)){
//            statement.setInt(1, admin.getId());
//            statement.setString(2, admin.getName());
            statement.setString(1, admin.getUsername());
            statement.setString(2, admin.getPassword());

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    status = true;
                }
            }
        } catch (SQLException e) {
            cs.printSQLException(e);
        }
        return status;
    }
}
