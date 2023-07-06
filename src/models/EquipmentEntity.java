package models;

import java.sql.Date;

public class EquipmentEntity extends BaseEntity {
	private int equipmentCategoryId;
	private String id;
	private String name;
	private int status;
	private Date mfg;
	private Date yearOfUse;
	private int numberOfRepairs;
	private String note;
	private int roomId;


	public EquipmentEntity() {
		super();
	}

	public EquipmentEntity(int equipmentCategoryId, String name, int status, Date mfg, Date yearOfUse,
			int numberOfRepairs, String note,int roomId) {
		super();
		this.equipmentCategoryId = equipmentCategoryId;
		this.name = name;
		this.status = status;
		this.mfg = mfg;
		this.yearOfUse = yearOfUse;
		this.numberOfRepairs = numberOfRepairs;
		this.note  = note;
		this.setRoomId(roomId);
	}

	public EquipmentEntity(int equipmentCategoryId, String id, String name, int status, Date mfg,
			Date yearOfUse, int numberOfRepairs, String note,int roomId) {
		super();
		this.equipmentCategoryId = equipmentCategoryId;
		this.id = id;
		this.name = name;
		this.status = status;
		this.mfg = mfg;
		this.yearOfUse = yearOfUse;
		this.numberOfRepairs = numberOfRepairs;
		this.note = note;
		this.setRoomId(roomId);
	}

	public int getEquipmentCategoryId() {
		return equipmentCategoryId;
	}

	public void setEquipmentCategoryId(int equipmentCategoryId) {
		this.equipmentCategoryId = equipmentCategoryId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getMfg() {
		return mfg;
	}

	public void setMfg(Date mfg) {
		this.mfg = mfg;
	}

	public Date getYearOfUse() {
		return yearOfUse;
	}

	public void setYearOfUse(Date yearOfUse) {
		this.yearOfUse = yearOfUse;
	}

	public int getNumberOfRepairs() {
		return numberOfRepairs;
	}

	public void setNumberOfRepairs(int numberOfRepairs) {
		this.numberOfRepairs = numberOfRepairs;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void displayEquipment() {
		System.out.print(this.getEquipmentCategoryId());
		System.out.print(this.getId());
		System.out.print(this.getName());
	}

	@Override
	public boolean equals(Object obj){
		EquipmentEntity entity =(EquipmentEntity) obj;
		if(entity.id==this.id) return true;
		return false;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

}
