package views.report;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
import service.EquipmentService;
import service.RoomReportService;
import service.RoomService;
import service.UserService;
import views.room.Equipment;

public class ReportViewController {

	@FXML
	private Button deleteEquipmentBtn;

	@FXML
	private AnchorPane detailModal;

	@FXML
	private Label detailRoom;

	@FXML
	private Label detailUser;

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
	private TableView<Report> reports;

	private int curId = -1;
	private int detailRoomId;
	private String selectedEquipId;

	@FXML
	void closeDetailModal(ActionEvent event) {
		detailModal.setVisible(false);
		detailRoomId = -1;
	}

	@FXML
	void deleteEquipment(ActionEvent event) {
		if (selectedEquipId == null || selectedEquipId.isEmpty())
			return;

	}

	@FXML
	void addEquipment(ActionEvent event) {

	}

	@FXML
	void editEquipment(ActionEvent event) {

	}

	@FXML
	void rowEquipmentClicked(MouseEvent event) {
		Equipment clickedRow = equipments.getSelectionModel().getSelectedItem();
		if (clickedRow == null)
			return;

		selectedEquipId = clickedRow.getDisplayId();
	}

	@FXML
	void onRowClicked(MouseEvent event) {
		Report clickedRow = reports.getSelectionModel().getSelectedItem();
		if (clickedRow == null)
			return;
		curId = clickedRow.getDisplayId().intValue();

		try {
			detailRoom.setText(clickedRow.getDisplayRoom());
			for (RoomEntity room : RoomService.getRepo().getAll()) {
				if (room.getName().equals(detailRoom.getText())) {
					detailRoomId = room.getId();
				}
			}
			detailUser.setText(clickedRow.getDisplayReporter());
			updateEquipTable();
			detailModal.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
					newRp.setDisplayEquipment("MT009");
					newRp.setDisplayStatus(rp.getStatus());
					newRp.setDisplayReporter(userRepo.getUserById(rp.getReporterId()).getName());
					newRp.setDisplayTimestamp(rp.getCreatedAt());
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
				newRp.setDisplayEquipment("MT009");
				newRp.setDisplayStatus(rp.getStatus());
				newRp.setDisplayReporter(userRepo.getUserById(rp.getReporterId()).getName());
				newRp.setDisplayTimestamp(rp.getCreatedAt());
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
			List<EquipmentEntity> equipmentsEntity = EquipmentService.getRepo().getAllEquipmentInRoom(roomId);
			if (equipmentsEntity == null)
				return;
			equipments.getItems().clear();
			for (EquipmentEntity equip : equipmentsEntity) {
				Equipment newEquip = new Equipment();
				newEquip.setDisplayId(equip.getId());
				newEquip.setDisplayName(equip.getName());
				newEquip.setDisplayStatus("Hỏng");
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

	public void initialize() {
		reportId.setCellValueFactory(new PropertyValueFactory<>("displayId"));
		reportRoom.setCellValueFactory(new PropertyValueFactory<>("displayRoom"));
		reportEquipment.setCellValueFactory(new PropertyValueFactory<>("displayEquipment"));
		reportStatus.setCellValueFactory(new PropertyValueFactory<>("displayStatus"));
		reportUser.setCellValueFactory(new PropertyValueFactory<>("displayReporter"));
		reportTimestamp.setCellValueFactory(new PropertyValueFactory<>("displayTimestamp"));

		equipId.setCellValueFactory(new PropertyValueFactory<Equipment, String>("displayId"));
		equipName.setCellValueFactory(new PropertyValueFactory<Equipment, String>("displayName"));
		equipStatus.setCellValueFactory(new PropertyValueFactory<Equipment, String>("displayStatus"));
		equipTimeUse.setCellValueFactory(new PropertyValueFactory<Equipment, Date>("displayTimeUse"));
		equipMfg.setCellValueFactory(new PropertyValueFactory<Equipment, Date>("displayMfg"));
		equipRepairTime.setCellValueFactory(new PropertyValueFactory<Equipment, Integer>("displayTimeRepair"));
		equipNote.setCellValueFactory(new PropertyValueFactory<Equipment, String>("displayNote"));

		updateTable();
	}
}
