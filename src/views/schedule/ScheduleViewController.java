package views.schedule;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.RoomEntity;
import models.RoomScheduleEntity;
import models.UserEntity;
import service.RoomScheduleService;
import service.RoomService;
import service.UserService;

public class ScheduleViewController {

	@FXML
	private DatePicker inputEndTime;

	@FXML
	private TextArea inputReason;

	@FXML
	private ChoiceBox<String> inputRoom;

	@FXML
	private DatePicker inputStartTime;

	@FXML
	private Label inputTitle;

	@FXML
	private ChoiceBox<String> inputUser;

	@FXML
	private TextField inputRoomName;

	@FXML
	private TableView<Schedule> schedules;

	@FXML
	private TableColumn<Schedule, Date> scheduleEndTime;

	@FXML
	private TableColumn<Schedule, Integer> scheduleId;

	@FXML
	private TableColumn<Schedule, String> scheduleReason;

	@FXML
	private TableColumn<Schedule, String> scheduleRoom;

	@FXML
	private TableColumn<Schedule, Date> scheduleStartTime;

	@FXML
	private TableColumn<Schedule, String> scheduleUser;

	@FXML
	private Button deleteBtn;

	@FXML
	private AnchorPane scheduleModal;

	private Integer curId;

	List<String> listRoom = new ArrayList<>();
	List<String> listUser = new ArrayList<>();

	@FXML
	void closeModal(ActionEvent event) {
		scheduleModal.setVisible(false);
	}

	@FXML
	void openModal(ActionEvent event) {
		deleteBtn.setVisible(false);
		curId = null;
		inputTitle.setText("Thêm lịch mới");
		inputRoom.setValue(null);
		inputUser.setValue(null);
		inputStartTime.setValue(null);
		inputEndTime.setValue(null);
		inputReason.clear();

		scheduleModal.setVisible(true);
	}

	@FXML
	void submit(ActionEvent event) {
		try {
			RoomScheduleEntity rse = new RoomScheduleEntity();
			rse.setRoomId(RoomService.getRepo().getAll().get(listRoom.indexOf(inputRoom.getValue())).getId());
			rse.setTeacherId(UserService.getRepo().getAll().get(listUser.indexOf(inputUser.getValue())).getId());
			rse.setStartTime(Date.valueOf(inputStartTime.getValue()));
			rse.setEndTime(Date.valueOf(inputEndTime.getValue()));
			rse.setReason(inputReason.getText());

			RoomScheduleService rss = RoomScheduleService.getRepo();
			if (curId == null) {
				rse.setId(rss.getAll().size() + 1);
				rss.save(rse);
			} else {
				rse.setId(curId.intValue());
				rss.update(rse);
			}

			updateTable();
			scheduleModal.setVisible(false);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@FXML
	void delete(ActionEvent event) {
		try {
			if (curId == null)
				return;
			RoomScheduleService rss = RoomScheduleService.getRepo();
			RoomScheduleEntity rse = rss.getRoomScheduleById(curId.intValue());
			rss.delete(rse);
			curId = null;
			updateTable();
			scheduleModal.setVisible(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void onRowClick(MouseEvent event) {
		Schedule clickedRow = schedules.getSelectionModel().getSelectedItem();
		if (clickedRow == null)
			return;
		curId = clickedRow.getDisplayId();

		deleteBtn.setVisible(true);
		inputTitle.setText("Chi tiết");
		inputRoom.setValue(clickedRow.getDisplayRoom());
		inputUser.setValue(clickedRow.getDisplayUser());
		inputStartTime.setValue(clickedRow.getDisplayStartTime().toLocalDate());
		inputEndTime.setValue(clickedRow.getDisplayEndTime().toLocalDate());
		inputReason.setText(clickedRow.getDisplayReason());

		scheduleModal.setVisible(true);
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

		String filter = String.valueOf(name).trim().toLowerCase();

		try {
			schedules.getItems().clear();
			RoomScheduleService rss = RoomScheduleService.getRepo();
			for (RoomScheduleEntity res : rss.getAll()) {
				if (RoomService.getRepo().getRoomById(res.getRoomId()).getName().toLowerCase().contains(filter)) {
					Schedule ns = new Schedule();
					ns.setDisplayId(Integer.valueOf(res.getId()));
					ns.setDisplayRoom(RoomService.getRepo().getRoomById(res.getRoomId()).getName());
					ns.setDisplayUser(UserService.getRepo().getUserById(res.getTeacherId()).getName());
					ns.setDisplayStartTime(res.getStartTime());
					ns.setDisplayEndTime(res.getEndTime());
					ns.setDisplayReason(res.getReason());
					schedules.getItems().add(ns);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void updateTable() {
		try {
			schedules.getItems().clear();
			RoomScheduleService rss = RoomScheduleService.getRepo();
			for (RoomScheduleEntity res : rss.getAll()) {
				Schedule ns = new Schedule();
				ns.setDisplayId(Integer.valueOf(res.getId()));
				ns.setDisplayRoom(RoomService.getRepo().getRoomById(res.getRoomId()).getName());
				ns.setDisplayUser(UserService.getRepo().getUserById(res.getTeacherId()).getName());
				ns.setDisplayStartTime(res.getStartTime());
				ns.setDisplayEndTime(res.getEndTime());
				ns.setDisplayReason(res.getReason());
				schedules.getItems().add(ns);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void initialize() {
		scheduleId.setCellValueFactory(new PropertyValueFactory<>("displayId"));
		scheduleRoom.setCellValueFactory(new PropertyValueFactory<>("displayRoom"));
		scheduleUser.setCellValueFactory(new PropertyValueFactory<>("displayUser"));
		scheduleStartTime.setCellValueFactory(new PropertyValueFactory<>("displayStartTime"));
		scheduleEndTime.setCellValueFactory(new PropertyValueFactory<>("displayEndTime"));
		scheduleReason.setCellValueFactory(new PropertyValueFactory<>("displayReason"));

		updateTable();

		try {
			for (RoomEntity room : RoomService.getRepo().getAll()) {
				listRoom.add(room.getName());
				inputRoom.getItems().add(room.getName());
			}
			for (UserEntity user : UserService.getRepo().getAll()) {
				listUser.add(user.getName());
				inputUser.getItems().add(user.getName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
