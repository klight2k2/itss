package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.EquipmentEntity;
import models.RoomEntity;
import models.RoomReportEntity;
import models.db.DB;

public class RoomReportService {
    public RoomReportEntity getRoomReportById(int reportId) {
        String sql = "SELECT * FROM room_report WHERE id = ?";

        try (PreparedStatement statement = DB.getConnection().prepareStatement(sql)) {
            statement.setInt(1, reportId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                RoomReportEntity report = new RoomReportEntity(
                        resultSet.getInt("id"),
                        resultSet.getInt("roomId"),
                        resultSet.getString("status"),
                        resultSet.getDate("createdAt"),
                        resultSet.getInt("reporterId"),
                        resultSet.getInt("approverId"));
                return report;
            } else {
                return null; // Báo cáo không tồn tại
            }
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<RoomReportEntity> getAllRoomReportByRoomId(RoomEntity room) throws SQLException {
        try {
            Statement stm = DB.getConnection().createStatement();
            String queryRoom_report = "SELECT * FROM room_report WHERE roomId = " + room.getId();
            // System.out.println(queryRoom_report);
            ResultSet res = stm.executeQuery(queryRoom_report);
            ArrayList<RoomReportEntity> roomReports = new ArrayList<>();
            while (res.next()) {
                RoomReportEntity roomReport = new RoomReportEntity(res.getInt("id"), res.getInt("roomId"),
                        res.getString("status"), res.getDate("createdAt"), res.getInt("reporterId"),
                        res.getInt("approverId"));

                String sql_room_equipment_report = "SELECT e.* FROM equipment e "
                        + "join room_equipment_report rer ON e.id = rer.equipmentId "
                        + "JOIN room_report rr ON rer.roomReportId = rr.roomId " + "WHERE rr.id = " + res.getInt("id");
                // System.out.println(sql_room_equipment_report);
                Statement newStm = DB.getConnection().createStatement();
                ResultSet res_room_equipment_report = newStm.executeQuery(sql_room_equipment_report);
                ArrayList<EquipmentEntity> tmp = new ArrayList<>();
                while (res_room_equipment_report.next()) {
                    tmp.add(new EquipmentEntity(
                            res_room_equipment_report.getInt("equipmentCategoryId"),
                            res_room_equipment_report.getString("id"),
                            res_room_equipment_report.getString("name"),
                            res_room_equipment_report.getInt("status"),
                            res_room_equipment_report.getDate("mfg"),
                            res_room_equipment_report.getDate("yearOfUse"),
                            res_room_equipment_report.getInt("numberOfRepairs"),
                            res_room_equipment_report.getString("note")));

                }
                roomReport.setListEquipmentReport(tmp);
                roomReports.add(roomReport);
            }
            return roomReports;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RoomReportEntity> getAll() throws SQLException {
        try {
            Statement stm = DB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM room_report");
            ArrayList<RoomReportEntity> roomReports = new ArrayList<>();
            while (res.next()) {
                RoomReportEntity roomReport = new RoomReportEntity(res.getInt("id"), res.getInt("roomId"),
                        res.getString("status"), res.getDate("createdAt"), res.getInt("reporterId"),
                        res.getInt("approverId"));
                roomReports.add(roomReport);
            }
            return roomReports;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean save(RoomReportEntity room_report) throws SQLException {
        try {
            String insertSql = "INSERT INTO room_report (id, roomId, status, createdAt, reporterId, approverId) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insertSql);
            preparedStmt.setInt(1, room_report.getId());
            preparedStmt.setInt(2, room_report.getRoomId());
            preparedStmt.setString(3, room_report.getStatus());
            preparedStmt.setDate(4, room_report.getCreatedAt());
            preparedStmt.setInt(5, room_report.getReporterId());
            preparedStmt.setInt(6, room_report.getApproverId());
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(RoomReportEntity room_report) throws SQLException {
        try {
            String deleteSql = "DELETE FROM room_report WHERE id = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(deleteSql);
            preparedStmt.setInt(1, room_report.getId());
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(RoomReportEntity room_report) throws SQLException {
        try {
            String updateSql = "UPDATE room_report SET roomId = ?, status = ?, createdAt = ?, reporterId = ?, "
                    + "approverId = ? WHERE id = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(updateSql);
            preparedStmt.setInt(1, room_report.getRoomId());
            preparedStmt.setString(2, room_report.getStatus());
            preparedStmt.setDate(3, room_report.getCreatedAt());
            preparedStmt.setInt(4, room_report.getReporterId());
            preparedStmt.setInt(5, room_report.getApproverId());
            preparedStmt.setInt(6, room_report.getId());
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
