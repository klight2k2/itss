package views.room;

import java.sql.Date;

import models.EquipmentEntity;

public class Equipment extends EquipmentEntity {
	private String displayId;
	private String displayName;
	private String displayStatus;
	private Date displayTimeUse;
	private Date displayMfg;
	private Integer displayTimeRepair;
	private String displayNote;

	public Equipment() {

	}

	public String getDisplayId() {
		return displayId;
	}

	public void setDisplayId(String displayId) {
		this.displayId = displayId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayStatus() {
		return displayStatus;
	}

	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}

	public Date getDisplayTimeUse() {
		return displayTimeUse;
	}

	public void setDisplayTimeUse(Date displayTimeUse) {
		this.displayTimeUse = displayTimeUse;
	}

	public Date getDisplayMfg() {
		return displayMfg;
	}

	public void setDisplayMfg(Date displayMfg) {
		this.displayMfg = displayMfg;
	}

	public Integer getDisplayTimeRepair() {
		return displayTimeRepair;
	}

	public void setDisplayTimeRepair(Integer displayTimeRepair) {
		this.displayTimeRepair = displayTimeRepair;
	}

	public String getDisplayNote() {
		return displayNote;
	}

	public void setDisplayNote(String displayNote) {
		this.displayNote = displayNote;
	}
}
