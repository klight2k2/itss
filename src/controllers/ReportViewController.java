package controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.CategoryEquipmentEntity;
import models.EquipmentEntity;
import models.RoomEntity;
import models.RoomReportEntity;
import models.UserEntity;
import service.CategoryEquipmentService;
import service.EquipmentService;
import service.RoomReportService;
import service.RoomService;
import service.UserService;
import views.report.Report;
import views.room.Equipment;

public class ReportViewController {

	@FXML
	private Button deleteEquipmentBtn;

	@FXML
	private AnchorPane detailModal;

	@FXML
	private AnchorPane addModal;

	@FXML
	private AnchorPane addEquipModal;

	@FXML
	private AnchorPane detailEquipmentModal;

	@FXML
	private Label detailRoom;

	@FXML
	private Label detailUser;

	@FXML
	private Label detailStatus;

	@FXML
	private Label detailTime;

	@FXML
	private Label detailNote;

	@FXML
	private Label reportTitle;

	@FXML
	private TableColumn<Equipment, String> addEquipId;

	@FXML
	private TableColumn<Equipment, String> addEquipName;

	@FXML
	private TableColumn<Equipment, String> addEquipNote;

	@FXML
	private TableView<Equipment> addEquipments;

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

	@FXML
	private TableView<Equipment> equipments;

	@FXML
	private TextField inputSearch;

	@FXML
	private TableColumn<Report, String> reportEquipment;

	@FXML
	private TableColumn<Report, Integer> reportId;

	@FXML
	private TableColumn<Report, String> reportRoom;

	@FXML
	private TableColumn<Report, String> reportStatus;

	@FXML
	private TableColumn<Report, Date> reportTimestamp;

	@FXML
	private TableColumn<Report, String> reportUser;

	@FXML
	private TableColumn<Report, String> reportNote;

	@FXML
	private TableView<Report> reports;

	@FXML
	private ComboBox<UserEntity> inputReporter;
	ObservableList<UserEntity> listUser = FXCollections.observableArrayList();

	@FXML
	private ComboBox<RoomEntity> inputRoom;
	ObservableList<RoomEntity> listRoom = FXCollections.observableArrayList();

	@FXML
	private ComboBox<String> inputStatus;
	ObservableList<String> listStatus = FXCollections.observableArrayList();

	@FXML
	private DatePicker inputTime;

	@FXML
	private TextArea inputNote;

	@FXML
	private ComboBox<CategoryEquipmentEntity> inputEquipCategory;

	@FXML
	private DatePicker inputEquipMfg;

	@FXML
	private TextField inputEquipName;

	@FXML
	private TextArea inputEquipNote;

	@FXML
	private TextField inputEquipRepairTime;

	@FXML
	private ComboBox<RoomEntity> inputEquipRoom;

	@FXML
	private ComboBox<String> inputEquipStatus;

	@FXML
	private DatePicker inputEquipYearUse;

	private int curId = -1;
	private int detailRoomId;
	private String selectedEquipId;
	List<String> curReportEquips = new ArrayList<>();
	List<String> initStatus = new ArrayList<>();

	@FXML
	private TextField searchEquipTextField;

