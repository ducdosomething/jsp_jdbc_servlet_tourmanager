package org.example.tourscrud.model;

import java.sql.SQLException;
import java.util.List;

public interface ITourDAO {
    public void addNewTour(Tour tour) throws SQLException;
    public Tour searchTourById(int id);
    public List<Tour> showAllTours();
    public boolean deleteTour(int id) throws SQLException;
    public boolean updateUser(Tour tour) throws SQLException;
}
