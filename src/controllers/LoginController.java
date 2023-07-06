package controllers;

import javafx.stage.Stage;
import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.sql.*;

import models.UserEntity;
import models.db.DB;
import service.UserService;
import utils.Utils;
import views.Links;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import service.UserService;

public class LoginController {
	@FXML
	private Button loginBtn;

	@FXML
	private TextField passwordField;

	@FXML
	private TextField userameField;

	@FXML
	void onSubmit(MouseEvent event) {
		String password = passwordField.getText();
		String username = userameField.getText();
		if (password.equals("") || username.equals("")) {
			Utils.createDialog(AlertType.WARNING, "Cảnh báo", "",
					"Vui lòng nhập đầy đủ thông tin tài khoản và mật khẩu!");
		}
		UserService userService = UserService.getRepo();
		try {
			Boolean checkLogin=userService.checkLogin(username, password);
			if (!checkLogin) {
				Utils.createDialog(AlertType.ERROR, "Lỗi", "",
						"Tài khoản hoặc mật khẩu không chính xác, vui lòng thử lại!");
			} else {
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(Links.HOMEVIEW));
				Scene scene = new Scene(loader.load());
				stage.setScene(scene);
				System.out.println("USER"+currentUser.getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static UserEntity currentUser = new UserEntity();

}
