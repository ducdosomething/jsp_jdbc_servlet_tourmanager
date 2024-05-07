package org.example.tourscrud.model.DTO;

import org.example.tourscrud.model.Customers;

import java.sql.SQLException;
import java.util.List;

public interface IOderdetail {
    public OderdetailDTO searchCusById(int id);
    public List<OderdetailDTO> showAllOderdetail();
    public boolean deleteCustomer(int id) throws SQLException;

}
