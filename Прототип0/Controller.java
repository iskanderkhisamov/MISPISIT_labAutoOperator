package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private Label label;

    @FXML
    private Label stack;

    public void btn(javafx.event.ActionEvent actionEvent) {
        Autooperator auto = new Autooperator();
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        for (double position : auto.P) {
            series.getData().add(new XYChart.Data<String, Number>
                    (String.valueOf(auto.a[(int) position]), position));
            series.getData().add(new XYChart.Data
                    <String, Number>(String.valueOf(auto.a[(int) position]), position + 1));
        }
        series.setName("Циклограмма");
        lineChart.getData().add(series);
        String stacking = "";
        for (int i = 0; i < auto.P.length; i++) {
            if (auto.P[i] == 5)
                stacking += auto.P[i] + " →  0" + "   ";
            else if (i == auto.P.length - 1)
                stacking += "0";
            else
                stacking += auto.P[i] + " → " + (auto.P[i] + 1) + "   ";
        }
        stack.setText("Очередность: " + stacking);
        label.setText("Эффективность (качество) Ф(t) расписания R(t): " + String.valueOf(auto.func));
    }
}
