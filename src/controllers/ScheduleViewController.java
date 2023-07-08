package controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import models.RoomEntity;
import models.RoomScheduleEntity;
import models.UserEntity;
import service.RoomScheduleService;
import service.RoomService;
import service.UserService;
import views.schedule.DateTimePicker;
import views.schedule.Schedule;

public class ScheduleViewController {

	@FXML
	private HBox endTimeBox, startTimeBox;

	private DateTimePicker inputEndTime = new DateTimePicker();
	private DateTimePicker inputStartTime = new DateTimePicker();

	@FXML
	private TextArea inputReason;

	@FXML
	private ComboBox<RoomEntity> inputRoom;

	@FXML
	private Label inputTitle;

	@FXML
	private ComboBox<UserEntity> inputUser;

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

	private ObservableList<RoomEntity> listRoom = FXCollections.observableArrayList();
	private ObservableList<UserEntity> listUser = FXCollections.observableArrayList();

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
			System.out.println(this.inputStartTime.getDateTimeValue());
			RoomScheduleEntity rse = new RoomScheduleEntity();
			rse.setRoomId(inputRoom.getSelectionModel().getSelectedItem().getId());
			rse.setTeacherId(inputUser.getSelectionModel().getSelectedItem().getId());
			rse.setStartTime((inputStartTime.getDateTimeValue()));
			rse.setEndTime((inputEndTime.getDateTimeValue()));
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
		for (RoomEntity item : listRoom) {
			if (item.getId() == clickedRow.getRoomId()) {
				// borrowerCombobox.setV
				inputRoom.setValue(item);
				System.out.println("success");
			}

		}
		for (UserEntity item : listUser) {
			if (item.getId() == clickedRow.getTeacherId()) {
				// borrowerCombobox.setV
				inputUser.setValue(item);
				System.out.println("success");
			}

		}
		inputStartTime.setDateTimeValue(clickedRow.getDisplayStartTime());
		inputEndTime.setDateTimeValue(clickedRow.getDisplayEndTime());
		inputReason.setText(clickedRow.getDisplayReason());
		System.out.println("date time"+clickedRow.getDisplayStartTime().toLocalDate());

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
					for (RoomEntity item : listRoom) {
						if (item.getId() == res.getRoomId()) {
							ns.setDisplayRoom(item.getName());
						}

					}
					for (UserEntity item : listUser) {
						if (item.getId() == res.getTeacherId()) {
							System.out.println("user id" + res.getTeacherId());
							ns.setDisplayUser(item.getName());
						}

					}
					ns.setRoomId(res.getRoomId());
					ns.setTeacherId(res.getTeacherId());
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
				for (RoomEntity item : listRoom) {
					if (item.getId() == res.getRoomId()) {
						ns.setDisplayRoom(item.getName());
					}

				}
				for (UserEntity item : listUser) {
					if (item.getId() == res.getTeacherId()) {
						ns.setDisplayUser(item.getName());
					}

				}
				ns.setRoomId(res.getRoomId());
				ns.setTeacherId(res.getTeacherId());
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
		startTimeBox.getChildren().add(inputStartTime);
		endTimeBox.getChildren().add(inputEndTime);
		this.closeModal(null);
		scheduleId.setCellValueFactory(new PropertyValueFactory<>("displayId"));
		scheduleRoom.setCellValueFactory(new PropertyValueFactory<>("displayRoom"));
		scheduleUser.setCellValueFactory(new PropertyValueFactory<>("displayUser"));
		scheduleStartTime.setCellValueFactory(new PropertyValueFactory<>("displayStartTime"));
		scheduleEndTime.setCellValueFactory(new PropertyValueFactory<>("displayEndTime"));
		scheduleReason.setCellValueFactory(new PropertyValueFactory<>("displayReason"));

		try {
			listRoom.setAll(RoomService.getRepo().getAll());
			inputRoom.setItems(listRoom);
			listUser.setAll(UserService.getRepo().getAll());
			inputUser.setItems(listUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateTable();

	}
}
