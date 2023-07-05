package views.schedule;

import java.sql.Date;

import models.RoomScheduleEntity;

public class Schedule extends RoomScheduleEntity {
	private Integer displayId;
	private String displayRoom;
	private String displayUser;
	private Date displayStartTime;
	private Date displayEndTime;
	private String displayReason;

	public Integer getDisplayId() {
		return displayId;
	}

	public void setDisplayId(Integer displayId) {
		this.displayId = displayId;
	}

	public String getDisplayRoom() {
		return displayRoom;
	}

	public void setDisplayRoom(String displayRoom) {
		this.displayRoom = displayRoom;
	}

	public String getDisplayUser() {
		return displayUser;
	}

	public void setDisplayUser(String displayUser) {
		this.displayUser = displayUser;
	}

	public Date getDisplayStartTime() {
		return displayStartTime;
	}

	public void setDisplayStartTime(Date displayStartTime) {
		this.displayStartTime = displayStartTime;
	}

	public Date getDisplayEndTime() {
		return displayEndTime;
	}

	public void setDisplayEndTime(Date displayEndTime) {
		this.displayEndTime = displayEndTime;
	}

	public String getDisplayReason() {
		return displayReason;
	}

	public void setDisplayReason(String displayReason) {
		this.displayReason = displayReason;
	}
}
