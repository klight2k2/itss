package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.EquipmentEntity;

public class EquipmentController {
	public EquipmentController() {
		// TODO Auto-generated constructor stub
	}
	
	public List<EquipmentEntity> getAllEquipment() throws SQLException {
		return new EquipmentEntity().getAll();
	}

}
