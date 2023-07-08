package views.schedule;

import java.time.LocalDateTime;

import models.RoomScheduleEntity;

public class Schedule extends RoomScheduleEntity {
	private Integer displayId;
	private String displayRoom;
	private String displayUser;
	private LocalDateTime displayStartTime;
	private LocalDateTime displayEndTime;
	private String displayReason;
	private String displayStatus;

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

	public LocalDateTime getDisplayStartTime() {
		return displayStartTime;
	}

	public void setDisplayStartTime(LocalDateTime displayStartTime) {
		this.displayStartTime = displayStartTime;
	}

	public LocalDateTime getDisplayEndTime() {
		return displayEndTime;
	}

	public void setDisplayEndTime(LocalDateTime displayEndTime) {
		this.displayEndTime = displayEndTime;
	}

	public String getDisplayReason() {
		return displayReason;
	}

	public void setDisplayReason(String displayReason) {
		this.displayReason = displayReason;
	}

	public String getDisplayStatus() {
		return displayStatus;
	}

	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}
}
