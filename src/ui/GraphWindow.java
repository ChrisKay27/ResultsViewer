package ui;

import database.DBConn;
import database.QueryParams;
import javafx.util.Pair;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Chris on 6/12/2017.
 */
public class GraphWindow extends JPanel {

    public GraphWindow(QueryParams queryParams) {


        ExperimentParams xAxis = queryParams.getXAxisParam();
        ExperimentParams seriesParam = queryParams.getSeriesParam();

        List<String> possibleValues = DBConn.getPossibleValues( seriesParam );




        String[] yValues = {"PCOT", "messageOverHeadIncurred"};

        for (String yValue : yValues) {

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();


            for (String possibleValue : possibleValues) {

                String query = "SELECT AVG("+yValue+"), "+xAxis+" \nFROM results \n" +
                        "WHERE "+seriesParam+" = '"+possibleValue+"' ";


                Map<ExperimentParams, String> paramValues = queryParams.getParamValues();
                for (ExperimentParams expParam : paramValues.keySet()) {

                    if( expParam != queryParams.getSeriesParam() && expParam != queryParams.getXAxisParam() ){

                        query += "\n AND " + expParam.name() + " = '" + paramValues.get(expParam) + "' ";
                    }
                }

                //Add x axis range value
                query += "\n AND " + xAxis + " BETWEEN " + queryParams.getxAxisRange().getxMin() + " AND " + queryParams.getxAxisRange().getxMax() ;


                query += "\n GROUP BY "+xAxis+"\n ORDER BY "+xAxis+";";


                List<Pair<Double, Double>> series = DBConn.getSeries(query);


                series.forEach(p -> dataset.addValue(p.getKey(),possibleValue,p.getValue()+""));
            }

            JFreeChart lineChart = ChartFactory.createLineChart(yValue + " vs " + xAxis + " vs " + seriesParam, xAxis.name(), yValue, dataset);

            ChartPanel cPanel = new ChartPanel(lineChart);

            add(cPanel);
        }
    }
}
