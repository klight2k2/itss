package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NotificationUtil {
	public static void success(String title, String content) {
			Alert warning = new Alert(AlertType.INFORMATION);
			warning.setTitle(title);
			warning.setHeaderText("");
			warning.setContentText(content);
			warning.showAndWait();
	}
	public static void error(String title, String content) {
		Alert warning = new Alert(AlertType.ERROR);
		warning.setTitle(title);
		warning.setHeaderText("");
		warning.setContentText(content);
		warning.showAndWait();
}
	public static void warning(String title, String content) {
		Alert warning = new Alert(AlertType.WARNING);
		warning.setTitle(title);
		warning.setHeaderText("");
		warning.setContentText(content);
		warning.showAndWait();
}
	public static void confirm(String title, String content) {
		Alert warning = new Alert(AlertType.CONFIRMATION);
		warning.setTitle(title);
		warning.setHeaderText("");
		warning.setContentText(content);
		warning.showAndWait();
}
	
	  public static void main(String[] args) {
	        NotificationUtil  notify= new NotificationUtil();
	        notify.success("hello","xin chao con lon");
	    }
}
