package models;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class RoomScheduleEntity extends BaseEntity{
	private UserEntity teacher;
	private RoomEntity room;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String reason;
	@Override
	public List getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void save() throws SQLException {
		// TODO Auto-generated method stub
		
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
