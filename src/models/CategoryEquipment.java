package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.db.DB;

public class CategoryEquipment extends BaseEntity {
    private int id;
    private String name;
    private String code;

    public CategoryEquipment(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public CategoryEquipment() {
        // Default constructor
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

    public boolean save() throws SQLException {
        try {
            String insertSql = "INSERT INTO equipment_category (id, name, code) VALUES (?, ?, ?)";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insertSql);
            preparedStmt.setInt(1, this.id);
            preparedStmt.setString(2, this.name);
            preparedStmt.setString(3, this.code);
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete() throws SQLException {
        try {
            String deleteSql = "DELETE FROM equipment_category WHERE id = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(deleteSql);
            preparedStmt.setInt(1, this.id);
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update() throws SQLException {
        String updateSql = "UPDATE category_equipment SET name = ?, code = ? WHERE id = ?";
        try (PreparedStatement statement = DB.getConnection().prepareStatement(updateSql)) {
            statement.setString(1, this.name);
            statement.setString(2, this.code);
            statement.setInt(3, this.id);
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
    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
