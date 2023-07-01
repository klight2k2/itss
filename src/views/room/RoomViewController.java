package views.room;

import java.sql.SQLException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class RoomViewController {

	@FXML
	private AnchorPane addModal;

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

//	@FXML
//	private TableColumn<Room, Integer> roomReports;

	@FXML
	private TextField inputRoomName;

	@FXML
	private ChoiceBox<Boolean> inputRoomStatus;

	private Integer inputId;

	private Boolean[] status = { true, false };

	void openModal(String title) {
		String displayTitle = title == null ? "Thêm phòng mới" : title;
		modalTitle.setText(displayTitle);
		addModal.setVisible(true);
	}

	@FXML
	void closeModal() {
		addModal.setVisible(false);
		inputId = null;
		inputRoomName.setText(null);
		inputRoomStatus.setValue(null);
	}

	@FXML
	void handleOpenModal(ActionEvent event) {
		openModal(null);
	}

	@FXML
	void submit(ActionEvent event) {
		String name = inputRoomName.getText();
		Boolean status = inputRoomStatus.getValue();

		System.out.println("Edit id: " + inputId);

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
					// newRoom.setNumsOfReports(roomEntity.get);

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

	@FXML
	void rowClicked(MouseEvent event) {
		Room clickedRoom = rooms.getSelectionModel().getSelectedItem();
		if (clickedRoom == null)
			return;
		inputId = Integer.valueOf(clickedRoom.getDisplayId());
		System.out.println("Clicked id: " + inputId);
		inputRoomName.setText(String.valueOf(clickedRoom.getDisplayName()));
		inputRoomStatus.setValue(Boolean.valueOf(clickedRoom.isStatus()));
		openModal("Chỉnh sửa phòng");
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
//				newRoom.setNumsOfReports(roomEntity.get);

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

		roomId.setCellValueFactory(new PropertyValueFactory<Room, Integer>("displayId"));
		roomName.setCellValueFactory(new PropertyValueFactory<Room, String>("displayName"));
		roomStatus.setCellValueFactory(new PropertyValueFactory<Room, String>("displayStatus"));
		roomEquipments.setCellValueFactory(new PropertyValueFactory<Room, Integer>("numsOfEquipments"));
//		roomReports.setCellValueFactory(new PropertyValueFactory<Room, Integer>("numsOfReports"));
		updateTable();
	}
}
