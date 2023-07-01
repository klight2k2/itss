package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.db.DB;

public class RoomReportEntity extends BaseEntity {
    private int id;
    private int roomId;
    private String status;
    private java.sql.Date createdAt;
    private int reporterId;
    private int approverId;

    public RoomReportEntity(int id, int roomId, String status, java.sql.Date createdAt, int reporterId, int approverId) {
        this.id = id;
        this.roomId = roomId;
        this.status = status;
        this.createdAt = createdAt;
        this.reporterId = reporterId;
        this.approverId = approverId;
    }

    public RoomReportEntity() {
        // Default constructor
    }
    public List<RoomReportEntity> getAllRoomReportByRoomId(int roomId) throws SQLException {
        try {
            String query = "SELECT * FROM room_report WHERE roomId = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(query);
            preparedStmt.setInt(1, roomId);
            ResultSet res = preparedStmt.executeQuery();

            List<RoomReportEntity> roomReports = new ArrayList<>();
            while (res.next()) {
                RoomReportEntity roomReport = new RoomReportEntity(
                    res.getInt("id"),
                    res.getInt("roomId"),
                    res.getString("status"),
                    res.getDate("createdAt"),
                    res.getInt("reporterId"),
                    res.getInt("approverId")
                );
                roomReports.add(roomReport);
            }
            return roomReports;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<RoomReportEntity> getAll() throws SQLException {
        try {
            Statement stm = DB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM room_report");
            ArrayList<RoomReportEntity> roomReports = new ArrayList<>();
            while (res.next()) {
                RoomReportEntity roomReport = new RoomReportEntity(
                    res.getInt("id"),
                    res.getInt("roomId"),
                    res.getString("status"),
                    res.getDate("createdAt"),
                    res.getInt("reporterId"),
                    res.getInt("approverId")
                );
                roomReports.add(roomReport);
            }
            return roomReports;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save() throws SQLException {
        try {
            String insertSql = "INSERT INTO room_report (id, roomId, status, createdAt, reporterId, approverId) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insertSql);
            preparedStmt.setInt(1, this.id);
            preparedStmt.setInt(2, this.roomId);
            preparedStmt.setString(3, this.status);
            preparedStmt.setDate(4, this.createdAt);
            preparedStmt.setInt(5, this.reporterId);
            preparedStmt.setInt(6, this.approverId);
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
            String deleteSql = "DELETE FROM room_report WHERE id = ?";
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
        try {
            String updateSql = "UPDATE room_report SET roomId = ?, status = ?, createdAt = ?, reporterId = ?, " +
                    "approverId = ? WHERE id = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(updateSql);
            preparedStmt.setInt(1, this.roomId);
            preparedStmt.setString(2, this.status);
            preparedStmt.setDate(3, this.createdAt);
            preparedStmt.setInt(4, this.reporterId);
            preparedStmt.setInt(5, this.approverId);
            preparedStmt.setInt(6, this.id);
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.sql.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.sql.Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getReporterId() {
        return reporterId;
    }

    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }

    public int getApproverId() {
        return approverId;
    }

    public void setApproverId(int approverId) {
        this.approverId = approverId;
    }
}