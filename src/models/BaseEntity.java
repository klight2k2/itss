package models;

import java.sql.SQLException;
import java.util.List;

abstract class BaseEntity {
	private int id;
	private String name;

	public BaseEntity() {
		// TODO Auto-generated constructor stub
	}

	abstract public List getAll() throws SQLException;

	abstract public boolean save() throws SQLException;

	abstract public boolean delete() throws SQLException;

	abstract public boolean update() throws SQLException;

	public String getName() {
		return name;
	}

//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
}
