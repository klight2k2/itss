package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.EquipmentEntity;
import models.PayBorrowEntity;
import models.UserEntity;
import models.db.DB;
import utils.NotificationUtil;

public class PayBorrowService extends BaseService<PayBorrowEntity>{
	private static PayBorrowService repo;

	public static PayBorrowService getRepo() {
		if (repo != null) {
			return repo;
		} else {
			return new PayBorrowService();
		}
	};

	public UserEntity getUserByPayBorrowId(int payBorrowId) {
		try {
			Statement stm = DB.getConnection().createStatement();
			ResultSet res = stm.executeQuery(
					"Select u.* from pay_borrow_equipment pbe, user u where pbe.borrowerId = u.id and pbe.payBorrowId = "
							+ payBorrowId);
			if (res.next()) {
				return new UserEntity(res.getString("id"), res.getString("username"), res.getString("password"),
						res.getString("role"));
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public List<EquipmentEntity> getEquipmentByPayBorrowId(int payBorrowId) {
		try {
			Statement stm = DB.getConnection().createStatement();
			ResultSet res = stm.executeQuery(
					"Select e.* from pay_borrow_equipment pbe, equipment e where pbe.equipmentId = e.id and pbe.payBorrowId = "
							+ payBorrowId);
			List<EquipmentEntity> tmp = new ArrayList<>();
			while (res.next()) {
				tmp.add(new EquipmentEntity(res.getInt("equipmentCategoryId"), res.getString("id"),
						res.getString("name"), res.getInt("status"), res.getDate("mfg"), res.getDate("yearOfUse"),
						res.getInt("numberOfRepairs"), res.getString("note"), res.getInt("roomId")));
			}
			return tmp;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public List<PayBorrowEntity> getAll() throws SQLException {
		try {
			Statement stm = DB.getConnection().createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM pay_borrow");
			ArrayList<PayBorrowEntity> payBorrows = new ArrayList<>();
			while (res.next()) {
				PayBorrowEntity payBorrow = new PayBorrowEntity(res.getInt("id"), res.getDate("fromDate"),
						res.getDate("toDate"), res.getString("status"), res.getString("borrowReason"),
						res.getString("refuseReason"), res.getInt("borrowerId"));
				payBorrows.add(payBorrow);
			}
			return payBorrows;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean save(PayBorrowEntity pay_borrow) throws SQLException {
		try {
			String insertSql = "INSERT INTO pay_borrow (fromDate, toDate, status, borrowReason, refuseReason, borrowerId) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insertSql);
			preparedStmt.setDate(1, pay_borrow.getFromDate());
			preparedStmt.setDate(2, pay_borrow.getToDate());
			preparedStmt.setString(3, pay_borrow.getStatus());
			preparedStmt.setString(4, pay_borrow.getBorrowReason());
			preparedStmt.setString(5, pay_borrow.getRefuseReason());
			preparedStmt.setInt(6, pay_borrow.getBorrowerId());
			preparedStmt.execute();
			ResultSet queryPayBorrow = DB.getConnection().createStatement()
					.executeQuery("Select max(id) as id from pay_borrow ");
			if (queryPayBorrow.next())
				System.out.print(queryPayBorrow.getInt("id"));
			int equipLenght = pay_borrow.getListEquipment().size();

			if (equipLenght > 0) {

				StringBuilder sql = new StringBuilder(
						"INSERT IGNORE INTO pay_borrow_equipment (payBorrowId,equipmentId) VALUES ");
				int cnt = 0;
				for (EquipmentEntity equip : pay_borrow.getListEquipment()) {
					sql.append("(" + queryPayBorrow.getInt("id") + ",'" + equip.getId() + "')");
					cnt += 1;
					if (cnt < equipLenght) {
						sql.append(",");
					}
				}
				;
				sql.append(";");

				System.err.println(sql.toString());
				Statement equipQuery = DB.getConnection().createStatement();
				String sqlCmd = sql.toString();
				equipQuery.execute(sqlCmd);
			}
			NotificationUtil.success("Thành công", "Thêm lịch mượn trả thành công");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			NotificationUtil.error("Thất bại", "Thêm lịch mượn trả thất bại");
			return false;
		}
	}

	public boolean delete(PayBorrowEntity pay_borrow) throws SQLException {
		try {
			String deleteSql = "DELETE FROM pay_borrow WHERE id = ?";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(deleteSql);
			preparedStmt.setInt(1, pay_borrow.getId());
			preparedStmt.execute();

			NotificationUtil.success("Thành công", "Xóa lịch mượn trả thành công");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			NotificationUtil.error("Thất bại", "Xóa lịch mượn trả thất bại");
			return false;
		}
	}

	public boolean update(PayBorrowEntity pay_borrow) throws SQLException {

		// Update operation may not be applicable for pay_borrow getTable() structure
		String sqlPayBorrow = "UPDATE pay_borrow "
				+ "SET fromDate = ?, toDate = ?, status = ?, borrowReason = ?, refuseReason = ?, borrowerId = ? "
				+ "WHERE id = ?";

		try {
			PreparedStatement statement = DB.getConnection().prepareStatement(sqlPayBorrow);
			statement.setDate(1, pay_borrow.getFromDate());
			statement.setDate(2, pay_borrow.getToDate());
			statement.setString(3, pay_borrow.getStatus());
			statement.setString(4, pay_borrow.getBorrowReason());
			statement.setString(5, pay_borrow.getRefuseReason());
			statement.setInt(6, pay_borrow.getBorrowerId());
			statement.setInt(7, pay_borrow.getId());

			statement.executeUpdate();
			String deleteSql = "DELETE FROM pay_borrow_equipment WHERE payBorrowId = ?";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(deleteSql);
			preparedStmt.setInt(1, pay_borrow.getId());
			preparedStmt.execute();
			int equipLenght = pay_borrow.getListEquipment().size();
			if (equipLenght > 0) {

				StringBuilder sql = new StringBuilder(
						"INSERT IGNORE INTO pay_borrow_equipment (payBorrowId,equipmentId) VALUES ");
				int cnt = 0;
				for (EquipmentEntity equip : pay_borrow.getListEquipment()) {
					sql.append("(" + pay_borrow.getId() + ",'" + equip.getId() + "')");
					cnt += 1;
					if (cnt < equipLenght) {
						sql.append(",");
					}
				}
				;
				sql.append(";");

				System.err.println(sql.toString());
				Statement equipQuery = DB.getConnection().createStatement();
				String sqlCmd = sql.toString();
				equipQuery.execute(sqlCmd);
			}
			NotificationUtil.success("Thành công", "Chỉnh sửa lịch mượn trả thành công");
			return true;
		} catch (SQLException e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
			NotificationUtil.error("Thất bại", "Chỉnh sửa lịch mượn trả thất bại");
			return false;
		}
	}

	public boolean saveListEquipmentInPayBorrow(int payBorrowId, List<Integer> listEquipmentId) {
		try {
			for (int i = 0; i < listEquipmentId.size(); i++) {
				Statement stmStatement = DB.getConnection().createStatement();
				String sqlString = "INSERT INTO pay_borrow_equipment (payBorrowId,equipmentId) VALUE (" + payBorrowId
						+ "," + listEquipmentId.get(i) + ")";
				if (stmStatement.execute(sqlString)) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteListEquipmentInPayBorrowId(int payBorrowId, List<Integer> listEquipmentId) {
		try {
			for (int i = 0; i < listEquipmentId.size(); i++) {
				Statement stmStatement = DB.getConnection().createStatement();
				String sqlString = "DELETE FROM pay_borrow_equipment WHERE payBorrowId = " + payBorrowId + " and "
						+ "equipmentId = " + listEquipmentId.get(i);
				if (stmStatement.execute(sqlString)) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
