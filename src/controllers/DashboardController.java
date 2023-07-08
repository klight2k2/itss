package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

import common.StatisticContants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;
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
	@FXML
	private ComboBox<String> typeStatisticComboBox;
	@FXML
    private Text equipmentCountLabel;

    @FXML
    private Text reportCountLabel;

    @FXML
    private Text roomCountLabel;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		StatisticalService statisticRepo = StatisticalService.getRepo();
		
		try {
			roomCountLabel.setText(String.valueOf(statisticRepo.countRooms()));
			reportCountLabel.setText(String.valueOf(statisticRepo.countReport()));
			equipmentCountLabel.setText(String.valueOf(statisticRepo.countEquipment()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		ObservableList<String> typeStatistic = FXCollections.observableArrayList();
		typeStatistic.add(StatisticContants.STATUS_CATEGORY);
		typeStatistic.add(StatisticContants.BORROW_CATEGORY);
		typeStatisticComboBox.setValue(StatisticContants.STATUS_CATEGORY);
		typeStatisticComboBox.setItems(typeStatistic);

		XYChart.Series<String, Number> seriesEquipmentFixing = new XYChart.Series<>();
		XYChart.Series<String, Number> seriesEquipmentGood = new XYChart.Series<>();
		XYChart.Series<String, Number> seriesEquipmentBad = new XYChart.Series<>();
		XYChart.Series<String, Number> BorrowEquipment = new XYChart.Series<>();
		try {
			for (Map.Entry<String, Integer> category : statisticRepo.countBorrowedEquipmentByCategory().entrySet()) {
				BorrowEquipment.getData().add(new XYChart.Data<>(category.getKey(), category.getValue()));
			}
			borrowEquipmentBarChart.getData().addAll(BorrowEquipment);
			borrowEquipmentBarChart.setLegendVisible(false);
			borrowEquipmentBarChart.setVisible(false);

			for (Map.Entry<String, Integer[]> category : statisticRepo.getEquipmentStatusByCategory().entrySet()) {
				seriesEquipmentBad.getData().add(new XYChart.Data<>(category.getKey(), category.getValue()[0]));
				seriesEquipmentGood.getData().add(new XYChart.Data<>(category.getKey(), category.getValue()[1]));
				seriesEquipmentFixing.getData().add(new XYChart.Data<>(category.getKey(), category.getValue()[2]));
			}
			seriesEquipmentBad.setName("Đang hỏng");
			seriesEquipmentFixing.setName("Đang sửa");
			seriesEquipmentGood.setName("Tốt");
			equipmentBarChart.getData().addAll(seriesEquipmentGood, seriesEquipmentFixing, seriesEquipmentBad);

			ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList();
			for (Map.Entry<String, Integer> roomStatus : statisticRepo.countRoomByStatus().entrySet()) {

				PieChart.Data data = new PieChart.Data(roomStatus.getKey(), roomStatus.getValue());

				valueList.add(data);
			}

			PieChart pieChart = new PieChart(valueList);
			pieChart.getData().forEach(data -> {
				String percentage = String.valueOf((int) data.getPieValue()) + " phòng";
				Tooltip toolTip = new Tooltip(percentage);
				Tooltip.install(data.getNode(), toolTip);
			});
			roomPieChart.getData().setAll(valueList);
			roomPieChart.setStartAngle(30);
			roomPieChart.setLabelsVisible(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void changeStatisticTypeEquipment(ActionEvent event) {
		String selectedType = typeStatisticComboBox.getSelectionModel().getSelectedItem().toString();
		switch (selectedType) {
		case StatisticContants.BORROW_CATEGORY:
			equipmentBarChart.setVisible(false);
			borrowEquipmentBarChart.setVisible(true);

			break;
		case StatisticContants.STATUS_CATEGORY:
			equipmentBarChart.setVisible(true);
			borrowEquipmentBarChart.setVisible(false);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + selectedType);
		}
	}

}
