package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.db.DB;

public class RoomEntity extends BaseEntity {
	private int id;
	private boolean status;
	private String name;
	private List<EquipmentEntity> listEquipment;
	private List<RoomScheduleEntity> listRoomSchedule;

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
		List<RoomEntity> a = new ArrayList<RoomEntity>();
		try {
			RoomEntity customRoom = room.getAll().get(0);
			customRoom.setName("TEST");
			customRoom.update();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
