package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.db.DB;

public class RoomEntity extends BaseEntity {
	private int id;
	private boolean status;
	private String name;
	public List<EquipmentEntity> listEquipment;
	public List<RoomScheduleEntity> listRoomSchedule;
	public List<RoomReportEntity> listRoomReport;

	public List<RoomReportEntity> getListRoomReport() {
		return listRoomReport;
	}

	public void setListRoomReport(List<RoomReportEntity> listRoomReport) {
		this.listRoomReport = listRoomReport;
	}

	public List<RoomScheduleEntity> getListRoomSchedule() {
		return listRoomSchedule;
	}

	public void setListRoomSchedule(List<RoomScheduleEntity> listRoomSchedule) {
		this.listRoomSchedule = listRoomSchedule;
	}

	public RoomEntity(int id, boolean status, String name) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public RoomEntity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<RoomEntity> getAll() throws SQLException {
		try {
			Statement stm = DB.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select * from room");
			ArrayList<RoomEntity> medium = new ArrayList<>();
			while (res.next()) {
				RoomEntity room = new RoomEntity(res.getInt("id"), res.getBoolean("status"), res.getString("name"));
				room.setListEquipment(new EquipmentEntity().getAllEquipmentInRoom(room.getId()));
				room.setListRoomSchedule(new RoomScheduleEntity().getAllRoomScheduleByRoomId(room.getId()));
				room.setListRoomReport(new RoomReportEntity().getAllRoomReportByRoomId(room.getId()));
				medium.add(room);
			}
			return medium;
		} catch (SQLException e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public boolean save() throws SQLException {
		// TODO Auto-generated method stub
		try {
			String insert_sqlString = " insert into room (id, name, status)" + " values (?, ?, ?)";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
			preparedStmt.setInt(1, this.id);
			preparedStmt.setString(2, this.name);
			preparedStmt.setBoolean(3, this.status);
			preparedStmt.execute();
			return true;

		} catch (SQLException e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
			return false;
		}
	}

	public boolean saveListEquipment() throws SQLException {
		// TODO Auto-generated method stub
		// System.out.println(this.listEquipment.size());
		try {
			Statement stm = DB.getConnection().createStatement();
			List<EquipmentEntity> roomEquipment = this.listEquipment;
			System.out.println(this.getName());
			if (roomEquipment == null) {
				return true;
			}

			for (int i = 0; i < this.listEquipment.size(); i++) {
				String sqlRoomEquipment = "INSERT IGNORE INTO room_equipment (roomId, equipmentId) VALUES (";
				roomEquipment.get(i).save();
				sqlRoomEquipment += this.getId() + ",";
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

	@Override
	public boolean delete() throws SQLException {
		// TODO Auto-generated method stub
		try {
			String insert_sqlString = "DELETE FROM room WHERE id = ?";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
			preparedStmt.setInt(1, this.id);
			preparedStmt.execute();
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public boolean update() throws SQLException {
		// TODO Auto-generated method stub
		try {
			String insert_sqlString = "UPDATE room SET id = ?, name = ?, status = ? WHERE id = ?";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
			preparedStmt.setInt(1, this.id);
			preparedStmt.setString(2, this.name);
			preparedStmt.setBoolean(3, this.status);
			preparedStmt.setInt(4, this.id);
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

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

	public List<EquipmentEntity> getListEquipment() {
		return listEquipment;
	}

	public void setListEquipment(List<EquipmentEntity> listEquipment) {
		this.listEquipment = listEquipment;
	}

	public static void main(String[] args) {
		RoomEntity room = new RoomEntity();

		try {
			List<RoomEntity> a = room.getAll();
			System.out.println(a.get(3).getListRoomSchedule().get(0).getTeacher().getName());
			// System.out.println(a.get(2).getListEquipment().size());
			// System.out.println(a.get(1).getListRoomReport().get(0).getId());
			// System.out.println(a.get(2).getListEquipment().size());
			// a.get(0).saveListEquipment();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
