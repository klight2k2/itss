package controllers;

import java.util.ArrayList;
import java.util.List;

import models.RoomEntity;

public class RoomController {
	private List<RoomEntity> list_roomEntities = new ArrayList<RoomEntity>();

	public List<RoomEntity> getList_roomEntities() {
		return list_roomEntities;
	}

	public void setList_roomEntities(List<RoomEntity> list_roomEntities) {
		this.list_roomEntities = list_roomEntities;
	}
	
}
