package org.example.tourscrud.model;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

public interface ITourDAO {
    public void addNewTour(Tour tour) throws SQLException;
    public Tour searchTourById(int id);
    public List<Tour> showAllTours();
    public boolean deleteTour(int id) throws SQLException;
    public boolean updateTour(Tour tour) throws SQLException;
    public void saveImagePathToDatabase(String imagePath) throws ServletException;
    public String extractFileName(Part part);
    public File getFolderUpload();
}
