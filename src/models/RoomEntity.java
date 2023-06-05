package models;

import java.sql.SQLException;
import java.util.List;



public class RoomEntity extends BaseEntity {
	private boolean status;
	private List<EquipmentEntity> listEquipment;
	public RoomEntity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<RoomEntity> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save() throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
