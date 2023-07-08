package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RoomReportEntity extends BaseEntity {
	private int id;
	private int roomId;
	private String status;
	private java.sql.Date createdAt;
	private int reporterId;
	private int approverId;
	private String note;

	private List<EquipmentEntity> listEquipmentReport = new ArrayList<>();

	public List<EquipmentEntity> getListEquipmentReport() {
		return this.listEquipmentReport;
	}

	public void setListEquipmentReport(List<EquipmentEntity> listEquipmentReport) {
		this.listEquipmentReport = listEquipmentReport;
	}

	public RoomReportEntity() {
		// Default constructor
	}

	public RoomReportEntity(int roomId, String status, Date createdAt, int reporterId, int approverId,
			List<EquipmentEntity> listEquipmentReport) {
		super();
		this.roomId = roomId;
		this.status = status;
		this.createdAt = createdAt;
		this.reporterId = reporterId;
		this.approverId = approverId;
		this.listEquipmentReport = listEquipmentReport;
	}

	public RoomReportEntity(int id, int roomId, String status, Date createdAt, int reporterId, int approverId,
			List<EquipmentEntity> listEquipmentReport) {
		super();
		this.id = id;
		this.roomId = roomId;
		this.status = status;
		this.createdAt = createdAt;
		this.reporterId = reporterId;
		this.approverId = approverId;
		this.listEquipmentReport = listEquipmentReport;
	}

	public RoomReportEntity(int id, int roomId, String status, java.sql.Date createdAt, int reporterId, int approverId,
			String note) {
		this.id = id;
		this.roomId = roomId;
		this.status = status;
		this.createdAt = createdAt;
		this.reporterId = reporterId;
		this.approverId = approverId;
		this.note = note;
	}

	// Getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.sql.Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(java.sql.Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getReporterId() {
		return reporterId;
	}

	public void setReporterId(int reporterId) {
		this.reporterId = reporterId;
	}

	public int getApproverId() {
		return approverId;
	}

	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
