package controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import common.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.EquipmentEntity;
import models.PayBorrowEntity;
import models.UserEntity;
import service.EquipmentService;
import service.PayBorrowService;
import service.UserService;
import utils.Utils;
import views.payborrow.PayBorrow;
import javafx.util.Callback;

public class PayBorrowViewController {

	@FXML
	private DatePicker borrowDate;

	@FXML
	private TextArea borrowReason;

	@FXML
	private ComboBox<UserEntity> borrowerCombobox;

	@FXML
	private DatePicker payDate;

	@FXML
	private AnchorPane pbDetailModal;

	@FXML
	private AnchorPane addEquipModal;

	@FXML
	private TableView<PayBorrow> pbEquipments;

	@FXML
	private TableColumn<PayBorrow, Date> pbBorrowDate;

	@FXML
	private TableColumn<PayBorrow, String> pbBorrowReason;

	@FXML
	private TableColumn<PayBorrow, String> pbBorrowUser;

	@FXML
	private TableColumn<PayBorrow, String> operationBorrowcolumn;

	// @FXML
	// private TableColumn<PayBorrow, ?> pbEquipId;

	@FXML
	private TableColumn<PayBorrow, Integer> pbId;

	@FXML
	private TableColumn<PayBorrow, Date> pbPayDate;

	@FXML
	private TableColumn<PayBorrow, String> pbRefuseReason;

	@FXML
	private TableColumn<PayBorrow, String> pbStatus;

	@FXML
	private TableColumn<PayBorrow, String> operationPayBorrowColumn;

	@FXML
	private ChoiceBox<String> status;

	private String[] statusValues = { "Đang chờ", "Chấp nhận","Từ chối", "Đang mượn", "Đã trả" };

	@FXML
	private TextArea refuseReason;

	@FXML
	private Label modalTitle;

	@FXML
	private TableColumn<EquipmentEntity, String> borrowNoteColumn;

	@FXML
	private TableColumn<EquipmentEntity, String> borrowEquipNameColumn;

	@FXML
	private TableColumn<EquipmentEntity, String> operationcolumn;

	@FXML
	private TableColumn<EquipmentEntity, String> borrowIdColumn;

	@FXML
	private TableColumn<EquipmentEntity, String> borrowedIdColumn;

	@FXML
	private TableColumn<EquipmentEntity, String> borrowedEquipNameColumn;

	@FXML
	private TableColumn<EquipmentEntity, String> borrowedNoteColumn;

	@FXML
	private TableView<EquipmentEntity> tableViewBorrow;

	@FXML
	private TableView<EquipmentEntity> tableViewBorrowed;

	@FXML
	void addEquipment(ActionEvent event) {
		addEquipModal.setVisible(true);

	}

	private int pbCurId;
	private ObservableList<EquipmentEntity> listBorrowed = FXCollections.observableArrayList();
	private ObservableList<EquipmentEntity> listBorrow = FXCollections.observableArrayList();
	private ObservableList<UserEntity> listBorrower = FXCollections.observableArrayList();

	@FXML
	void closeModal(ActionEvent event) {
		pbDetailModal.setVisible(false);
	}

