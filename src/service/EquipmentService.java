package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.EquipmentEntity;
import models.RoomEntity;
import models.db.DB;

public class EquipmentService {
    public List<EquipmentEntity> getAll() throws SQLException {
        try {
            Statement stm = DB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("select * from equipment");
            ArrayList<EquipmentEntity> medium = new ArrayList<>();
            while (res.next()) {
                EquipmentEntity equipment = new EquipmentEntity(res.getInt("equipmentCategoryId"), res.getString("id"),
                        res.getString("name"),
                        res.getInt("status"), res.getDate("mfg"), res.getDate("yearOfUse"),
                        res.getInt("numberOfRepairs"),
                        res.getString("note"));
                medium.add(equipment);
            }
            return medium;
        } catch (SQLException e) {
            // TODO: handle exception
            return null;
        }

    }

    public boolean save(EquipmentEntity equipment) throws SQLException {
        try {
            String insert_sqlString = " INSERT IGNORE INTO equipment (equipmentCategoryId, id, name, status, mfg, yearOfUse, numberOfRepairs, note)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
            preparedStmt.setInt(1, equipment.getEquipmentCategoryId());
            preparedStmt.setString(2, equipment.getId());
            preparedStmt.setString(3, equipment.getName());
            preparedStmt.setInt(4, equipment.getStatus());
            preparedStmt.setDate(5, equipment.getMfg());
            preparedStmt.setDate(6, equipment.getYearOfUse());
            preparedStmt.setInt(7, equipment.getNumberOfRepairs());
            preparedStmt.setString(8, equipment.getNote());
            preparedStmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean delete(EquipmentEntity equipment) throws SQLException {
        // TODO Auto-generated method stub
        try {
            String insert_sqlString = "DELETE FROM equipment WHERE id = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
            preparedStmt.setString(1, equipment.getId());
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            // TODO: handle exception
            return false;
        }
    }

    public boolean update(EquipmentEntity equipment) throws SQLException {
        // TODO Auto-generated method stub
        try {
            String insert_sqlString = "UPDATE equipment SET equipmentCategoryId = ?, id = ?, name = ?, status = ?, mfg = ?, yearOfUse = ?, numberOfRepairs = ?, note = ? WHERE id = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
            preparedStmt.setInt(1, equipment.getEquipmentCategoryId());
            preparedStmt.setString(2, equipment.getId());
            preparedStmt.setString(3, equipment.getName());
            preparedStmt.setInt(4, equipment.getStatus());
            preparedStmt.setDate(5, equipment.getMfg());
            preparedStmt.setDate(6, equipment.getYearOfUse());
            preparedStmt.setInt(7, equipment.getNumberOfRepairs());
            preparedStmt.setString(8, equipment.getNote());
            preparedStmt.setString(9, equipment.getId());
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }

    }

    public List<EquipmentEntity> getAllEquipmentInRoom(RoomEntity room) throws SQLException {
        try {
            Statement stm = DB.getConnection().createStatement();
            String insert_sqlString = "select * from room_equipment where roomId = " + room.getId() + ";";
            ResultSet res = stm.executeQuery(insert_sqlString);
            String sql = "SELECT * FROM equipment WHERE id IN (";
            while (res.next()) {
                sql += "'" + res.getString("equipmentId") + "'";
                sql += ",";
            }
            if (sql.charAt(sql.length() - 1) == ',') {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ")";
            if (sql.charAt(sql.length() - 2) != '(') {
                res = stm.executeQuery(sql);
            }
            ArrayList<EquipmentEntity> medium = new ArrayList<>();
            while (res.next()) {
                EquipmentEntity equipment = new EquipmentEntity(res.getInt("equipmentCategoryId"), res.getString("id"),
                        res.getString("name"),
                        res.getInt("status"), res.getDate("mfg"), res.getDate("yearOfUse"),
                        res.getInt("numberOfRepairs"),
                        res.getString("note"));
                medium.add(equipment);
            }
            return medium;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }

    }

    public EquipmentEntity getEquipmentById(String equipmentId) {
        String sql = "SELECT * FROM equipment WHERE id = ?";

        try (PreparedStatement statement = DB.getConnection().prepareStatement(sql)) {
            statement.setString(1, equipmentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                EquipmentEntity equipment = new EquipmentEntity(
                        resultSet.getInt("equipmentCategoryId"),
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("status"),
                        resultSet.getDate("mfg"),
                        resultSet.getDate("yearOfUse"),
                        resultSet.getInt("numberOfRepairs"),
                        resultSet.getString("note"));
                return equipment;
            } else {
                return null; // Thiết bị không tồn tại
            }
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<EquipmentEntity> getAllEquipmentNoUse() throws SQLException {
        try {
            Statement stm = DB.getConnection().createStatement();
            String insert_sqlString = "select equipmentId from room_equipment";
            ResultSet res = stm.executeQuery(insert_sqlString);
            String sql = "SELECT * FROM equipment WHERE id NOT IN (";
            while (res.next()) {
                sql += "'" + res.getString("equipmentId") + "'";
                sql += ",";
            }
            if (sql.charAt(sql.length() - 1) == ',') {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ")";
            if (sql.charAt(sql.length() - 2) != '(') {
                res = stm.executeQuery(sql);
            }
            ArrayList<EquipmentEntity> medium = new ArrayList<>();
            while (res.next()) {
                EquipmentEntity equipment = new EquipmentEntity(res.getInt("equipmentCategoryId"), res.getString("id"),
                        res.getString("name"),
                        res.getInt("status"), res.getDate("mfg"), res.getDate("yearOfUse"),
                        res.getInt("numberOfRepairs"),
                        res.getString("note"));
                medium.add(equipment);
            }
            return medium;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }

    }
}
