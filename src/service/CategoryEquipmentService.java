package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.CategoryEquipment;
import models.db.DB;

public class CategoryEquipmentService {
       private static CategoryEquipmentService repo;

    public static CategoryEquipmentService getRepo() {
        if (repo != null) {
            return repo;
        } else {
            return new CategoryEquipmentService();
        }
    };
    public boolean save(CategoryEquipment categoryEquip) throws SQLException {
        try {
            String insertSql = "INSERT INTO equipment_category (id, name, code) VALUES (?, ?, ?)";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insertSql);
            preparedStmt.setInt(1, categoryEquip.getId());
            preparedStmt.setString(2, categoryEquip.getName());
            preparedStmt.setString(3, categoryEquip.getCode());
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(CategoryEquipment categoryEquip) throws SQLException {
        try {
            String deleteSql = "DELETE FROM equipment_category WHERE id = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(deleteSql);
            preparedStmt.setInt(1, categoryEquip.getId());
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean update() throws SQLException {
        // Update operation may not be applicable for this table structure
        return false;
    }
    
    
    public List<CategoryEquipment> getAll() throws SQLException {
        try {
            Statement stm = DB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM equipment_category");
            ArrayList<CategoryEquipment> equipmentCategories = new ArrayList<>();
            while (res.next()) {
                CategoryEquipment categoryEquipment = new CategoryEquipment(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getString("code"));
                equipmentCategories.add(categoryEquipment);
            }
            return equipmentCategories;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
