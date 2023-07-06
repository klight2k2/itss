package views.equipment;

import java.sql.Date;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.CategoryEquipment;
import models.EquipmentEntity;
import models.RoomEntity;
import service.CategoryEquipmentService;
import service.EquipmentService;
import service.RoomService;

public class EquipmentViewController {

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
	private DatePicker inputEquipMfg;

	@FXML
	private TextArea inputEquipNote;

	@FXML
	private TextField inputEquipRepairTime;

	@FXML
	private ComboBox<String> inputEquipCategory;

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

	@FXML
	private Button deleteBtn;

	@FXML
	private ComboBox<String> inputEquipRoom;

	private String curId;

	public String convertStatus(int status) {
		return status == 0 ? "Hỏng" : status == 1 ? "Tốt" : "Đang sửa";
	}

	public int convertStatus(String status) {
		return status == "Hỏng" ? 0 : status == "Tốt" ? 1 : status == "Đang sửa" ? 2 : -1;
	}

	@FXML
	void onRowClick(MouseEvent event) {
		Equipment clickedRow = equipments.getSelectionModel().getSelectedItem();
		if (clickedRow == null)
			return;
		curId = clickedRow.getDisplayId();
		deleteBtn.setVisible(true);
		inputTitle.setText("Chỉnh sửa");
		inputEquipName.setText(clickedRow.getDisplayName());
		inputEquipRoom.setValue(clickedRow.getDisplayRoom());
		inputEquipCategory.setValue(clickedRow.getDisplayCategory());
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
		deleteBtn.setVisible(false);
		inputTitle.setText("Thêm thiết bị");
		inputEquipName.clear();
		inputEquipRoom.setValue("Room ?");
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

//			for (RoomEntity room : roomRepo.getAll()) {
//				if (room.getName().equals(inputEquipRoom.getValue())) {
//
//				}
//			}
			for (CategoryEquipment ce : categoryEquipmentService.getAll()) {
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
					newEq.setDisplayRoom("NAH");
					for (CategoryEquipment ce : categoryEquipmentService.getAll()) {
						if (ce.getId() == eq.getEquipmentCategoryId()) {
							newEq.setDisplayCategory(ce.getName());
						}
					}
					newEq.setDisplayName(eq.getName());
					newEq.setDisplayStatus(convertStatus(eq.getStatus()));
					newEq.setDisplayTimeUse(eq.getYearOfUse());
					newEq.setDisplayMfg(eq.getMfg());
					newEq.setDisplayTimeRepair(eq.getNumberOfRepairs());
					newEq.setDisplayNote(eq.getNote());
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
			EquipmentEntity eqs = new EquipmentEntity();
			EquipmentService equipmentRepo = EquipmentService.getRepo();
			CategoryEquipmentService categoryEquipmentService = CategoryEquipmentService.getRepo();
			for (EquipmentEntity eq : equipmentRepo.getAll()) {
				Equipment newEq = new Equipment();
				newEq.setDisplayId(eq.getId());
				newEq.setDisplayRoom("NAH");
				for (CategoryEquipment ce : categoryEquipmentService.getAll()) {
					if (ce.getId() == eq.getEquipmentCategoryId()) {
						newEq.setDisplayCategory(ce.getName());
					}
				}
				newEq.setDisplayName(eq.getName());
				newEq.setDisplayStatus(convertStatus(eq.getStatus()));
				newEq.setDisplayTimeUse(eq.getYearOfUse());
				newEq.setDisplayMfg(eq.getMfg());
				newEq.setDisplayTimeRepair(eq.getNumberOfRepairs());
				newEq.setDisplayNote(eq.getNote());
				equipments.getItems().add(newEq);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initialize() {
		addEquipmentModal.setVisible(false);
		CategoryEquipmentService categoryEquipmentRepo = CategoryEquipmentService.getRepo();
		RoomService roomRepo = RoomService.getRepo();
		try {
			for (RoomEntity room : roomRepo.getAll()) {
				inputEquipRoom.getItems().add(room.getName());
			}
			for (CategoryEquipment ce : categoryEquipmentRepo.getAll()) {
				inputEquipCategory.getItems().add(ce.getName());
			}
			inputEquipStatus.getItems().addAll("Hỏng", "Tốt", "Đang sửa");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	}
}
