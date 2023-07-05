package models;

import java.sql.Date;

public class RoomScheduleEntity extends BaseEntity {
	private int id;
	private int teacherId;
	private int roomId;
	private Date startTime;
	private Date endTime;
	private String reason;

	private UserEntity teacher = new UserEntity();

	public UserEntity getTeacher() {
		return teacher;
	}

	public void setTeacher(UserEntity teacher) {
		this.teacher = teacher;
	}

	public RoomScheduleEntity(int teacherId, int roomId, Date startTime, Date endTime, String reason,
			UserEntity teacher) {
		super();
		this.teacherId = teacherId;
		this.roomId = roomId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.reason = reason;
		this.teacher = teacher;
	}

	public RoomScheduleEntity(int id, int teacherId, int roomId, Date startTime, Date endTime, String reason) {
		this.id = id;
		this.teacherId = teacherId;
		this.roomId = roomId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.reason = reason;
	}

	public RoomScheduleEntity() {
		// Default constructor
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
