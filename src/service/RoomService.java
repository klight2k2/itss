package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import models.EquipmentEntity;
import models.RoomEntity;
import models.RoomScheduleEntity;
import models.db.DB;
import utils.NotificationUtil;

public class RoomService extends BaseService<RoomEntity> {
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
			String insert_sqlString = " insert into room (name, status)" + " values (?, ?)";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
			preparedStmt.setString(1, room.getName());
			preparedStmt.setBoolean(2, room.getStatus());
			preparedStmt.execute();
			NotificationUtil.success("Thành công", "Thêm phòng thành công");
			return true;

		} catch (SQLException e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
			e.printStackTrace();
			NotificationUtil.error("Thất bại", "Thêm phòng thất bại");
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

			StringBuffer sqlRoomEquipment = new StringBuffer(
					"UPDATE equipment set roomId=" + room.getId() + " WHERE id in (");
			for (EquipmentEntity equip : roomEquipment) {
				sqlRoomEquipment.append(equip.getId());
				sqlRoomEquipment.append(",");
				// System.out.println(stm.executeUpdate(sqlRoomEquipment));
			}
			sqlRoomEquipment.append(")");
			stm.executeUpdate(sqlRoomEquipment.toString());
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
			NotificationUtil.success("Thành công", "Xóa phòng thành công");
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			NotificationUtil.error("Thất bại", "Xóa phòng thất bại");
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
			NotificationUtil.success("Thành công", "Chỉnh sửa phòng thành công");
			return true;
		} catch (SQLException e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
			NotificationUtil.error("Thất bại", "Chỉnh sửa phòng thất bại");
			return false;
		}
	}

	public RoomEntity getRoomById(int roomId) {
		String sql = "SELECT * FROM room WHERE id = ?";

		try (PreparedStatement statement = DB.getConnection().prepareStatement(sql)) {
			statement.setInt(1, roomId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				RoomEntity room = new RoomEntity(resultSet.getInt("id"), resultSet.getBoolean("status"),
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

	public List<RoomEntity> getAllRoomNotConflict(String startTime, String endTime) {
		System.out.println(startTime + "--" + endTime);
		List<RoomEntity> listGuestRoomEntities = new ArrayList<RoomEntity>();
		try {
			String sqlString = "select r.* from room r where r.id in (select roomId from room_schedule where startTime >= '"
					+ endTime + "' or endTime <= '" + startTime + "') or status = 0 and id != 1";
			Statement aStatement = DB.getConnection().createStatement();
			ResultSet resultSet = aStatement.executeQuery(sqlString);
			while (resultSet.next()) {
				System.out.println(resultSet.getString("name"));
				listGuestRoomEntities.add(new RoomEntity(resultSet.getInt("id"), resultSet.getBoolean("status"),
						resultSet.getString("name")));
			}
			return listGuestRoomEntities;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return listGuestRoomEntities;
		}
	}

	public boolean autoChangeRoom(int scheduleId, LocalDateTime startTime, LocalDateTime endTime) {
//		System.out.println(startTime + "--" + endTime);
//		List<RoomEntity> listGuestRoomEntities = new ArrayList<RoomEntity>();
		try {
			String sqlString = "select r.* from room r where r.id in (select roomId from room_schedule where startTime >= '"
					+ endTime + "' or endTime <= '" + startTime + "') or status = 0 and id != 1";
			Statement aStatement = DB.getConnection().createStatement();
			ResultSet resultSet = aStatement.executeQuery(sqlString);
			if (resultSet.next()) {
				String sql = "update room_schedule set roomId = " + resultSet.getInt("id") + " where id = "
						+ scheduleId;
				Statement stmStatement = DB.getConnection().createStatement();
				System.out.println(sql);
				stmStatement.execute(sql);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		try {
			RoomScheduleEntity aEntity = new RoomScheduleService().getAll().get(0);
//			System.out.println(aEntity.getStartTime() + "--" + aEntity.getEndTime());
//			List<RoomEntity> roomService = new RoomService().getAllRoomNotConflict("2023-08-04 15:20:30", "2023-08-04 17:20:30");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
