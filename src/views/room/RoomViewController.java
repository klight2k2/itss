package views.room;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.EquipmentEntity;
import models.RoomEntity;
import models.RoomReportEntity;

public class RoomViewController {

	@FXML
	private AnchorPane addModal;

	@FXML
	private AnchorPane detailModal;

	@FXML
	private AnchorPane confirmDeleteModal;

	@FXML
	private Button deleteEquipmentBtn;

	@FXML
	private Label modalTitle;

	@FXML
	private TextField inputSearch;

	@FXML
	private TableView<Room> rooms;

	@FXML
	private TableColumn<Room, Integer> roomId;

	@FXML
	private TableColumn<Room, String> roomName;

	@FXML
	private TableColumn<Room, String> roomStatus;

	@FXML
	private TableColumn<Room, Integer> roomEquipments;

	@FXML
	private TableColumn<Room, Integer> roomReports;

	@FXML
	private TextField inputRoomName;

	@FXML
	private ChoiceBox<Boolean> inputRoomStatus;

	private Integer inputId;

	private String inputEquipId;

	private Boolean[] status = { true, false };

	@FXML
	private Label detailRoomName;

	@FXML
	private Label detailRoomStatus;

	@FXML
	private TableView<Equipment> equipments;

	@FXML
	private TableColumn<Equipment, String> equipId;

	@FXML
	private TableColumn<Equipment, Date> equipMfg;

	@FXML
	private TableColumn<Equipment, String> equipName;

	@FXML
	private TableColumn<Equipment, String> equipNote;

	@FXML
	private TableColumn<Equipment, Integer> equipRepairTime;

	@FXML
	private TableColumn<Equipment, String> equipStatus;

	@FXML
	private TableColumn<Equipment, Date> equipTimeUse;

	public String convertStatus(int status) {
		return status == 0 ? "Hỏng" : status == 1 ? "Tốt" : "Đang sửa";
	}

	void openModal(String title) {
		String displayTitle = title == null ? "Thêm phòng mới" : title;
		modalTitle.setText(displayTitle);
		addModal.setVisible(true);
	}

	@FXML
	void closeModal() {
		addModal.setVisible(false);
	}

	@FXML
	void closeDetailModal() {
		detailModal.setVisible(false);
		equipments.getItems().clear();
	}

	@FXML
	void handleOpenModal(ActionEvent event) {
		inputId = null;
		inputRoomName.clear();
		openModal(null);
	}

	String convertRoomStatus(Boolean status) {
		return status ? "Đang sử dụng" : "Không sử dụng";
	}

	Boolean convertRoomStatus(String status) {
		return status == "Đang sử dụng" ? true : false;
	}

