package org.example.tourscrud.service.province;

import org.example.tourscrud.model.DTO.IQuanity;
import org.example.tourscrud.model.DTO.QuanityDTO;
import org.example.tourscrud.service.ConnectionDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityDAO implements IQuanity {

    ConnectionDAO cs = new ConnectionDAO();
    public QuantityDAO() {

    }
    @Override
    public List<QuanityDTO> showAllQuanity() {
        List<QuanityDTO> quanity = new ArrayList<>();
        Connection connection = cs.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select t.type, count(name) as quanity from t_type t join tour tu on t.id = tu.type_id join orderdetails od on tu.id = od.tour_id group by t.type");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String type = rs.getString("type");
                int quanity1 = rs.getInt("quanity");
                QuanityDTO quanityDTO = new QuanityDTO(type,quanity1);
                quanity.add(quanityDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quanity;
    }
}
