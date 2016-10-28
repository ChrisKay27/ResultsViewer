import javafx.util.Pair;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;

/**
 * Created by Chris on 7/1/2016.
 */
public class ArrivalRate_VS_PCOT_VS_MaxActive extends JFrame{

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        long experimentNumber = 1477618496227L;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); //AND experimentNumber = 1467483185469


        List<Pair<Double, Double>> series = DBConn.getSeries("SELECT AVG(PCOT), arrivalRate FROM results WHERE maxActiveTrans = 10 AND experimentNumber = "+experimentNumber+" GROUP BY arrivalRate ORDER BY arrivalRate;");
        series.forEach(p -> dataset.addValue(p.getKey(),"maxActiveTrans 10",p.getValue()+""));


        series = DBConn.getSeries("SELECT AVG(PCOT), arrivalRate FROM results WHERE maxActiveTrans = 30 AND experimentNumber = "+experimentNumber+" GROUP BY arrivalRate ORDER BY arrivalRate;");
        series.forEach(p -> dataset.addValue(p.getKey(),"maxActiveTrans 30",p.getValue()+""));


        JFreeChart lineChart = ChartFactory.createLineChart("Results", "Arrival Rate", "PCOT", dataset);

        ChartPanel cPanel = new ChartPanel(lineChart);
        frame.setContentPane(cPanel);
        frame.setSize(1000,600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }



}
