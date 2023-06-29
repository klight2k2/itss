package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import models.db.DB;

public class EquipmentEntity extends BaseEntity {
	private LocalDateTime fromDate ;
	private LocalDateTime toDate ;
	private LocalDateTime mfg ;
	private String note ;
	private int numberOfRepairs;
	private int status;
	private String equipmentCategory;

	


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

	@Override
	public void delete() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
