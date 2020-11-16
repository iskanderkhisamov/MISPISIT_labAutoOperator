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

    @FXML
    private Label tLabel;

    public void btn(javafx.event.ActionEvent actionEvent) {
        Autooperator auto = new Autooperator();
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        int count = 0;
        for (double position : auto.P) {
            if (position == 6) {
                series.getData().add(new XYChart.Data<String, Number>
                        (String.valueOf((int) auto.a[(int) position]), position));
                series.getData().add(new XYChart.Data
                        <String, Number>(String.valueOf((int) auto.a[(int) position]), 0));
            } else {
                series.getData().add(new XYChart.Data<String, Number>
                        (String.valueOf((int) auto.a[(int) position]), position));
                series.getData().add(new XYChart.Data
                        <String, Number>(String.valueOf((int) auto.a[(int) position]), position + 1));
            }
        }
        series.setName("Циклограмма");
        lineChart.getData().add(series);
        String stacking = "";
        for (int i = 0; i < auto.P.length; i++) {
            if (auto.P[i] == 6)
                stacking += auto.P[i] + " → 0" + "   ";
            else
                stacking += auto.P[i] + " → " + (auto.P[i] + 1) + "   ";
        }
        tLabel.setText("Технологический маршрут обработки каждого изделий: " + (int) auto.t[0] + ", " + (int) auto.t[1]
                + ", " + (int) auto.t[2] + ", " + (int) auto.t[3] + ", " + (int) auto.t[4] + ", " + (int) auto.t[5]
                + ", " + (int) auto.t[6]);
        stack.setText("Очередность: " + stacking + "  0");
        label.setText("Эффективность (качество) Ф(t) расписания R(t): " + auto.func);
    }
}
