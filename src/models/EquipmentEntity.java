package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.db.DB;

public class EquipmentEntity extends BaseEntity {
	private int quantity ;
	private int goodStatus;

	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getGoodStatus() {
		return goodStatus;
	}

	public void setGoodStatus(int goodStatus) {
		this.goodStatus = goodStatus;
	}
	@Override
	  public void save() throws SQLException {
	        Statement stm = DB.getConnection().createStatement();
//			logic to save equipment
	        
//	        if equipment not exist, add equipment to db
	  }

	@Override
	public List<EquipmentEntity> getAll() throws SQLException {
		Statement stm = DB.getConnection().createStatement();
		ResultSet res = stm.executeQuery("select * from Equipment");
		ArrayList<EquipmentEntity> medium = new ArrayList<>();
		while (res.next()) {
			EquipmentEntity equipment = new EquipmentEntity();
			medium.add(equipment);
		}
		return medium;
	}

}
