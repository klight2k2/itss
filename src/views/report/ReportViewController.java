package views.report;

import java.sql.Date;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.RoomEntity;
import models.RoomReportEntity;
import models.UserEntity;
import service.PayBorrowService;
import service.RoomReportService;
import service.RoomService;
import service.UserService;

public class ReportViewController {

	@FXML
	private TextField inputRoomName;

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

	private int delId = -1;

	@FXML
	void onRowClicked(MouseEvent event) {
		Report clickedRow = reports.getSelectionModel().getSelectedItem();
		if (clickedRow == null)
			return;
		delId = clickedRow.getDisplayId().intValue();
	}

	@FXML
	void delete(ActionEvent event) {
		try {
			RoomReportService roomRepo= RoomReportService.getRepo();
			if (delId > -1) {
				RoomReportEntity report =  roomRepo.getRoomReportById(delId);
				roomRepo.delete(report);
				updateTable();
			}
			delId = -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void reset(ActionEvent event) {
		inputRoomName.clear();
		updateTable();
	}

	@FXML
	void search(ActionEvent event) {
		String name = inputRoomName.getText();
		if (name == null || name.isEmpty())
			return;

		String filter = String.valueOf(name).trim();

		try {
			RoomReportEntity report = new RoomReportEntity();
			RoomReportService roomReportRepo= RoomReportService.getRepo();
			UserService userRepo= UserService.getRepo();
			RoomService roomRepo= RoomService.getRepo();
			
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
			RoomReportService roomReportRepo= RoomReportService.getRepo();
			UserService userRepo= UserService.getRepo();
			RoomService roomRepo= RoomService.getRepo();
			
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

	public void initialize() {
		reportId.setCellValueFactory(new PropertyValueFactory<>("displayId"));
		reportRoom.setCellValueFactory(new PropertyValueFactory<>("displayRoom"));
		reportEquipment.setCellValueFactory(new PropertyValueFactory<>("displayEquipment"));
		reportStatus.setCellValueFactory(new PropertyValueFactory<>("displayStatus"));
		reportUser.setCellValueFactory(new PropertyValueFactory<>("displayReporter"));
		reportTimestamp.setCellValueFactory(new PropertyValueFactory<>("displayTimestamp"));

		updateTable();
	}
}
