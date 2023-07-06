package models;

import java.sql.Date;
import java.util.List;

enum PayBorrowStatus {
	PENDING,
	BORROWING,
	PAID

}

public class PayBorrowEntity extends BaseEntity {
	private int id;
	private java.sql.Date fromDate;
	private java.sql.Date toDate;
	private String status;
	private String borrowReason;
	private String refuseReason;
	private int borrowerId;
	private List<EquipmentEntity> listEquipment;

	public  List<EquipmentEntity> getListEquipment() {
		return listEquipment;
	}
	public void setListEquipment(List<EquipmentEntity> listEquipment) {
		this.listEquipment = listEquipment;
	}

	public PayBorrowEntity(int id, java.sql.Date fromDate, java.sql.Date toDate, String status,
			String borrowReason, String refuseReason, int borrowerId) {
		this.id = id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.status = status;
		this.borrowReason = borrowReason;
		this.refuseReason = refuseReason;
		this.borrowerId = borrowerId;
	}

	public PayBorrowEntity(Date fromDate, Date toDate, String status, String borrowReason, String refuseReason,
			int borrowerId) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.status = status;
		this.borrowReason = borrowReason;
		this.refuseReason = refuseReason;
		this.borrowerId = borrowerId;

	}

	public PayBorrowEntity() {
		// Default constructor
	}

	// Getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(java.sql.Date fromDate) {
		this.fromDate = fromDate;
	}

	public java.sql.Date getToDate() {
		return toDate;
	}

	public void setToDate(java.sql.Date toDate) {
		this.toDate = toDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBorrowReason() {
		return borrowReason;
	}

	public void setBorrowReason(String borrowReason) {
		this.borrowReason = borrowReason;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public int getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(int borrowerId) {
		this.borrowerId = borrowerId;
	}
}