	@FXML
	void submit(ActionEvent event) {
		String name = inputRoomName.getText();
		Boolean status = inputRoomStatus.getValue();

		System.out.println("Edit id: " + inputId);

		detailRoomName.setText(name);
		detailRoomStatus.setText(convertRoomStatus(status));

		if (inputId != null) {
			try {
				List<RoomEntity> roometts = new RoomEntity().getAll();
				for (RoomEntity room : roometts) {
					if (room.getId() == inputId.intValue()) {
						room.setName(name);
						room.setStatus(status);
						room.update();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Room newRoom = new Room();
			newRoom.setId(rooms.getItems().size() + 1);
			newRoom.setName(name);
			newRoom.setStatus(status);
			newRoom.setDisplayStatus();
			newRoom.setNumsOfEquipments(10);
//			newRoom.setNumsOfReports(5);

			try {
				newRoom.save();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		closeModal();
		updateTable();
	}

	@FXML
	void search(ActionEvent event) {
		String name = inputSearch.getText();
		if (name == null || name.isEmpty())
			return;

		String filter = String.valueOf(name).trim();

		try {
			Room room = new Room();
			List<RoomEntity> roomsEntity = room.getAll();

			rooms.getItems().clear();

			for (RoomEntity roomEntity : roomsEntity) {
				if (roomEntity.getName().contains(filter)) {
					Room newRoom = new Room();
					newRoom.setDisplayId(roomEntity.getId());
					newRoom.setDisplayName(roomEntity.getName());
					newRoom.setStatus(roomEntity.isStatus());
					newRoom.setDisplayStatus();
					newRoom.setNumsOfEquipments(roomEntity.getListEquipment().size());
					newRoom.setNumsOfReports(
							new RoomReportEntity().getAllRoomReportByRoomId(roomEntity.getId()).size());

					rooms.getItems().add(newRoom);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void clearSearch() {
		inputSearch.clear();
		updateTable();
	}

	void initEquipTable() {
		equipId.setCellValueFactory(new PropertyValueFactory<Equipment, String>("displayId"));
		equipName.setCellValueFactory(new PropertyValueFactory<Equipment, String>("displayName"));
		equipStatus.setCellValueFactory(new PropertyValueFactory<Equipment, String>("displayStatus"));
		equipTimeUse.setCellValueFactory(new PropertyValueFactory<Equipment, Date>("displayTimeUse"));
		equipMfg.setCellValueFactory(new PropertyValueFactory<Equipment, Date>("displayMfg"));
		equipRepairTime.setCellValueFactory(new PropertyValueFactory<Equipment, Integer>("displayTimeRepair"));
		equipNote.setCellValueFactory(new PropertyValueFactory<Equipment, String>("displayNote"));
	}

	void updateEquipTable() {
		try {
			int roomId = inputId.intValue();
			List<EquipmentEntity> equipmentsEntity = new EquipmentEntity().getAllEquipmentInRoom(roomId);
			if (equipmentsEntity == null)
				return;
			equipments.getItems().clear();
			for (EquipmentEntity equip : equipmentsEntity) {
				Equipment newEquip = new Equipment();
				newEquip.setDisplayId(equip.getId());
				newEquip.setDisplayName(equip.getName());
				newEquip.setDisplayStatus(convertStatus(equip.getStatus()));
				newEquip.setDisplayMfg(equip.getMfg());
				newEquip.setDisplayTimeUse(equip.getYearOfUse());
				newEquip.setDisplayTimeRepair(equip.getNumberOfRepairs());
				newEquip.setDisplayNote(equip.getNote());
				equipments.getItems().add(newEquip);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void editRoom(ActionEvent event) {
		openModal("Chỉnh sửa phòng");
	}

	// Delete room modal
	@FXML
	void confirmDeleteRoom(ActionEvent event) {
		try {
			Room room = new Room();
			for (RoomEntity delRoom : room.getAll()) {
				if (delRoom.getId() == inputId.intValue()) {
					delRoom.delete();
					confirmDeleteModal.setVisible(false);
					closeDetailModal();
					updateTable();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void cancelDeleteRoom(ActionEvent event) {
		confirmDeleteModal.setVisible(false);
	}

	@FXML
	void deleteRoom(ActionEvent event) {
		confirmDeleteModal.setVisible(true);
	}

	@FXML
	void deleteEquipment(ActionEvent event) {
		try {
			Equipment equip = new Equipment();
			for (EquipmentEntity e : equip.getAll()) {
				if (e.getId().equals(inputEquipId)) {
					e.delete();
					break;
				}
			}
			deleteEquipmentBtn.setVisible(false);
			updateEquipTable();
			updateTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void rowEquipmentClicked(MouseEvent event) {
		Equipment clickedEquip = equipments.getSelectionModel().getSelectedItem();
		if (clickedEquip == null)
			return;

		inputEquipId = String.valueOf(clickedEquip.getDisplayId());

		deleteEquipmentBtn.setVisible(true);
	}

	@FXML
	void rowClicked(MouseEvent event) {
		Room clickedRoom = rooms.getSelectionModel().getSelectedItem();
		if (clickedRoom == null)
			return;

		inputId = Integer.valueOf(clickedRoom.getDisplayId());
		inputRoomName.setText(String.valueOf(clickedRoom.getDisplayName()));
		inputRoomStatus.setValue(Boolean.valueOf(clickedRoom.isStatus()));

		detailRoomName.setText(String.valueOf(clickedRoom.getDisplayName()));
		detailRoomStatus.setText(String.valueOf(clickedRoom.getDisplayStatus()));

		updateEquipTable();

		detailModal.setVisible(true);
	}

	private void updateTable() {
		try {
			Room room = new Room();
			List<RoomEntity> roomsEntity = room.getAll();

			rooms.getItems().clear();

			for (RoomEntity roomEntity : roomsEntity) {
				Room newRoom = new Room();
				newRoom.setDisplayId(roomEntity.getId());
				newRoom.setDisplayName(roomEntity.getName());
				newRoom.setStatus(roomEntity.isStatus());
				newRoom.setDisplayStatus();
				List<EquipmentEntity> equipments = roomEntity.getListEquipment();
				newRoom.setNumsOfEquipments(equipments != null ? equipments.size() : 0);
				newRoom.setNumsOfReports(new RoomReportEntity().getAllRoomReportByRoomId(roomEntity.getId()).size());

				rooms.getItems().add(newRoom);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initialize() {
		inputRoomStatus.getItems().addAll(status);
		addModal.setVisible(false);
		detailModal.setVisible(false);
		confirmDeleteModal.setVisible(false);
		deleteEquipmentBtn.setVisible(false);

		roomId.setCellValueFactory(new PropertyValueFactory<Room, Integer>("displayId"));
		roomName.setCellValueFactory(new PropertyValueFactory<Room, String>("displayName"));
		roomStatus.setCellValueFactory(new PropertyValueFactory<Room, String>("displayStatus"));
		roomEquipments.setCellValueFactory(new PropertyValueFactory<Room, Integer>("numsOfEquipments"));
		roomReports.setCellValueFactory(new PropertyValueFactory<Room, Integer>("numsOfReports"));
		initEquipTable();
		updateTable();
	}
}
