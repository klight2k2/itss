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

public class RoomService {
    private static RoomService repo;

    public static RoomService getRepo() {
        if (repo != null) {
            return repo;
        } else {
            return new RoomService();
        }
    };

    public List<RoomEntity> getAll() throws SQLException {
        try {
            Statement stm = DB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("select * from room");
            ArrayList<RoomEntity> medium = new ArrayList<>();
            while (res.next()) {
                RoomEntity room = new RoomEntity(res.getInt("id"), res.getBoolean("status"), res.getString("name"));
                room.setListEquipment(new EquipmentService().getAllEquipmentInRoom(room.getId()));
                room.setListRoomSchedule(new RoomScheduleService().getAllRoomScheduleByRoomId(room.getId()));
                room.setListRoomReport(new RoomReportService().getAllRoomReportByRoomId(room.getId()));
                medium.add(room);
            }
            return medium;
        } catch (SQLException e) {
            // TODO: handle exception
            return null;
        }
    }

    public boolean save(RoomEntity room) throws SQLException {
        // TODO Auto-generated method stub
        try {
            String insert_sqlString = " insert into room (id, name, status)" + " values (?, ?, ?)";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
            preparedStmt.setInt(1, room.getId());
            preparedStmt.setString(2, room.getName());
            preparedStmt.setBoolean(3, room.getStatus());
            preparedStmt.execute();
            return true;

        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean saveListEquipment(RoomEntity room) throws SQLException {
        // TODO Auto-generated method stub
        // System.out.println(this.listEquipment.size());
        try {
            Statement stm = DB.getConnection().createStatement();
            List<EquipmentEntity> roomEquipment = room.getListEquipment();
            // System.out.println(this.getName());
            if (roomEquipment == null) {
                return true;
            }

            for (int i = 0; i < room.getListEquipment().size(); i++) {
                String sqlRoomEquipment = "INSERT IGNORE INTO room_equipment (roomId, equipmentId) VALUES (";
                new EquipmentService().save(roomEquipment.get(i));
                sqlRoomEquipment += room.getId() + ",";
                sqlRoomEquipment += "'" + roomEquipment.get(i).getId() + "'" + ")";
                System.out.println(sqlRoomEquipment);
                stm.executeUpdate(sqlRoomEquipment);
                // System.out.println(stm.executeUpdate(sqlRoomEquipment));
            }
            return true;

        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean delete(RoomEntity room) throws SQLException {
        // TODO Auto-generated method stub
        try {
            String insert_sqlString = "DELETE FROM room WHERE id = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
            preparedStmt.setInt(1, room.getId());
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            // TODO: handle exception
            return false;
        }
    }

    public boolean update(RoomEntity room) throws SQLException {
        // TODO Auto-generated method stub
        try {
            String insert_sqlString = "UPDATE room SET id = ?, name = ?, status = ? WHERE id = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
            preparedStmt.setInt(1, room.getId());
            preparedStmt.setString(2, room.getName());
            preparedStmt.setBoolean(3, room.getStatus());
            preparedStmt.setInt(4, room.getId());
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }
    }

    public RoomEntity getRoomById(int roomId) {
        String sql = "SELECT * FROM room WHERE id = ?";

        try (PreparedStatement statement = DB.getConnection().prepareStatement(sql)) {
            statement.setInt(1, roomId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                RoomEntity room = new RoomEntity(
                        resultSet.getInt("id"),
                        resultSet.getBoolean("status"),
                        resultSet.getString("name"));
                return room;
            } else {
                return null; // Phòng không tồn tại
            }
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return null;
        }
    }
}
