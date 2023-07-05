package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.RoomScheduleEntity;
import models.UserEntity;
import models.db.DB;

public class RoomScheduleService {
    private static RoomScheduleService repo;

    public static RoomScheduleService getRepo() {
        if (repo != null) {
            return repo;
        } else {
            return new RoomScheduleService();
        }
    };

    public RoomScheduleEntity getRoomScheduleById(int scheduleId) {
        String sql = "SELECT * FROM room_schedule WHERE id = ?";

        try (PreparedStatement statement = DB.getConnection().prepareStatement(sql)) {
            statement.setInt(1, scheduleId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                RoomScheduleEntity schedule = new RoomScheduleEntity(resultSet.getInt("id"),
                        resultSet.getInt("teacherId"), resultSet.getInt("roomId"), resultSet.getDate("startTime"),
                        resultSet.getDate("endTime"), resultSet.getString("reason"));
                return schedule;
            } else {
                return null; // Lịch trình không tồn tại
            }
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<RoomScheduleEntity> getAllRoomScheduleByRoomId(int roomId) throws SQLException {
        try {
            String query = "SELECT * FROM room_schedule WHERE roomId = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(query);
            preparedStmt.setInt(1, roomId);
            ResultSet res = preparedStmt.executeQuery();

            List<RoomScheduleEntity> roomSchedules = new ArrayList<>();
            while (res.next()) {
                RoomScheduleEntity roomSchedule = new RoomScheduleEntity(res.getInt("id"), res.getInt("teacherId"),
                        res.getInt("roomId"), res.getDate("startTime"), res.getDate("endTime"),
                        res.getString("reason"));
                String sql_user = "SELECT * FROM user WHERE id = " + 1;
                // System.out.println(sql_user);
                Statement stm = DB.getConnection().createStatement();
                ResultSet res_user = stm.executeQuery(sql_user);
                // System.out.println(res_user);
                if (res_user.next()) {
                    UserEntity a = new UserEntity(res_user.getInt("id"), res_user.getString("name"),
                            res_user.getString("username"), res_user.getString("password"), res_user.getString("role"));
                    roomSchedule.setTeacher(a);
                }
                roomSchedules.add(roomSchedule);
            }
            return roomSchedules;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RoomScheduleEntity> getAll() throws SQLException {
        try {
            Statement stm = DB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM room_schedule");
            ArrayList<RoomScheduleEntity> schedules = new ArrayList<>();
            while (res.next()) {
                RoomScheduleEntity schedule = new RoomScheduleEntity(res.getInt("id"), res.getInt("teacherId"),
                        res.getInt("roomId"), res.getDate("startTime"), res.getDate("endTime"),
                        res.getString("reason"));
                schedules.add(schedule);
            }
            return schedules;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean save(RoomScheduleEntity roomSchedule) throws SQLException {
        try {
        	Statement stm = DB.getConnection().createStatement();
        	ResultSet resultSet = stm.executeQuery("select count(*) as count from room_schedule");
        	if (resultSet.next()) {
        		roomSchedule.setId(resultSet.getInt("count")+1);
			}
            String insertSql = "INSERT INTO room_schedule (id, teacherId, roomId, startTime, endTime, reason) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insertSql);
            preparedStmt.setInt(1, roomSchedule.getId());
            preparedStmt.setInt(2, roomSchedule.getTeacherId());
            preparedStmt.setInt(3, roomSchedule.getRoomId());
            preparedStmt.setDate(4, new java.sql.Date(roomSchedule.getStartTime().getTime()));
            preparedStmt.setDate(5, new java.sql.Date(roomSchedule.getEndTime().getTime()));
            preparedStmt.setString(6, roomSchedule.getReason());
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(RoomScheduleEntity roomSchedule) throws SQLException {
        try {
            String deleteSql = "DELETE FROM room_schedule WHERE id = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(deleteSql);
            preparedStmt.setInt(1, roomSchedule.getId());
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(RoomScheduleEntity roomSchedule) throws SQLException {
        try {
            String updateSql = "UPDATE room_schedule SET teacherId = ?, roomId = ?, startTime = ?, "
                    + "endTime = ?, reason = ? WHERE id = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(updateSql);
            preparedStmt.setInt(1, roomSchedule.getTeacherId());
            preparedStmt.setInt(2, roomSchedule.getRoomId());
            preparedStmt.setDate(3, new java.sql.Date(roomSchedule.getStartTime().getTime()));
            preparedStmt.setDate(4, new java.sql.Date(roomSchedule.getEndTime().getTime()));
            preparedStmt.setString(5, roomSchedule.getReason());
            preparedStmt.setInt(6, roomSchedule.getId());
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