	@FXML
	void openModal(ActionEvent event) {
		pbCurId = -1;
		listBorrowed.removeAll();
		status.setValue(null);
		borrowDate.setValue(null);
		payDate.setValue(null);
		borrowReason.clear();
		refuseReason.clear();
		listBorrowed.clear();
		try {
			listBorrow.setAll(EquipmentService.getRepo().getAllEquipmentNoUse());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pbDetailModal.setVisible(true);
	}

	@FXML
	void submit(ActionEvent event) {
		try {

			PayBorrowEntity pb = new PayBorrowEntity();
			PayBorrowService payBorrowRepo = PayBorrowService.getRepo();
			pb.setStatus(Utils.convertBorrowStatus(
					status.getValue()));
			pb.setFromDate(Date.valueOf(borrowDate.getValue()));
			pb.setToDate(Date.valueOf(payDate.getValue()));
			pb.setBorrowReason(borrowReason.getText());
			pb.setRefuseReason(refuseReason.getText());
			pb.setBorrowerId(borrowerCombobox.getSelectionModel().getSelectedItem().getId());
			pb.setListEquipment(listBorrowed);
			System.out.println("submit  borrow" + listBorrowed.size());
			if (pbCurId > 0) {
				pb.setId(pbCurId);
				payBorrowRepo.update(pb);
			} else {
				payBorrowRepo.save(pb);
			}

			updateTable();
			pbDetailModal.setVisible(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void delete(ActionEvent event) {
		try {
			PayBorrowService payBorrowRepo = PayBorrowService.getRepo();
			PayBorrowEntity pbs = new PayBorrow();
			for (PayBorrowEntity pb : payBorrowRepo.getAll()) {
				if (pb.getId() == pbCurId) {
					payBorrowRepo.delete(pb);
					break;
				}
			}
			updateTable();
			pbDetailModal.setVisible(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void rowClicked(MouseEvent event) {
		if (!LoginController.currentUser.getRole().equals(Role.ADMIN)) return;

		PayBorrow clickedRow = pbEquipments.getSelectionModel().getSelectedItem();
		if (clickedRow == null)
			return;

		pbCurId = clickedRow.getDisplayId().intValue();
		for (UserEntity item : listBorrower) {
			if (item.getId() == clickedRow.getBorrowerId()) {
				// borrowerCombobox.setV
				borrowerCombobox.setValue(item);
				System.out.println("success");
			}

		}
		System.out.println(clickedRow.getBorrowerId());

		status.setValue(clickedRow.getDisplayStatus());
		borrowDate.setValue(clickedRow.getDisplayFromDate().toLocalDate());
		payDate.setValue(clickedRow.getDisplayToDate().toLocalDate());
		borrowReason.setText(clickedRow.getDisplayBorrowReason());
		refuseReason.setText(clickedRow.getDisplayRefuseReason());
		try {

			listBorrowed.setAll(EquipmentService.getRepo().getEquipmentBorrowed(pbCurId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loi o day");
			e.printStackTrace();
		}
		try {
			listBorrow.setAll(EquipmentService.getRepo().getAllEquipmentNoUse());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableViewBorrowed.setItems(listBorrowed);

		tableViewBorrow.setItems(listBorrow);

		pbDetailModal.setVisible(true);
	}

	@FXML
	private TextField searchEquipTextField;

	public void onSearchEquip(InputMethodEvent event) {
		FilteredList<EquipmentEntity> filteredData = new FilteredList<>(listBorrow, p -> true);
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

	public void updateTable() {
		try {
			pbEquipments.getItems().clear();
			PayBorrowService payBorrowRepo = PayBorrowService.getRepo();
			for (PayBorrowEntity pb : payBorrowRepo.getAll()) {
				PayBorrow newPb = new PayBorrow();
				newPb.setId(pb.getId());
				newPb.setDisplayId(Integer.valueOf(pb.getId()));
				newPb.setDisplayStatus( Utils.convertReverseBorrowStatus(pb.getStatus()));
				newPb.setDisplayFromDate(pb.getFromDate());
				newPb.setDisplayToDate(pb.getToDate());
				newPb.setDisplayBorrowReason(pb.getBorrowReason());
				newPb.setDisplayRefuseReason(pb.getRefuseReason());
				newPb.setBorrowerId(pb.getBorrowerId());
				for (UserEntity curUser : listBorrower) {
					if (curUser.getId() == pb.getBorrowerId()) {
						newPb.setDisplayUsername(curUser.getName());
						break;
					}
				}
				pbEquipments.getItems().add(newPb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void hiddenAddEquipModal() {
		addEquipModal.setVisible(false);
	}

	public void initialize() {
		try {
			pbDetailModal.setVisible(false);
			addEquipModal.setVisible(false);
				if (!LoginController.currentUser.getRole().equals(Role.ADMIN)) {
			operationPayBorrowColumn.setVisible(false);
		}
			status.getItems().addAll(statusValues);
			listBorrower.setAll(UserService.getRepo().getAll());
			borrowerCombobox.setItems(listBorrower);

			pbId.setCellValueFactory(new PropertyValueFactory<PayBorrow, Integer>("displayId"));
			pbBorrowUser.setCellValueFactory(new PropertyValueFactory<PayBorrow, String>("displayUsername"));
			pbStatus.setCellValueFactory(new PropertyValueFactory<PayBorrow, String>("displayStatus"));
			pbBorrowDate.setCellValueFactory(new PropertyValueFactory<PayBorrow, Date>("displayFromDate"));
			pbPayDate.setCellValueFactory(new PropertyValueFactory<PayBorrow, Date>("displayToDate"));
			pbBorrowReason.setCellValueFactory(new PropertyValueFactory<PayBorrow, String>("displayBorrowReason"));
			pbRefuseReason.setCellValueFactory(new PropertyValueFactory<PayBorrow, String>("displayRefuseReason"));

			borrowedIdColumn.setCellValueFactory(new PropertyValueFactory<EquipmentEntity, String>("id"));
			borrowedEquipNameColumn.setCellValueFactory(new PropertyValueFactory<EquipmentEntity, String>("name"));
			borrowedNoteColumn.setCellValueFactory(new PropertyValueFactory<EquipmentEntity, String>("note"));

			borrowIdColumn.setCellValueFactory(new PropertyValueFactory<EquipmentEntity, String>("id"));
			borrowEquipNameColumn.setCellValueFactory(new PropertyValueFactory<EquipmentEntity, String>("name"));
			borrowNoteColumn.setCellValueFactory(new PropertyValueFactory<EquipmentEntity, String>("note"));
			tableViewBorrow.setRowFactory(tv -> {
				TableRow<EquipmentEntity> row = new TableRow<>();
				row.setOnMouseClicked(event -> {
					if (event.getClickCount() == 2 && (!row.isEmpty())) {
						EquipmentEntity eqip = tableViewBorrow.getSelectionModel().getSelectedItem();
						listBorrowed.add(eqip);
						listBorrow.remove(eqip);
						hiddenAddEquipModal();

					}
				});
				return row;
			});
			tableViewBorrowed.setItems(listBorrowed);
			tableViewBorrow.setItems(listBorrow);
			updateTableBorrow();
			updateOperationEquip();
			updateTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void updateOperationEquip() {
		Callback<TableColumn<EquipmentEntity, String>, TableCell<EquipmentEntity, String>> cellFoctory = (
				TableColumn<EquipmentEntity, String> param) -> {
			// make cell containing buttons
			final TableCell<EquipmentEntity, String> cell = new TableCell<EquipmentEntity, String>() {
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
							EquipmentEntity test = (EquipmentEntity) getTableRow().getItem();
							System.out.println("delete now" + test);
							System.out.println(test.toString());
							listBorrowed.remove(test);
							listBorrow.add(test);

						});
						HBox managebtn = new HBox(deleteIcon);
						managebtn.setStyle("-fx-alignment:center");
						HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
						setGraphic(managebtn);
						setText(null);

					}
				}

			};
			return cell;
		};
		operationcolumn.setCellFactory(cellFoctory);
	}

	void updateTableBorrow() {
		Callback<TableColumn<PayBorrow, String>, TableCell<PayBorrow, String>> cellFoctory = (
				TableColumn<PayBorrow, String> param) -> {
			// make cell containing buttons
			final TableCell<PayBorrow, String> cell = new TableCell<PayBorrow, String>() {
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
							PayBorrow payBorrow = (PayBorrow) getTableRow().getItem();
							System.out.println("delete now" + payBorrow);
							PayBorrowService rss = PayBorrowService.getRepo();
							try {
								rss.delete(payBorrow);
								updateTable();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						});
						HBox managebtn = new HBox(deleteIcon);
						managebtn.setStyle("-fx-alignment:center");
						HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
						setGraphic(managebtn);
						setText(null);

					}
				}

			};
			return cell;
		};
		operationPayBorrowColumn.setCellFactory(cellFoctory);
	}

}
