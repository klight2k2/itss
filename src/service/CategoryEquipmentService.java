package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.CategoryEquipmentEntity;
import models.db.DB;
import utils.NotificationUtil;

public class CategoryEquipmentService {
	private static CategoryEquipmentService repo;

	public static CategoryEquipmentService getRepo() {
		if (repo != null) {
			return repo;
		} else {
			return new CategoryEquipmentService();
		}
	};

	public List<CategoryEquipmentEntity> getAll() throws SQLException {
		try {
			Statement stm = DB.getConnection().createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM equipment_category");
			ArrayList<CategoryEquipmentEntity> equipmentCategories = new ArrayList<>();
			while (res.next()) {
				CategoryEquipmentEntity categoryEquipment = new CategoryEquipmentEntity(res.getInt("id"),
						res.getString("name"), res.getString("code"));
				equipmentCategories.add(categoryEquipment);
			}
			return equipmentCategories;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean save(CategoryEquipmentEntity categoryEquipment) throws SQLException {
		try {

			String insertSql = "INSERT INTO equipment_category ( name, code) VALUES (?, ?)";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insertSql);
			preparedStmt.setString(1, categoryEquipment.getName());
			preparedStmt.setString(2, categoryEquipment.getCode());
			preparedStmt.execute();
			NotificationUtil.success("Thành công", "Thêm loại thiết bị thành công");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			NotificationUtil.error("Thất bại", "Thêm loại thiết bị thất bại");
			return false;
		}
	}

	public boolean delete(CategoryEquipmentEntity categoryEquipment) throws SQLException {
		try {
			String deleteSql = "DELETE FROM equipment_category WHERE id = ?";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(deleteSql);
			preparedStmt.setInt(1, categoryEquipment.getId());
			preparedStmt.execute();
			NotificationUtil.success("Thành công", "Xóa loại thiết bị thành công");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			NotificationUtil.error("Thất bại", "Xóa loại thiết bị thất bại");
			return false;
		}
	}

	public boolean update(CategoryEquipmentEntity categoryEquipment) throws SQLException {
		String updateSql = "UPDATE equipment_category SET name = ?, code = ? WHERE id = ?";
		try (PreparedStatement statement = DB.getConnection().prepareStatement(updateSql)) {
			statement.setString(1, categoryEquipment.getName());
			statement.setString(2, categoryEquipment.getCode());
			statement.setInt(3, categoryEquipment.getId());
			int rowsAffected = statement.executeUpdate();
			NotificationUtil.success("Thành công", "Chỉnh sửa loại thiết bị thành công");
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
			NotificationUtil.error("Thất bại", "Chỉnh sửa loại thiết bị thất bại");
			return false;
		}
	}

	public static CategoryEquipmentEntity getCategoryById(int categoryId) throws SQLException {
		String selectSql = "SELECT * FROM equipment_category WHERE id = ?";
		try (PreparedStatement statement = DB.getConnection().prepareStatement(selectSql)) {
			statement.setInt(1, categoryId);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					CategoryEquipmentEntity category = new CategoryEquipmentEntity();
					category.setId(resultSet.getInt("id"));
					category.setName(resultSet.getString("name"));
					category.setCode(resultSet.getString("code"));
					return category;
				}
			}
		} catch (SQLException e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		return null;
	}
}
