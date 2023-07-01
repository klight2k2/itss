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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public boolean update() throws SQLException {
        // Update operation may not be applicable for this table structure
        return false;
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
