package controllers;

import java.sql.*;

import models.UserEntity;
import models.db.DB;

public class LoginController {
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
