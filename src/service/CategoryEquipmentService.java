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

    public boolean save(CategoryEquipment categoryEquipment) throws SQLException {
        try {
            Statement stm = DB.getConnection().createStatement();
            ResultSet resultSet = stm.executeQuery("select count(*) as count from equipment_category");
            if (resultSet.next()) {
                categoryEquipment.setId(resultSet.getInt("count") + 1);
            }
            String insertSql = "INSERT INTO equipment_category (id, name, code) VALUES (?, ?, ?)";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insertSql);
            preparedStmt.setInt(1, categoryEquipment.getId());
            preparedStmt.setString(2, categoryEquipment.getName());
            preparedStmt.setString(3, categoryEquipment.getCode());
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(CategoryEquipment categoryEquipment) throws SQLException {
        try {
            String deleteSql = "DELETE FROM equipment_category WHERE id = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(deleteSql);
            preparedStmt.setInt(1, categoryEquipment.getId());
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(CategoryEquipment categoryEquipment) throws SQLException {
        String updateSql = "UPDATE category_equipment SET name = ?, code = ? WHERE id = ?";
        try (PreparedStatement statement = DB.getConnection().prepareStatement(updateSql)) {
            statement.setString(1, categoryEquipment.getName());
            statement.setString(2, categoryEquipment.getCode());
            statement.setInt(3, categoryEquipment.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static CategoryEquipment getCategoryById(int categoryId) throws SQLException {
        String selectSql = "SELECT * FROM category_equipment WHERE id = ?";
        try (PreparedStatement statement = DB.getConnection().prepareStatement(selectSql)) {
            statement.setInt(1, categoryId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    CategoryEquipment category = new CategoryEquipment();
                    category.setId(resultSet.getInt("id"));
                    category.setName(resultSet.getString("name"));
                    category.setCode(resultSet.getString("code"));
                    return category;
                }
            }
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return null;
    }
}
