package org.example.tourscrud.model;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

public interface ICustomer {
    public void addNewCustomer(Customers customers) throws SQLException;
    public Customers searchCusById(int id);
    public List<Customers> showAllCustomer();
    public boolean deleteCustomer(int id) throws SQLException;

    boolean updateCustomer(Customers customers) throws SQLException;
}
