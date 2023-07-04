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

}
