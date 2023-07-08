package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.Role;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.UserService;
import utils.NotificationUtil;
import views.Links;

public class HomeViewController implements Initializable {

	@FXML
	private Text pageTitle;
	@FXML
	private ImageView avatar;

	@FXML
	private AnchorPane content;

	@FXML
	private Button equipmentBtn;

	@FXML
	private Button homeBtn;

	@FXML
	private Button logoutBtn;

	@FXML
	private Button roomBtn;

	@FXML
	private Button scheduleBtn;

	@FXML
	private Button borrowBtn;

	@FXML
	private Button reportBtn;

	@FXML
	private Button signUpBtn;

	@FXML
	private Text userName;

	private Button selectedBtn;

	@FXML
	void onNavagate(MouseEvent event) {
		selectedBtn.getStyleClass().remove("active");
		Button sourceButton = (Button) event.getSource();
		selectedBtn = sourceButton;
		selectedBtn.getStyleClass().add("active");
		if (sourceButton.equals(equipmentBtn)) {
			System.out.print("equipmentBtn");
			loadView(Links.EQUIPMENTVIEW);
			pageTitle.setText("Thiết bị");
		}

		else if (sourceButton == homeBtn) {
			System.out.print("homeBtn");
			loadView(Links.HOMECONTENTVIEW);
			pageTitle.setText("Trang chủ");
		}

		else if (sourceButton == scheduleBtn) {
			System.out.print("scheduleBtn");
			loadView(Links.SCHEDULEVIEW);
			pageTitle.setText("Lịch sử dụng phòng");
		}

		else if (sourceButton == roomBtn) {
			System.out.print("roomBtn");
			loadView(Links.ROOMVIEW);
			pageTitle.setText("Phòng học");
		}

		else if (sourceButton == borrowBtn) {
			System.out.print("borrowBtn");
			loadView(Links.PAYBORROWVIEW);
			pageTitle.setText("Mượn trả thiết bị");
		} else if (sourceButton == signUpBtn) {
			if (!UserService.getRepo().isAdmin()) {
				NotificationUtil.warning("Cảnh báo", "Bạn không có quyền thực hiện chức năng này!");
				return;
			}
			System.out.print("signUpBtn");
			loadView(Links.SIGN_UP_VIEW);
			pageTitle.setText("");
		}

		else if (sourceButton == reportBtn) {
			System.out.print("reportBtn");
			loadView(Links.REPORTVIEW);
			pageTitle.setText("Báo cáo cơ sở vật chất");
		} else if (sourceButton == logoutBtn) {
			System.out.print("logoutBtn");
			try {
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(Links.LOGIN_VIEW));
				Scene scene;
				scene = new Scene(loader.load());
				stage.setScene(scene);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void loadView(String viewName) {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource(viewName));
			content.getChildren().setAll(pane);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.selectedBtn = homeBtn;
		UserService userRepo= UserService.getRepo();
		this.loadView(Links.HOMECONTENTVIEW);
		selectedBtn.getStyleClass().add("active");

		userName.setText(userRepo.getCurrentUsername());

	}

}
