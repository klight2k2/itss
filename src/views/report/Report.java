package views.report;

import java.sql.Date;

import models.RoomReportEntity;

public class Report extends RoomReportEntity {
	private Integer displayId;
	private String displayRoom;
	private String displayEquipment;
	private String displayStatus;
	private String displayReporter;
	private Date displayTimestamp;

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

	public String getDisplayEquipment() {
		return displayEquipment;
	}

	public void setDisplayEquipment(String displayEquipment) {
		this.displayEquipment = displayEquipment;
	}

	public String getDisplayStatus() {
		return displayStatus;
	}

	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}

	public String getDisplayReporter() {
		return displayReporter;
	}

	public void setDisplayReporter(String displayReporter) {
		this.displayReporter = displayReporter;
	}

	public Date getDisplayTimestamp() {
		return displayTimestamp;
	}

	public void setDisplayTimestamp(Date displayTimestamp) {
		this.displayTimestamp = displayTimestamp;
	}
}
