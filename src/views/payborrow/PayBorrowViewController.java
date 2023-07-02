package views.payborrow;

import java.sql.Date;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.PayBorrowEntity;
import models.UserEntity;

public class PayBorrowViewController {

	@FXML
	private DatePicker borrowDate;

	@FXML
	private TextArea borrowReason;

	@FXML
	private TextField borrowUser;

	@FXML
	private TextField euipId;

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

	private String[] statusValues = { "PENDING", "BORROWING", "PAID" };

	@FXML
	private TextArea refuseReason;

	@FXML
	private Label roomId;

	@FXML
	void closeModal(ActionEvent event) {
		System.out.println("Close");
	}

	@FXML
	void submit(ActionEvent event) {
		System.out.println("Submit");
	}

	public void updateTable() {
		try {
			pbEquipments.getItems().clear();
			PayBorrowEntity pbs = new PayBorrow();
			for (PayBorrowEntity pb : pbs.getAll()) {
				PayBorrow newPb = new PayBorrow();
				newPb.setDisplayId(Integer.valueOf(pb.getId()));
				newPb.setDisplayStatus(pb.getStatus());
				newPb.setDisplayFromDate(pb.getFromDate());
				newPb.setDisplayToDate(pb.getToDate());
				newPb.setDisplayBorrowReason(pb.getBorrowReason());
				newPb.setDisplayRefuseReason(pb.getRefuseReason());
				UserEntity user = new UserEntity();
				for (UserEntity curUser : user.getAll()) {
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
			System.out.println("Init");
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
