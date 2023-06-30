package views.home;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import views.Links;
import javafx.scene.input.MouseEvent;

public class HomeViewController {

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
    private Text userName;
    
    @FXML
    void onNavagate(MouseEvent event) {
    	  Button sourceButton = (Button) event.getSource();
    	  if(sourceButton.equals(equipmentBtn)){
              System.out.print("equipmentBtn");
              loadView(Links.EQUIPMENTVIEW);
          }

          else if(sourceButton == homeBtn){
              System.out.print("homeBtn");
              loadView(Links.HOMECONTENTVIEW);
          }

          else if(sourceButton == scheduleBtn){
              System.out.print("scheduleBtn");
              loadView(Links.SCHEDULEVIEW);
          }

    }
    
    public void loadView(String viewName) {
    	try {
    		AnchorPane pane= FXMLLoader.load(getClass().getResource(viewName));
    		content.getChildren().setAll(pane);
    	}catch(IOException ex) {
    		
    	}
    	
    	
    }

}
