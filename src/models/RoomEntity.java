package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.RoomService;

public class RoomEntity extends BaseEntity {
	private int id;
	private boolean status;
	private String name;
	public List<EquipmentEntity> listEquipment;
	public List<RoomScheduleEntity> listRoomSchedule;
	public List<RoomReportEntity> listRoomReport;

	public List<RoomReportEntity> getListRoomReport() {
		return listRoomReport;
	}

	public void setListRoomReport(List<RoomReportEntity> listRoomReport) {
		this.listRoomReport = listRoomReport;
	}

	public List<RoomScheduleEntity> getListRoomSchedule() {
		return listRoomSchedule;
	}

	public void setListRoomSchedule(List<RoomScheduleEntity> listRoomSchedule) {
		this.listRoomSchedule = listRoomSchedule;
	}

	public RoomEntity(boolean status, String name, List<EquipmentEntity> listEquipment,
			List<RoomScheduleEntity> listRoomSchedule, List<RoomReportEntity> listRoomReport) {
		super();
		this.status = status;
		this.name = name;
		this.listEquipment = listEquipment;
		this.listRoomSchedule = listRoomSchedule;
		this.listRoomReport = listRoomReport;
	}

	public RoomEntity(int id, boolean status, String name) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public RoomEntity() {
		// TODO Auto-generated constructor stub
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EquipmentEntity> getListEquipment() {
		return listEquipment;
	}

	public void setListEquipment(List<EquipmentEntity> listEquipment) {
		this.listEquipment = listEquipment;
	}

	public static void main(String[] args) {
		try {
			List<RoomEntity> list = new RoomService().getAll();
			for (RoomEntity roomEntity : list) {
				System.out.println(roomEntity.getName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
