package controllers;

import java.sql.Date;
import java.sql.SQLException;

import common.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import models.CategoryEquipmentEntity;
import models.EquipmentEntity;
import models.RoomEntity;
import service.CategoryEquipmentService;
import service.EquipmentService;
import service.RoomService;
import service.UserService;
import views.equipment.Equipment;

public class EquipmentViewController {

	@FXML
	private Button addEquipmentBtn;

	@FXML
	private AnchorPane addEquipmentModal;

	@FXML
	private TableView<Equipment> equipments;

	@FXML
	private TableColumn<Equipment, String> equipId;

	@FXML
	private TableColumn<Equipment, Date> equipMfg;

	@FXML
	private TableColumn<Equipment, String> equipName;

	@FXML
	private TableColumn<Equipment, String> equipCategory;

	@FXML
	private TableColumn<Equipment, String> equipNote;

	@FXML
	private TableColumn<Equipment, Integer> equipRepairTime;

	@FXML
	private TableColumn<Equipment, String> equipRoom;

	@FXML
	private TableColumn<Equipment, String> equipStatus;

	@FXML
	private TableColumn<Equipment, Date> equipTimeUse;

	@FXML
	private TableColumn<Equipment, String> operationEquipColumn;

	@FXML
	private DatePicker inputEquipMfg;

	@FXML
	private TextArea inputEquipNote;

	@FXML
	private TextField inputEquipRepairTime;

	@FXML
	private ComboBox<CategoryEquipmentEntity> inputEquipCategory;

	@FXML
	private ComboBox<String> inputEquipStatus;

	@FXML
	private DatePicker inputEquipYearUse;

	@FXML
	private TextField inputSearch;

	@FXML
	private TextField inputEquipName;

	@FXML
	private Label inputTitle;

	// @FXML
	// private Button deleteBtn;

	@FXML
	private ComboBox<RoomEntity> inputEquipRoom;

	private String curId;

	private ObservableList<RoomEntity> listRoom = FXCollections.observableArrayList();
	private ObservableList<CategoryEquipmentEntity> listCategory = FXCollections.observableArrayList();

	public String convertStatus(int status) {
		return status == 0 ? "Hỏng" : status == 1 ? "Tốt" : "Đang sửa";
	}

	public int convertStatus(String status) {
		return status == "Hỏng" ? 0 : status == "Tốt" ? 1 : status == "Đang sửa" ? 2 : -1;
	}

	@FXML
	void onRowClick(MouseEvent event) {
		if (!UserService.getRepo().isAdmin())
			return;
		Equipment clickedRow = equipments.getSelectionModel().getSelectedItem();
		if (clickedRow == null)
			return;
		curId = clickedRow.getDisplayId();
		System.out.println("clicked id" + clickedRow.getDisplayId());
		// deleteBtn.setVisible(true);
		inputTitle.setText("Chỉnh sửa");
		for (CategoryEquipmentEntity item : listCategory) {
			if (item.getId() == clickedRow.getEquipmentCategoryId()) {
				// borrowerCombobox.setV
				inputEquipCategory.setValue(item);
				System.out.println("success");
			}

		}
		for (RoomEntity item : listRoom) {
			if (item.getId() == clickedRow.getRoomId()) {
				// borrowerCombobox.setV
				inputEquipRoom.setValue(item);
				System.out.println("success");
			}

		}
		System.out.println("number cate" + clickedRow.getEquipmentCategoryId());
		inputEquipName.setText(clickedRow.getDisplayName());
		inputEquipStatus.setValue(clickedRow.getDisplayStatus());
		inputEquipMfg.setValue(clickedRow.getDisplayMfg().toLocalDate());
		inputEquipYearUse.setValue(clickedRow.getDisplayTimeUse().toLocalDate());
		inputEquipRepairTime.setText(clickedRow.getDisplayTimeRepair().toString());
		inputEquipNote.setText(clickedRow.getDisplayNote());

		addEquipmentModal.setVisible(true);
	}

