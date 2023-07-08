package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controllers.LoginController;
import models.UserEntity;
import models.db.DB;

public class UserService {

	private static UserService repo;

	public static UserService getRepo() {
		if (repo != null) {
			return repo;
		} else {
			return new UserService();
		}
	};

	public UserEntity getUserById(int userId) {
		String sql = "SELECT * FROM user WHERE id = ?";

		try (PreparedStatement statement = DB.getConnection().prepareStatement(sql)) {
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				UserEntity user = new UserEntity(resultSet.getInt("id"), resultSet.getString("name"),
						resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("role"));
				return user;
			} else {
				return null; // Người dùng không tồn tại
			}
		} catch (SQLException e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
			return null;
		}
	}

	public List<UserEntity> getAll() throws SQLException {
		try {
			Statement stm = DB.getConnection().createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM user");
			ArrayList<UserEntity> users = new ArrayList<>();
			while (res.next()) {
				UserEntity user = new UserEntity(res.getInt("id"), res.getString("name"), res.getString("username"),
						res.getString("password"), res.getString("role"));
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean save(UserEntity user) throws SQLException {
		try {
			String insertSql = "INSERT INTO user ( name, username, password, role) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insertSql);
			preparedStmt.setString(1, user.getName());
			preparedStmt.setString(2, user.getUsername());
			preparedStmt.setString(3, user.getPassword());
			preparedStmt.setString(4, user.getRole());
			preparedStmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(UserEntity user) throws SQLException {
		try {
			String deleteSql = "DELETE FROM user WHERE id = ?";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(deleteSql);
			preparedStmt.setInt(1, user.getId());
			preparedStmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(UserEntity user) throws SQLException {
		try {
			String updateSql = "UPDATE user SET name = ?, username = ?, password = ?, role = ? WHERE id = ?";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(updateSql);
			preparedStmt.setString(1, user.getName());
			preparedStmt.setString(2, user.getUsername());
			preparedStmt.setString(3, user.getPassword());
			preparedStmt.setString(4, user.getRole());
			preparedStmt.setInt(5, user.getId());
			preparedStmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkLogin(String userName, String password) throws ClassNotFoundException {
		ResultSet rs;
		try {
			Connection connection = DB.getConnection();
			Statement st = connection.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE userName = '" + userName + "'");
			if (rs == null) {
				return false;
			}
			while (rs.next()) {
				if (rs.getString("password") == null ? password == null : rs.getString("password").equals(password)) {
					LoginController.currentUser.setId(rs.getInt("id"));
					LoginController.currentUser.setName(rs.getString("name"));
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