	public void onSearchEquip(InputMethodEvent event) {
		FilteredList<Equipment> filteredData = new FilteredList<>(addEquipments.getItems(), p -> true);
		searchEquipTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(equip -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (equip.getName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else {
					return false;
				}
			});
		});

	}

	@FXML
	void closeDetailModal(ActionEvent event) {
		updateTable();
		detailModal.setVisible(false);
		detailRoomId = -1;
	}

	@FXML
	void deleteEquipment(ActionEvent event) {
		if (selectedEquipId == null || selectedEquipId.isEmpty())
			return;
		try {
			List<Integer> deleteEquips = new ArrayList<>();
			deleteEquips.add(Integer.valueOf(selectedEquipId));
			System.out.println("Delete: " + deleteEquips);
			RoomReportService.getRepo().deleteListEquipmentInRoomReport(curId, deleteEquips);
			selectedEquipId = null;
			updateEquipTable();
			detailEquipmentModal.setVisible(false);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@FXML
	void addOpenModal(ActionEvent event) {
		reportTitle.setText("Tạo báo cáo mới");
		inputRoom.setValue(null);
		inputRoom.setDisable(false);
		inputReporter.setValue(null);
		inputReporter.setDisable(false);
		inputStatus.setValue("PENDING");
		inputTime.setValue(null);
		inputNote.clear();
		addModal.setVisible(true);
	}

	@FXML
	void addCancel(ActionEvent event) {
		addModal.setVisible(false);
	}

	@FXML
	void editReport(ActionEvent event) {
		try {
			if (curId < 0)
				return;
			RoomReportEntity rre = RoomReportService.getRepo().getRoomReportById(curId);
			reportTitle.setText("Chỉnh sửa báo cáo");
			inputRoom.setValue(RoomService.getRepo().getRoomById(rre.getRoomId()));
			inputRoom.setDisable(true);
			inputReporter.setValue(UserService.getRepo().getUserById(rre.getReporterId()));
			inputReporter.setDisable(true);
			inputStatus.setValue(rre.getStatus());
			inputTime.setValue(rre.getCreatedAt().toLocalDate());
			inputNote.setText(rre.getNote());
			addModal.setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@FXML
	void addSubmit(ActionEvent event) {
		RoomReportEntity rre = new RoomReportEntity();
		rre.setRoomId(inputRoom.getSelectionModel().getSelectedItem().getId());
		rre.setReporterId(inputReporter.getSelectionModel().getSelectedItem().getId());
		rre.setCreatedAt(Date.valueOf(inputTime.getValue()));
		rre.setStatus(inputStatus.getSelectionModel().getSelectedItem());
		rre.setApproverId(1);
		rre.setNote(inputNote.getText());
		try {
			if (curId > 0) {
				rre.setId(curId);
				RoomReportService.getRepo().update(rre);
			} else {
				RoomReportService.getRepo().save(rre);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateTable();
		openDetailModal();
		addModal.setVisible(false);
	}

	@FXML
	void addEquipment(ActionEvent event) {
		addEquipModal.setVisible(true);
	}

	@FXML
	void closeAddEquipModal(ActionEvent event) {
		addEquipModal.setVisible(false);
	}

	@FXML
	void cancelEditEquip(ActionEvent event) {
		detailEquipmentModal.setVisible(false);
	}

	@FXML
	void submitEditEquip(ActionEvent event) {
		try {
			EquipmentEntity curEquip = new EquipmentEntity();
			curEquip.setId(selectedEquipId);
			curEquip.setRoomId(inputEquipRoom.getValue().getId());
			curEquip.setName(inputEquipName.getText());
			curEquip.setEquipmentCategoryId(inputEquipCategory.getValue().getId());
			curEquip.setStatus(initStatus.indexOf(inputEquipStatus.getValue()));
			curEquip.setMfg(Date.valueOf(inputEquipMfg.getValue()));
			curEquip.setYearOfUse(Date.valueOf(inputEquipYearUse.getValue()));
			curEquip.setNumberOfRepairs(Integer.valueOf(inputEquipRepairTime.getText()).intValue());
			curEquip.setNote(inputEquipNote.getText());
			EquipmentService.getRepo().update(curEquip);
			updateEquipTable();
			detailEquipmentModal.setVisible(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void addEquipRowClick(MouseEvent event) {
		Equipment clickedRow = addEquipments.getSelectionModel().getSelectedItem();
		if (clickedRow == null)
			return;

		try {
			List<Integer> addEquips = new ArrayList<>();
			addEquips.add(Integer.valueOf(clickedRow.getDisplayId()));
			System.out.println("Add equips: " + addEquips);
			System.out.println(curId);
			RoomReportService.getRepo().saveListEquipmentInRoomReport(curId, addEquips);
			updateEquipTable();
			addEquipModal.setVisible(false);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@FXML
	void rowEquipmentClicked(MouseEvent event) {
		Equipment clickedRow = equipments.getSelectionModel().getSelectedItem();
		if (clickedRow == null)
			return;

		selectedEquipId = clickedRow.getDisplayId();

		try {
			EquipmentEntity curEquip = EquipmentService.getRepo().getEquipmentById(selectedEquipId);
			inputEquipRoom.setValue(RoomService.getRepo().getRoomById(curEquip.getRoomId()));
			inputEquipName.setText(curEquip.getName());
			inputEquipCategory
					.setValue(CategoryEquipmentService.getRepo().getCategoryById(curEquip.getEquipmentCategoryId()));
			inputEquipStatus.setValue(initStatus.get(curEquip.getStatus()));
			inputEquipMfg.setValue(curEquip.getMfg().toLocalDate());
			inputEquipYearUse.setValue(curEquip.getYearOfUse().toLocalDate());
			inputEquipRepairTime.setText(String.valueOf(curEquip.getNumberOfRepairs()));
			inputEquipNote.setText(curEquip.getNote());
			detailEquipmentModal.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void onRowClicked(MouseEvent event) {
		
		Report clickedRow = reports.getSelectionModel().getSelectedItem();
		if (clickedRow == null)
			return;
		curId = clickedRow.getDisplayId().intValue();

		openDetailModal();
	}

	void openDetailModal() {
		try {
			if (curId < 0) {
				// Get newest
				curId = reports.getItems().get(reports.getItems().size() - 1).getDisplayId().intValue();
			}
			System.out.println("Current report: " + curId);
			RoomReportEntity rre = RoomReportService.getRepo().getRoomReportById(curId);
			detailRoom.setText(RoomService.getRepo().getRoomById(rre.getRoomId()).getName());
			detailRoomId = rre.getRoomId();
			detailUser.setText(UserService.getRepo().getUserById(rre.getReporterId()).getName());
			detailStatus.setText(rre.getStatus());
			detailTime.setText(rre.getCreatedAt().toString());
			detailNote.setText(rre.getNote());
			updateEquipTable();
			detailModal.setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@FXML
	void delete(ActionEvent event) {
		try {
			RoomReportService roomRepo = RoomReportService.getRepo();
			if (curId > -1) {
				RoomReportEntity report = roomRepo.getRoomReportById(curId);
				roomRepo.delete(report);
				updateTable();
			}
			detailModal.setVisible(false);
			curId = -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void reset(ActionEvent event) {
		if (inputSearch != null)
			inputSearch.clear();
		updateTable();
	}

	@FXML
	void search(ActionEvent event) {
		String name = inputSearch.getText();
		if (name == null || name.isEmpty())
			return;

		String filter = String.valueOf(name).trim();

		try {
			RoomReportEntity report = new RoomReportEntity();
			RoomReportService roomReportRepo = RoomReportService.getRepo();
			UserService userRepo = UserService.getRepo();
			RoomService roomRepo = RoomService.getRepo();

			reports.getItems().clear();
			for (RoomReportEntity rp : roomReportRepo.getAll()) {
				if (roomRepo.getRoomById(rp.getRoomId()).getName().contains(filter)) {
					Report newRp = new Report();
					newRp.setDisplayId(Integer.valueOf(rp.getId()));
					newRp.setDisplayRoom(roomRepo.getRoomById(rp.getRoomId()).getName());
					newRp.setDisplayEquipment(
							RoomReportService.getRepo().getEquipmentByRoomReportId(rp.getId()).size());
					newRp.setDisplayStatus(rp.getStatus());
					newRp.setDisplayReporter(userRepo.getUserById(rp.getReporterId()).getName());
					newRp.setDisplayTimestamp(rp.getCreatedAt());
					newRp.setDisplayNote(rp.getNote());
					reports.getItems().add(newRp);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateTable() {
		try {
			RoomReportEntity report = new RoomReportEntity();
			RoomReportService roomReportRepo = RoomReportService.getRepo();
			UserService userRepo = UserService.getRepo();
			RoomService roomRepo = RoomService.getRepo();

			reports.getItems().clear();
			for (RoomReportEntity rp : roomReportRepo.getAll()) {
				Report newRp = new Report();
				newRp.setDisplayId(Integer.valueOf(rp.getId()));
				newRp.setDisplayRoom(roomRepo.getRoomById(rp.getRoomId()).getName());
				newRp.setDisplayEquipment(RoomReportService.getRepo().getEquipmentByRoomReportId(rp.getId()).size());
				newRp.setDisplayStatus(rp.getStatus());
				newRp.setDisplayReporter(userRepo.getUserById(rp.getReporterId()).getName());
				newRp.setDisplayTimestamp(rp.getCreatedAt());
				newRp.setDisplayNote(rp.getNote());
				reports.getItems().add(newRp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void updateEquipTable() {
		try {
			if (detailRoomId < 0)
				return;
			int roomId = detailRoomId;
			List<EquipmentEntity> roomEquipments = EquipmentService.getRepo().getAllEquipmentInRoom(roomId);
			List<EquipmentEntity> reportEquipments = RoomReportService.getRepo().getEquipmentByRoomReportId(curId);
			if (roomEquipments == null)
				return;
			equipments.getItems().clear();
			addEquipments.getItems().clear();
			curReportEquips.clear();
			for (EquipmentEntity equip : reportEquipments) {
				Equipment newEquip = new Equipment();
				newEquip.setDisplayId(equip.getId());
				newEquip.setDisplayName(equip.getName());
				newEquip.setDisplayStatus(initStatus.get(equip.getStatus()));
				newEquip.setDisplayMfg(equip.getMfg());
				newEquip.setDisplayTimeUse(equip.getYearOfUse());
				newEquip.setDisplayTimeRepair(equip.getNumberOfRepairs());
				newEquip.setDisplayNote(equip.getNote());
				equipments.getItems().add(newEquip);
				curReportEquips.add(equip.getId());
			}
			for (EquipmentEntity equip : roomEquipments) {
				if (curReportEquips.indexOf(equip.getId()) == -1) {
					Equipment newEquip = new Equipment();
					newEquip.setDisplayId(equip.getId());
					newEquip.setDisplayName(equip.getName());
					newEquip.setDisplayStatus(initStatus.get(equip.getStatus()));
					newEquip.setDisplayMfg(equip.getMfg());
					newEquip.setDisplayTimeUse(equip.getYearOfUse());
					newEquip.setDisplayTimeRepair(equip.getNumberOfRepairs());
					newEquip.setDisplayNote(equip.getNote());
					addEquipments.getItems().add(newEquip);
				}
			}
			equipments.refresh();
			addEquipments.refresh();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initialize() {
		detailModal.setVisible(false);
		addModal.setVisible(false);
		addEquipModal.setVisible(false);
		detailEquipmentModal.setVisible(false);

		reportId.setCellValueFactory(new PropertyValueFactory<>("displayId"));
		reportRoom.setCellValueFactory(new PropertyValueFactory<>("displayRoom"));
		reportEquipment.setCellValueFactory(new PropertyValueFactory<>("displayEquipment"));
		reportStatus.setCellValueFactory(new PropertyValueFactory<>("displayStatus"));
		reportUser.setCellValueFactory(new PropertyValueFactory<>("displayReporter"));
		reportTimestamp.setCellValueFactory(new PropertyValueFactory<>("displayTimestamp"));
		reportNote.setCellValueFactory(new PropertyValueFactory<>("displayNote"));

		equipId.setCellValueFactory(new PropertyValueFactory<Equipment, String>("displayId"));
		equipName.setCellValueFactory(new PropertyValueFactory<Equipment, String>("displayName"));
		equipStatus.setCellValueFactory(new PropertyValueFactory<Equipment, String>("displayStatus"));
		equipTimeUse.setCellValueFactory(new PropertyValueFactory<Equipment, Date>("displayTimeUse"));
		equipMfg.setCellValueFactory(new PropertyValueFactory<Equipment, Date>("displayMfg"));
		equipRepairTime.setCellValueFactory(new PropertyValueFactory<Equipment, Integer>("displayTimeRepair"));
		equipNote.setCellValueFactory(new PropertyValueFactory<Equipment, String>("displayNote"));

		addEquipId.setCellValueFactory(new PropertyValueFactory<>("displayId"));
		addEquipName.setCellValueFactory(new PropertyValueFactory<>("displayName"));
		addEquipNote.setCellValueFactory(new PropertyValueFactory<>("displayNote"));

		try {
			listRoom.setAll(RoomService.getRepo().getAll());
			inputRoom.setItems(listRoom);
			listUser.setAll(UserService.getRepo().getAll());
			inputReporter.setItems(listUser);
			listStatus.setAll("PENDING", "APPROVED", "REJECTED");
			inputStatus.setItems(listStatus);

			inputEquipRoom.getItems().addAll(RoomService.getRepo().getAll());
			inputEquipCategory.getItems().addAll(CategoryEquipmentService.getRepo().getAll());
			initStatus.add(0, "Hỏng");
			initStatus.add(1, "Tốt");
			initStatus.add(2, "Đang sửa");
			inputEquipStatus.getItems().addAll(initStatus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		updateTable();
	}
}
