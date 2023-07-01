package views.room;

import models.RoomEntity;

public class Room extends RoomEntity {
	private Integer displayId;
	private String displayName;
	private String displayStatus;
	private Integer numsOfEquipments;
	private Integer numsOfReports;

	public Room() {
		this.setNumsOfEquipments(20);
		this.setNumsOfReports(10);
	}

	public String getDisplayStatus() {
		return displayStatus;
	}

	public void setDisplayStatus() {
		this.displayStatus = this.isStatus() ? "Đang sử dụng" : "Không sử dụng";
	}

	public Integer getNumsOfEquipments() {
		return numsOfEquipments;
	}

	public void setNumsOfEquipments(Integer numsOfEquipments) {
		this.numsOfEquipments = numsOfEquipments;
	}

	public Integer getNumsOfReports() {
		return numsOfReports;
	}

	public void setNumsOfReports(Integer numsOfReports) {
		this.numsOfReports = numsOfReports;
	}

	public Integer getDisplayId() {
		return displayId;
	}

	public void setDisplayId(Integer displayId) {
		this.displayId = displayId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}