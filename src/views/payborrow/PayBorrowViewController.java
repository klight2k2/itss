package views.payborrow;

import java.sql.Date;
import java.sql.SQLException;

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
import models.PayBorrowEntity;
import models.UserEntity;
import service.PayBorrowService;
import service.UserService;

public class PayBorrowViewController {

	@FXML
	private DatePicker borrowDate;

	@FXML
	private TextArea borrowReason;

	@FXML
	private TextField borrowUser;

	@FXML
	private TextField equipId;

	@FXML
	private DatePicker payDate;

	@FXML
	private AnchorPane pbDetailModal;

	@FXML
	private TableView<PayBorrow> pbEquipments;

	@FXML
	private TableColumn<PayBorrow, Date> pbBorrowDate;

	@FXML
	private TableColumn<PayBorrow, String> pbBorrowReason;

	@FXML
	private TableColumn<PayBorrow, String> pbBorrowUser;

//	@FXML
//	private TableColumn<PayBorrow, ?> pbEquipId;

	@FXML
	private TableColumn<PayBorrow, Integer> pbId;

	@FXML
	private TableColumn<PayBorrow, Date> pbPayDate;

	@FXML
	private TableColumn<PayBorrow, String> pbRefuseReason;

	@FXML
	private TableColumn<PayBorrow, String> pbStatus;

	@FXML
	private ChoiceBox<String> status;

	@FXML
	private Button deleteBtn;

	private String[] statusValues = { "PENDING", "BORROWING", "PAID" };

	@FXML
	private TextArea refuseReason;

	@FXML
	private Label modalTitle;

	private int pbCurId;

	@FXML
	void closeModal(ActionEvent event) {
		pbDetailModal.setVisible(false);
	}

	@FXML
	void openModal(ActionEvent event) {
		pbCurId = -1;
		deleteBtn.setVisible(false);
		equipId.setText("BD001");
		borrowUser.clear();
		status.setValue(null);
		borrowDate.setValue(null);
		payDate.setValue(null);
		borrowReason.clear();
		refuseReason.clear();

		pbDetailModal.setVisible(true);
	}

	@FXML
	void submit(ActionEvent event) {
		try {

			PayBorrowEntity pb = new PayBorrowEntity();
			PayBorrowService payBorrowRepo = PayBorrowService.getRepo();
			pb.setStatus(status.getValue());
			pb.setFromDate(Date.valueOf(borrowDate.getValue()));
			pb.setToDate(Date.valueOf(payDate.getValue()));
			pb.setBorrowReason(borrowReason.getText());
			pb.setRefuseReason(refuseReason.getText());
			if (pbCurId > 0) {
				pb.setId(pbCurId);
				payBorrowRepo.update(pb);
			} else {
				pb.setId(payBorrowRepo.getAll().size() + 1);
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
		PayBorrow clickedRow = pbEquipments.getSelectionModel().getSelectedItem();
		if (clickedRow == null)
			return;

		pbCurId = clickedRow.getDisplayId().intValue();
		deleteBtn.setVisible(true);
		equipId.setText("BD001");
		borrowUser.setText(clickedRow.getDisplayUsername());
		status.setValue(clickedRow.getDisplayStatus());
		borrowDate.setValue(clickedRow.getDisplayFromDate().toLocalDate());
		payDate.setValue(clickedRow.getDisplayToDate().toLocalDate());
		borrowReason.setText(clickedRow.getDisplayBorrowReason());
		refuseReason.setText(clickedRow.getDisplayRefuseReason());

		pbDetailModal.setVisible(true);
	}

	public void updateTable() {
		try {
			pbEquipments.getItems().clear();
			PayBorrowEntity pbs = new PayBorrow();
			PayBorrowService payBorrowRepo = PayBorrowService.getRepo();
			UserService userRepo = UserService.getRepo();
			for (PayBorrowEntity pb : payBorrowRepo.getAll()) {
				PayBorrow newPb = new PayBorrow();
				newPb.setDisplayId(Integer.valueOf(pb.getId()));
				newPb.setDisplayStatus(pb.getStatus());
				newPb.setDisplayFromDate(pb.getFromDate());
				newPb.setDisplayToDate(pb.getToDate());
				newPb.setDisplayBorrowReason(pb.getBorrowReason());
				newPb.setDisplayRefuseReason(pb.getRefuseReason());
				UserEntity user = new UserEntity();
				for (UserEntity curUser : userRepo.getAll()) {
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

	public void initialize() {
		try {
			pbDetailModal.setVisible(false);
			status.getItems().addAll(statusValues);

			pbId.setCellValueFactory(new PropertyValueFactory<PayBorrow, Integer>("displayId"));
			pbBorrowUser.setCellValueFactory(new PropertyValueFactory<PayBorrow, String>("displayUsername"));
			pbStatus.setCellValueFactory(new PropertyValueFactory<PayBorrow, String>("displayStatus"));
			pbBorrowDate.setCellValueFactory(new PropertyValueFactory<PayBorrow, Date>("displayFromDate"));
			pbPayDate.setCellValueFactory(new PropertyValueFactory<PayBorrow, Date>("displayToDate"));
			pbBorrowReason.setCellValueFactory(new PropertyValueFactory<PayBorrow, String>("displayBorrowReason"));
			pbRefuseReason.setCellValueFactory(new PropertyValueFactory<PayBorrow, String>("displayRefuseReason"));

			updateTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
