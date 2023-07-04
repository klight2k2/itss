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
import utils.Utils;
import views.Links;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
public class LoginController {
    @FXML
    private Button loginBtn;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField userameField;

    @FXML
    void onSubmit(MouseEvent event) {
    	String password= passwordField.getText();
    	String username= userameField.getText();
    	if(password.equals("") || username.equals("")) {
    		Utils.createDialog(AlertType.WARNING,"Cảnh báo!","","Vui lòng nhập đầy đủ thông tin tài khoản và mật khẩu!");
    	}
    	
        try {
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(getClass().getResource(Links.HOMEVIEW));
			Scene scene = new Scene(loader.load());
			 stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    public static UserEntity currentUser = new UserEntity();
	 public boolean login(String userName, String password) throws SQLException, ClassNotFoundException{
	        
		 Connection connection = DB.getConnection();
	        
	        Statement st = connection.createStatement();
	        ResultSet rs = st.executeQuery("SELECT * FROM users WHERE userName = '" + userName +"'");
	        if (rs == null) {
	            return false;
	        }
	        while (rs.next()) {                
	            if (rs.getString("password") == null ? password == null : rs.getString("password").equals(password)) {
	                LoginController.currentUser.setId(rs.getInt("id"));
	                LoginController.currentUser.setName(rs.getString("userName"));
	                return true;
	            }
	        }
	        connection.close();
	        return false;
	    }
}
