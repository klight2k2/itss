package views.payborrow;

import java.sql.Date;

import models.PayBorrowEntity;

public class PayBorrow extends PayBorrowEntity {
	private Integer displayId;
	private String displayUsername;
	private String displayStatus;
	private Date displayFromDate;
	private Date displayToDate;
	private String displayBorrowReason;
	private String displayRefuseReason;

	public Integer getDisplayId() {
		return displayId;
	}

	public void setDisplayId(Integer displayId) {
		this.displayId = displayId;
	}

	public String getDisplayUsername() {
		return displayUsername;
	}

	public void setDisplayUsername(String displayUsername) {
		this.displayUsername = displayUsername;
	}

	public String getDisplayStatus() {
		return displayStatus;
	}

	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}

	public Date getDisplayFromDate() {
		return displayFromDate;
	}

	public void setDisplayFromDate(Date displayFromDate) {
		this.displayFromDate = displayFromDate;
	}

	public Date getDisplayToDate() {
		return displayToDate;
	}

	public void setDisplayToDate(Date displayToDate) {
		this.displayToDate = displayToDate;
	}

	public String getDisplayBorrowReason() {
		return displayBorrowReason;
	}

	public void setDisplayBorrowReason(String displayBorrowReason) {
		this.displayBorrowReason = displayBorrowReason;
	}

	public String getDisplayRefuseReason() {
		return displayRefuseReason;
	}

	public void setDisplayRefuseReason(String displayRefuseReason) {
		this.displayRefuseReason = displayRefuseReason;
	}
}
