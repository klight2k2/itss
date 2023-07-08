package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import models.UserEntity;
import service.UserService;
import utils.NotificationUtil;

import java.sql.SQLException;

import common.Role;

public class SignUpController {

	@FXML
	private RadioButton isAdmin;

	@FXML
	private RadioButton isTeacher;

	@FXML
	private Button signUpButton;

	@FXML
	private PasswordField signUpPassword;

	@FXML
	private TextField signUpUsername;
	@FXML
	private TextField signUpName;

	@FXML
	void handleSignUp(ActionEvent event) {
		String inputUsername = signUpUsername.getText();
		String inputPassword = signUpPassword.getText();
		String inputName=signUpName.getText();
		String role="";

		if (!isAdmin.isSelected() && !isTeacher.isSelected()) {
			NotificationUtil.warning("Tạo tài khoản mới", "Vui lòng chọn vai trò cho người dùng!");
			return;
		} else {
			if (isTeacher.isSelected())
				role = Role.ADMIN;
			if (isAdmin.isSelected())
				role = Role.TEACHER;
		}
		if(inputPassword.equals("") || inputUsername.equals("") || inputName.equals("")) {
			NotificationUtil.warning("Tạo tài khoản mới", "Vui lòng điền đầy đủ thông tin!");
			return;
		}
		if(inputPassword.length()<6) {
			NotificationUtil.warning("Tạo tài khoản mới", "Mật khẩu tối thiếu phải có 6 kí tự!");
			return;
		}
		try {
			UserService.getRepo().save(new UserEntity(inputName,inputUsername,inputPassword,role));
			NotificationUtil.success("Tạo tài khoản", "Bạn đã tạo tài khoản thành công");
//			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