	@FXML
	void delete(ActionEvent event) {
		try {
			if (curId == null || curId.isEmpty())
				return;
			EquipmentService eq = EquipmentService.getRepo();
			EquipmentEntity curEquip = eq.getEquipmentById(curId);
			eq.delete(curEquip);
			curId = null;
			updateTable();
			addEquipmentModal.setVisible(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void closeModal(ActionEvent event) {
		addEquipmentModal.setVisible(false);
	}

	@FXML
	void openModal(ActionEvent event) {
		// deleteBtn.setVisible(false);
		inputTitle.setText("Thêm thiết bị");
		inputEquipName.clear();
		inputEquipCategory.setValue(null);
		inputEquipStatus.setValue(null);
		inputEquipMfg.setValue(null);
		inputEquipYearUse.setValue(null);
		inputEquipRepairTime.clear();
		inputEquipNote.clear();

		addEquipmentModal.setVisible(true);
	}

	@FXML
	void submit(ActionEvent event) {
		try {
			EquipmentService equipmentRepo = EquipmentService.getRepo();
			RoomService roomRepo = RoomService.getRepo();
			CategoryEquipmentService categoryEquipmentService = CategoryEquipmentService.getRepo();
			EquipmentEntity newEq = new EquipmentEntity();

			// for (RoomEntity room : roomRepo.getAll()) {
			// if (room.getName().equals(inputEquipRoom.getValue())) {
			//
			// }
			// }
			for (CategoryEquipmentEntity ce : categoryEquipmentService.getAll()) {
				if (ce.getName().equals(inputEquipCategory.getValue())) {
					newEq.setEquipmentCategoryId(ce.getId());
				}
			}
			newEq.setName(inputEquipName.getText());
			newEq.setStatus(convertStatus(inputEquipStatus.getValue()));
			newEq.setMfg(Date.valueOf(inputEquipMfg.getValue()));
			newEq.setYearOfUse(Date.valueOf(inputEquipYearUse.getValue()));
			newEq.setNumberOfRepairs(Integer.valueOf(inputEquipRepairTime.getText()).intValue());
			newEq.setNote(inputEquipNote.getText());
			newEq.setEquipmentCategoryId(inputEquipCategory.getSelectionModel().getSelectedItem().getId());
			newEq.setRoomId(inputEquipRoom.getSelectionModel().getSelectedItem().getId());
			System.out.println("id" + curId);
			if (curId == null || curId.isEmpty()) {
				newEq.setId("Unknown" + equipmentRepo.getAll().size());
				equipmentRepo.save(newEq);
			} else {
				newEq.setId(curId);
				equipmentRepo.update(newEq);
			}

			addEquipmentModal.setVisible(false);
			updateTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void reset(ActionEvent event) {
		inputSearch.clear();
		updateTable();
	}

	@FXML
	void search(ActionEvent event) {
		String name = inputSearch.getText();
		if (name == null || name.isEmpty())
			return;

		String filter = String.valueOf(name).trim().toLowerCase();

		equipments.getItems().clear();
		EquipmentService equipmentRepo = EquipmentService.getRepo();
		CategoryEquipmentService categoryEquipmentService = CategoryEquipmentService.getRepo();

		EquipmentEntity eqs = new EquipmentEntity();
		try {
			for (EquipmentEntity eq : equipmentRepo.getAll()) {
				if (eq.getName().toLowerCase().contains(filter)) {
					Equipment newEq = new Equipment();
					newEq.setDisplayId(eq.getId());
					newEq.setDisplayCategory(
							CategoryEquipmentService.getRepo().getCategoryById(eq.getEquipmentCategoryId()).getName());
					newEq.setDisplayRoom(RoomService.getRepo().getRoomById(eq.getRoomId()).getName());
					newEq.setId(eq.getId());
					newEq.setDisplayName(eq.getName());
					newEq.setDisplayStatus(convertStatus(eq.getStatus()));
					newEq.setDisplayTimeUse(eq.getYearOfUse());
					newEq.setDisplayMfg(eq.getMfg());
					newEq.setDisplayTimeRepair(eq.getNumberOfRepairs());
					newEq.setDisplayNote(eq.getNote());
					newEq.setRoomId(eq.getRoomId());
					newEq.setEquipmentCategoryId(eq.getEquipmentCategoryId());
					equipments.getItems().add(newEq);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateTable() {
		try {
			equipments.getItems().clear();
			EquipmentService equipmentRepo = EquipmentService.getRepo();
			for (EquipmentEntity eq : equipmentRepo.getAll()) {
				Equipment newEq = new Equipment();
				newEq.setDisplayId(eq.getId());
				newEq.setDisplayCategory(
						CategoryEquipmentService.getRepo().getCategoryById(eq.getEquipmentCategoryId()).getName());
				newEq.setDisplayRoom(RoomService.getRepo().getRoomById(eq.getRoomId()).getName());
				newEq.setId(eq.getId());
				newEq.setDisplayName(eq.getName());
				newEq.setDisplayStatus(convertStatus(eq.getStatus()));
				newEq.setDisplayTimeUse(eq.getYearOfUse());
				newEq.setDisplayMfg(eq.getMfg());
				newEq.setDisplayTimeRepair(eq.getNumberOfRepairs());
				newEq.setDisplayNote(eq.getNote());
				newEq.setRoomId(eq.getRoomId());
				newEq.setEquipmentCategoryId(eq.getEquipmentCategoryId());
				equipments.getItems().add(newEq);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initialize() {
		addEquipmentModal.setVisible(false);

		if (!UserService.getRepo().isAdmin()) {

			addEquipmentBtn.setVisible(false);
			operationEquipColumn.setVisible(false);
		}
		CategoryEquipmentService categoryEquipmentRepo = CategoryEquipmentService.getRepo();
		RoomService roomRepo = RoomService.getRepo();
		try {
			listRoom.setAll(roomRepo.getAll());
			inputEquipRoom.setItems(listRoom);
			listCategory.setAll(categoryEquipmentRepo.getAll());
			inputEquipCategory.setItems(listCategory);
			inputEquipStatus.getItems().addAll("Hỏng", "Tốt", "Đang sửa");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		equipId.setCellValueFactory(new PropertyValueFactory<>("displayId"));
		equipName.setCellValueFactory(new PropertyValueFactory<>("displayName"));
		equipCategory.setCellValueFactory(new PropertyValueFactory<>("displayCategory"));
		equipRoom.setCellValueFactory(new PropertyValueFactory<>("displayRoom"));
		equipStatus.setCellValueFactory(new PropertyValueFactory<>("displayStatus"));
		equipTimeUse.setCellValueFactory(new PropertyValueFactory<>("displayTimeUse"));
		equipMfg.setCellValueFactory(new PropertyValueFactory<>("displayMfg"));
		equipRepairTime.setCellValueFactory(new PropertyValueFactory<>("displayTimeRepair"));
		equipNote.setCellValueFactory(new PropertyValueFactory<>("displayNote"));

		updateTable();
		updateOperationEquip();
	}

	void updateOperationEquip() {
		Callback<TableColumn<Equipment, String>, TableCell<Equipment, String>> cellFoctory = (
				TableColumn<Equipment, String> param) -> {
			// make cell containing buttons
			final TableCell<Equipment, String> cell = new TableCell<Equipment, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					// that cell created only on non-empty rows
					if (empty) {
						setGraphic(null);
						setText(null);

					} else {

						Button deleteIcon = new Button("Xóa");

						deleteIcon.getStyleClass().add("danger");

						deleteIcon.setOnMouseClicked((MouseEvent event) -> {
							Equipment room = (Equipment) getTableRow().getItem();
							System.out.println("delete now" + room.getId());
							System.out.println(room.toString());
							EquipmentService rss = EquipmentService.getRepo();
							try {
								rss.delete(room);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							updateTable();

						});

						HBox managebtn = new HBox(deleteIcon);
						managebtn.setStyle("-fx-alignment:center");
						setGraphic(managebtn);
						setText(null);

					}
				}

			};

			return cell;
		};
		operationEquipColumn.setCellFactory(cellFoctory);
	}

}
