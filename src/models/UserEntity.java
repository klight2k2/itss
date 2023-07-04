package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.db.DB;

public class UserEntity extends BaseEntity {
    private int id;
    private String name;
    private String username;
    private String password;
    private String role;

    public UserEntity(int id, String name, String username, String password, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserEntity() {
        // Default constructor
    }
    
    public UserEntity getUserById(int userId) {
        String sql = "SELECT * FROM user WHERE id = ?";

        try (PreparedStatement statement = DB.getConnection().prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                UserEntity user = new UserEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
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

    
    @Override
    public List<UserEntity> getAll() throws SQLException {
        try {
            Statement stm = DB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM user");
            ArrayList<UserEntity> users = new ArrayList<>();
            while (res.next()) {
                UserEntity user = new UserEntity(
                    res.getInt("id"),
                    res.getString("name"),
                    res.getString("username"),
                    res.getString("password"),
                    res.getString("role")
                );
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save() throws SQLException {
        try {
            String insertSql = "INSERT INTO user (id, name, username, password, role) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insertSql);
            preparedStmt.setInt(1, this.id);
            preparedStmt.setString(2, this.name);
            preparedStmt.setString(3, this.username);
            preparedStmt.setString(4, this.password);
            preparedStmt.setString(5, this.role);
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
            String deleteSql = "DELETE FROM user WHERE id = ?";
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
            String updateSql = "UPDATE user SET name = ?, username = ?, password = ?, role = ? WHERE id = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(updateSql);
            preparedStmt.setString(1, this.name);
            preparedStmt.setString(2, this.username);
            preparedStmt.setString(3, this.password);
            preparedStmt.setString(4, this.role);
            preparedStmt.setInt(5, this.id);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public static void main(String[] args) {
		try {
			String sql_user = "SELECT * FROM user WHERE id = " + 1;
            System.out.println(sql_user);
            Statement stm = DB.getConnection().createStatement();
            ResultSet res_user = stm.executeQuery(sql_user);
            if (res_user.next()) {
				
            	System.out.println(res_user.getInt("id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
