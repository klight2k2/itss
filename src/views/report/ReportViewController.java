package views.report;

import java.sql.Date;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.RoomEntity;
import models.RoomReportEntity;
import models.UserEntity;

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

	@FXML
	void reset(ActionEvent event) {
		inputRoomName.clear();
	}

	@FXML
	void search(ActionEvent event) {

	}

	public void updateTable() {
		try {
			RoomReportEntity report = new RoomReportEntity();
			reports.getItems().clear();
			for (RoomReportEntity rp : report.getAll()) {
				Report newRp = new Report();
				newRp.setDisplayId(Integer.valueOf(rp.getId()));
				for (RoomEntity room : new RoomEntity().getAll()) {
					if (room.getId() == rp.getId()) {
						newRp.setDisplayRoom(room.getName());
					}
				}
				newRp.setDisplayEquipment("MT009");
				newRp.setDisplayStatus(rp.getStatus());
				for (UserEntity user : new UserEntity().getAll()) {
					if (user.getId() == rp.getReporterId())
						newRp.setDisplayReporter(user.getName());
				}
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
