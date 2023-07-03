package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.Stroke;
import java.sql.Date;

import models.db.DB;

public class EquipmentEntity extends BaseEntity {
	private int equipmentCategoryId;
	private String id;
	private String name;
	private int status;
	private Date mfg;
	private Date yearOfUse;
	private int numberOfRepairs;
	private String note;

	public EquipmentEntity() {
		super();
	}

	public EquipmentEntity(int equipmentCategoryId, String id, String name, int status, Date mfg,
			Date yearOfUse, int numberOfRepairs, String note) {
		super();
		this.equipmentCategoryId = equipmentCategoryId;
		this.id = id;
		this.name = name;
		this.status = status;
		this.mfg = mfg;
		this.yearOfUse = yearOfUse;
		this.numberOfRepairs = numberOfRepairs;
		this.note = note;
	}

	@Override
	public boolean save() throws SQLException {
		try {
			String insert_sqlString = " INSERT IGNORE INTO equipment (equipmentCategoryId, id, name, status, mfg, yearOfUse, numberOfRepairs, note)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
			preparedStmt.setInt(1, this.equipmentCategoryId);
			preparedStmt.setString(2, this.id);
			preparedStmt.setString(3, this.name);
			preparedStmt.setInt(4, this.status);
			preparedStmt.setDate(5, this.mfg);
			preparedStmt.setDate(6, this.yearOfUse);
			preparedStmt.setInt(7, this.numberOfRepairs);
			preparedStmt.setString(8, this.note);
			preparedStmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	
	public List<EquipmentEntity> getAllEquipmentInRoom(int roomId) throws SQLException {
		try {
			Statement stm = DB.getConnection().createStatement();
			String insert_sqlString = "select * from room_equipment where roomId = " + roomId + ";";
			ResultSet res = stm.executeQuery(insert_sqlString);
			String sql = "SELECT * FROM equipment WHERE id IN (";
            while (res.next()) {
//            	System.out.println(roomId + "-" + res.getString("equipmentId"));
                sql += "'" + res.getString("equipmentId") + "'";
                sql += ",";
            }
//            System.out.println(sql.charAt(1));
            if (sql.charAt(sql.length()-1 ) == ',') {
            	sql = sql.substring(0,sql.length()-1);
            }
            sql += ")";
//            System.out.println(sql);
            if (sql.charAt(sql.length()-2)!='(') {            	
            	res = stm.executeQuery(sql);
            }
			ArrayList<EquipmentEntity> medium = new ArrayList<>();
			while (res.next()) {
				EquipmentEntity equipment = new EquipmentEntity(res.getInt("equipmentCategoryId"), res.getString("id"),
						res.getString("name"),
						res.getInt("status"), res.getDate("mfg"), res.getDate("yearOfUse"),
						res.getInt("numberOfRepairs"),
						res.getString("note"));
				medium.add(equipment);
			}
			return medium;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}
	
	public EquipmentEntity getEquipmentById(String equipmentId) {
	    String sql = "SELECT * FROM equipment WHERE id = ?";

	    try (PreparedStatement statement = DB.getConnection().prepareStatement(sql)) {
	        statement.setString(1, equipmentId);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            EquipmentEntity equipment = new EquipmentEntity(
	                    resultSet.getInt("equipmentCategoryId"),
	                    resultSet.getString("id"),
	                    resultSet.getString("name"),
	                    resultSet.getInt("status"),
	                    resultSet.getDate("mfg"),
	                    resultSet.getDate("yearOfUse"),
	                    resultSet.getInt("numberOfRepairs"),
	                    resultSet.getString("note")
	            );
	            return equipment;
	        } else {
	            return null; // Thiết bị không tồn tại
	        }
	    } catch (SQLException e) {
	        System.err.println("Got an exception!");
	        System.err.println(e.getMessage());
	        return null;
	    }
	}
	
	public List<EquipmentEntity> getAllEquipmentNoUse() throws SQLException {
		try {
			Statement stm = DB.getConnection().createStatement();
			String insert_sqlString = "select equipmentId from room_equipment";
			ResultSet res = stm.executeQuery(insert_sqlString);
			String sql = "SELECT * FROM equipment WHERE id NOT IN (";
            while (res.next()) {
//            	System.out.println(roomId + "-" + res.getString("equipmentId"));
                sql += "'" + res.getString("equipmentId") + "'";
                sql += ",";
            }
//            System.out.println(sql.charAt(1));
            if (sql.charAt(sql.length()-1 ) == ',') {
            	sql = sql.substring(0,sql.length()-1);
            }
            sql += ")";
//            System.out.println(sql);
            if (sql.charAt(sql.length()-2)!='(') {            	
            	res = stm.executeQuery(sql);
            }
			ArrayList<EquipmentEntity> medium = new ArrayList<>();
			while (res.next()) {
				EquipmentEntity equipment = new EquipmentEntity(res.getInt("equipmentCategoryId"), res.getString("id"),
						res.getString("name"),
						res.getInt("status"), res.getDate("mfg"), res.getDate("yearOfUse"),
						res.getInt("numberOfRepairs"),
						res.getString("note"));
				medium.add(equipment);
			}
			return medium;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}
	public int getEquipmentCategoryId() {
		return equipmentCategoryId;
	}

	public void setEquipmentCategoryId(int equipmentCategoryId) {
		this.equipmentCategoryId = equipmentCategoryId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getMfg() {
		return mfg;
	}

	public void setMfg(Date mfg) {
		this.mfg = mfg;
	}

	public Date getYearOfUse() {
		return yearOfUse;
	}

	public void setYearOfUse(Date yearOfUse) {
		this.yearOfUse = yearOfUse;
	}

	public int getNumberOfRepairs() {
		return numberOfRepairs;
	}

	public void setNumberOfRepairs(int numberOfRepairs) {
		this.numberOfRepairs = numberOfRepairs;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public List<EquipmentEntity> getAll() throws SQLException {
		try {
			Statement stm = DB.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select * from equipment");
			ArrayList<EquipmentEntity> medium = new ArrayList<>();
			while (res.next()) {
				EquipmentEntity equipment = new EquipmentEntity(res.getInt("equipmentCategoryId"), res.getString("id"),
						res.getString("name"),
						res.getInt("status"), res.getDate("mfg"), res.getDate("yearOfUse"),
						res.getInt("numberOfRepairs"),
						res.getString("note"));
				medium.add(equipment);
			}
			return medium;
		} catch (SQLException e) {
			// TODO: handle exception
			return null;
		}

	}

	@Override
	public boolean delete() throws SQLException {
		// TODO Auto-generated method stub
		try {
			String insert_sqlString = "DELETE FROM equipment WHERE id = ?";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
			preparedStmt.setString(1, this.id);
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
			String insert_sqlString = "UPDATE equipment SET equipmentCategoryId = ?, id = ?, name = ?, status = ?, mfg = ?, yearOfUse = ?, numberOfRepairs = ?, note = ? WHERE id = ?";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
			preparedStmt.setInt(1, this.equipmentCategoryId);
			preparedStmt.setString(2, this.id);
			preparedStmt.setString(3, this.name);
			preparedStmt.setInt(4, this.status);
			preparedStmt.setDate(5, this.mfg);
			preparedStmt.setDate(6, this.yearOfUse);
			preparedStmt.setInt(7, this.numberOfRepairs);
			preparedStmt.setString(8, this.note);
			preparedStmt.setString(9, this.id);
			preparedStmt.execute();
			return true;
		} catch (SQLException e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
			return false;
		}

	}
	public void displayEquipment() {
		System.out.print(this.getEquipmentCategoryId());
		System.out.print(this.getId());
		System.out.print(this.getName());
	}
	public static void main(String[] args) {
		try {
//			System.out.println(new EquipmentEntity().getAllEquipmentInRoom(2).get(0).getName());
			System.out.println(new EquipmentEntity().getAllEquipmentNoUse().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
