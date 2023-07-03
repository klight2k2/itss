package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.db.DB;

public class RoomScheduleEntity extends BaseEntity {
	private int id;
	private int teacherId;
	private int roomId;
	private Date startTime;
	private Date endTime;
	private String reason;

	private UserEntity teacher = new UserEntity();

	public UserEntity getTeacher() {
		return teacher;
	}

	public void setTeacher(UserEntity teacher) {
		this.teacher = teacher;
	}

	public RoomScheduleEntity(int id, int teacherId, int roomId, Date startTime, Date endTime, String reason) {
		this.id = id;
		this.teacherId = teacherId;
		this.roomId = roomId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.reason = reason;
	}

	public RoomScheduleEntity() {
		// Default constructor
	}

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
//				System.out.println(sql_user);
				Statement stm = DB.getConnection().createStatement();
				ResultSet res_user = stm.executeQuery(sql_user);
//                System.out.println(res_user);
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

	@Override
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

	@Override
	public boolean save() throws SQLException {
		try {
			String insertSql = "INSERT INTO room_schedule (id, teacherId, roomId, startTime, endTime, reason) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insertSql);
			preparedStmt.setInt(1, this.id);
			preparedStmt.setInt(2, this.teacherId);
			preparedStmt.setInt(3, this.roomId);
			preparedStmt.setDate(4, new java.sql.Date(this.startTime.getTime()));
			preparedStmt.setDate(5, new java.sql.Date(this.endTime.getTime()));
			preparedStmt.setString(6, this.reason);
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
			String deleteSql = "DELETE FROM room_schedule WHERE id = ?";
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
			String updateSql = "UPDATE room_schedule SET teacherId = ?, roomId = ?, startTime = ?, "
					+ "endTime = ?, reason = ? WHERE id = ?";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(updateSql);
			preparedStmt.setInt(1, this.teacherId);
			preparedStmt.setInt(2, this.roomId);
			preparedStmt.setDate(3, new java.sql.Date(this.startTime.getTime()));
			preparedStmt.setDate(4, new java.sql.Date(this.endTime.getTime()));
			preparedStmt.setString(5, this.reason);
			preparedStmt.setInt(6, this.id);
			preparedStmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
