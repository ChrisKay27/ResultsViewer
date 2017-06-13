package ui;

import database.DBConn;
import javafx.util.Pair;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;

/**
 * Created by Chris on 6/12/2017.
 */
public class GraphWindow extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        long experimentNumber = Long.parseLong(args[0]);
        String xAxis = args[1];
        String seriesParam = args[2];


        DefaultCategoryDataset dataset = new DefaultCategoryDataset();


        List<String> possibleValues = DBConn.getPossibleValues(experimentNumber, seriesParam);

        for (String possibleValue : possibleValues) {
            List<Pair<Double, Double>> series = DBConn.getSeries(
                    "SELECT AVG(PCOT), "+xAxis+" FROM results " +
                            "WHERE "+seriesParam+" like '"+possibleValue+"' AND experimentNumber = "+experimentNumber+" GROUP BY "+xAxis+" ORDER BY "+xAxis+";");
            series.forEach(p -> dataset.addValue(p.getKey(),possibleValue,p.getValue()+""));
        }


        JFreeChart lineChart = ChartFactory.createLineChart(xAxis + " VS PCOT VS " + seriesParam, xAxis, "PCOT", dataset);

        ChartPanel cPanel = new ChartPanel(lineChart);
        frame.setContentPane(cPanel);
        frame.setSize(1000,600);
        frame.setVisible(true);
    }
}
