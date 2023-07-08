package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import service.StatisticalService;

public class DashboardController implements Initializable {

	@FXML
	private Pane pane;
	@FXML
	private BarChart equipmentBarChart;
	
	   @FXML
	    private PieChart roomPieChart;
		@FXML
		private BarChart borrowEquipmentBarChart;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		XYChart.Series<String, Number> seriesEquipmentFixing= new XYChart.Series<>();

		XYChart.Series<String, Number> seriesEquipmentGood = new XYChart.Series<>();
		XYChart.Series<String, Number> seriesEquipmentBad = new XYChart.Series<>();
		XYChart.Series<String, Number> BorrowEquipment = new XYChart.Series<>();
		StatisticalService statisticRepo= StatisticalService.getRepo();
		 try {
			for (Map.Entry<String,Integer[]> category:statisticRepo.getEquipmentStatsByCategory().entrySet()) {
				seriesEquipmentBad.getData().add(new XYChart.Data<>(category.getKey(), category.getValue()[0] ));
				seriesEquipmentGood.getData().add(new XYChart.Data<>(category.getKey(), category.getValue()[1] ));
				seriesEquipmentFixing.getData().add(new XYChart.Data<>(category.getKey(), category.getValue()[2] ));
			}
			for (Map.Entry<String,Integer> category:statisticRepo.countBorrowedEquipmentByCategory().entrySet()) {
				BorrowEquipment.getData().add(new XYChart.Data<>(category.getKey(), category.getValue() ));
			}
			
			borrowEquipmentBarChart.getData().addAll(BorrowEquipment);
			seriesEquipmentBad.setName("Đang hỏng");
			seriesEquipmentFixing.setName("Đang sửa");
			seriesEquipmentGood.setName("Tốt");
			 ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList();
			 for (Map.Entry<String,Integer> roomStatus:statisticRepo.getStatsRoomStatus().entrySet()) {
				 
				 PieChart.Data data = new PieChart.Data(roomStatus.getKey(), roomStatus.getValue());
				 
				 valueList.add(data);
			 }
			 
			 PieChart pieChart = new PieChart(valueList);
					 pieChart.getData().forEach(data -> {
					 String percentage =  String.valueOf((int)data.getPieValue())+" phòng";
					 Tooltip toolTip = new Tooltip(percentage);
					 Tooltip.install(data.getNode(), toolTip);
					});
					 roomPieChart.setTitle("Tình trạng phòng học");
			roomPieChart.getData().setAll(valueList);
			roomPieChart.setStartAngle(30);
			roomPieChart.setLabelsVisible(false);
			equipmentBarChart.getData().addAll(seriesEquipmentGood,seriesEquipmentFixing,seriesEquipmentBad);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
