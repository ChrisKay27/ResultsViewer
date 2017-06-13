package ui;

import database.DBConn;
import javafx.util.Pair;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Chris on 6/12/2017.
 */
public class ResultsViewer extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        JPanel content = new JPanel();

        TextBox experimentNum = new TextBox("Exp Num:","1497324104119",10);
        content.add(experimentNum);


        DropDown<ExperimentParams> xAxisDD = new DropDown<>("X Axis" , Arrays.asList(ExperimentParams.values()));
        xAxisDD.setSelectedItem(ExperimentParams.arrivalRate);
        content.add(xAxisDD);


        DropDown<ExperimentParams> seriesAxisDD = new DropDown<>("Series" , Arrays.asList(ExperimentParams.values()));
        seriesAxisDD.setSelectedItem(ExperimentParams.UpdateRate);
        content.add(seriesAxisDD);

        JButton createGraph = new JButton("Create Graph");
        createGraph.addActionListener(e -> {
            GraphWindow.main(new String[]{experimentNum.getText(),xAxisDD.getSelection().name(), seriesAxisDD.getSelection().name() });
        });
        content.add(createGraph);


        frame.setContentPane(content);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
