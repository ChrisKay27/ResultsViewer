package ui;

import database.DBConn;
import database.QueryParams;
import utils.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

/**
 * Created by Chris on 6/12/2017.
 */
public class ResultsViewer extends JFrame {

    private static final ExperimentParams DEFAULT_X_AXIS_PARAM = ExperimentParams.arrivalRate;
    private static final ExperimentParams DEFAULT_SERIES_PARAM = ExperimentParams.deadlockDetectionProtocol;

    private final JButton createGraph;

    private Map<ExperimentParams, DropDown<String>> expParamsToDD = new HashMap<>();
    private GraphWindow graphsPanel;

    private final DropDown<ExperimentParams> xAxisDD, seriesAxisDD;

    private final Slider xAxisMinSlider, xAxisMaxSlider;


    public ResultsViewer() {
        JPanel content = new JPanel();
        BoxLayout boxLayout = new BoxLayout(content, BoxLayout.Y_AXIS);
        content.setLayout(boxLayout);
        content.setMaximumSize(new Dimension(600, 800));

        JPanel topPanel = new JPanel();
        GridLayout topPanelLayout = new GridLayout(0, 2);
        topPanel.setLayout(topPanelLayout);

        JPanel paramsPanel = new JPanel();
        GridLayout experimentLayout = new GridLayout(0, 2);
        paramsPanel.setLayout(experimentLayout);


        xAxisDD = new DropDown<>("X Axis", ExperimentParams.getXAxisParams());
        xAxisDD.setSelectedItem(ExperimentParams.arrivalRate);
        xAxisDD.setDropdownPreferredSize(new Dimension(150, xAxisDD.getDropDown().getPreferredSize().height));
        xAxisDD.addActionListener(e -> {
            hideSelectedParamDropdowns();
        });
        topPanel.add(xAxisDD);


        seriesAxisDD = new DropDown<>("Series", ExperimentParams.getSeriesParams());
        seriesAxisDD.setSelectedItem(ExperimentParams.deadlockDetectionProtocol);
        seriesAxisDD.setDropdownPreferredSize(new Dimension(175, seriesAxisDD.getDropDown().getPreferredSize().height));
        seriesAxisDD.addActionListener(e -> {
            hideSelectedParamDropdowns();
        });
        topPanel.add(seriesAxisDD);


        xAxisMinSlider = new Slider("X Axis Min", 0, 1000);
        xAxisMinSlider.getSlider().setValue(0);
        topPanel.add(xAxisMinSlider);

        xAxisMaxSlider = new Slider("X Axis Max", 0, 1000);
        xAxisMaxSlider.getSlider().setValue(1000);
        topPanel.add(xAxisMaxSlider);

        content.add(topPanel);


        {
            for (ExperimentParams expParams : ExperimentParams.values()) {
                java.util.List<String> possibleValues = DBConn.getPossibleValues(expParams);

                if (ExperimentParams.isIntType(expParams))
                    possibleValues.sort(Comparator.comparingInt(Integer::valueOf));

                DropDown<String> DD = new DropDown<>(expParams.name(), possibleValues);
                DD.setDropdownPreferredSize(new Dimension(175, DD.getDropDown().getPreferredSize().height));
                paramsPanel.add(DD);
                expParamsToDD.put(expParams, DD);
            }


            expParamsToDD.get(DEFAULT_X_AXIS_PARAM).setEnabled(false);
            expParamsToDD.get(DEFAULT_SERIES_PARAM).setEnabled(false);


            createGraph = new JButton("Create Graph");
            createGraph.addActionListener(e -> {

                if (graphsPanel != null)
                    content.remove(graphsPanel);


                QueryParams queryParams = new QueryParams(xAxisDD.getSelection(), seriesAxisDD.getSelection());
                for (ExperimentParams expParam : expParamsToDD.keySet())
                    queryParams.getParamValues().put(expParam, expParamsToDD.get(expParam).getSelection());

                queryParams.setxAxisRange(xAxisMinSlider.getValue(), xAxisMaxSlider.getValue());

                graphsPanel = new GraphWindow(queryParams);

                content.add(graphsPanel);
                pack();
            });

            paramsPanel.add(createGraph);
            content.add(paramsPanel);


        }
        loadSettings();
        createGraph.doClick();
        setContentPane(content);

        pack();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveSettings();
            }
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void hideSelectedParamDropdowns() {
        expParamsToDD.values().forEach(dd -> dd.setEnabled(true));

        expParamsToDD.get(xAxisDD.getSelection()).setEnabled(false);
        expParamsToDD.get(seriesAxisDD.getSelection()).setEnabled(false);
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        new ResultsViewer();
    }


    private void saveSettings() {
        Map<String, String> settings = new HashMap<>();

        for (ExperimentParams expParam : expParamsToDD.keySet())
            settings.put(expParam.name(), expParamsToDD.get(expParam).getSelection());

        settings.put("xAxisDD", xAxisDD.getSelection().name());
        settings.put("seriesAxisDD", seriesAxisDD.getSelection().name());

        settings.put("xAxisMinSlider", xAxisMinSlider.getValue() + "");
        settings.put("xAxisMaxSlider", xAxisMaxSlider.getValue() + "");

        Settings.savePrefs("ResultsViewer", settings);
    }


    private void loadSettings() {

        List<String> keys = new ArrayList<>();

        for (ExperimentParams expParam : expParamsToDD.keySet())
            keys.add(expParam.name());

        keys.addAll(Arrays.asList("xAxisDD", "seriesAxisDD", "xAxisMinSlider", "xAxisMaxSlider"));

        Map<String, String> settings = Settings.getSettings("ResultsViewer", keys);


        for (ExperimentParams expParam : expParamsToDD.keySet()) {
            String setting = settings.get(expParam.name());

            if (!setting.isEmpty())
                expParamsToDD.get(expParam).setSelectedItem(setting);
        }


        String xAxisDDValue = settings.get("xAxisDD");
        String seriesAxisDDValue = settings.get("seriesAxisDD");
        String xAxisMinSliderValue = settings.get("xAxisMinSlider");
        String xAxisMaxSliderValue = settings.get("xAxisMaxSlider");

        if (!xAxisDDValue.isEmpty())
            xAxisDD.setSelectedItem(ExperimentParams.valueOf(xAxisDDValue));
        if (!seriesAxisDDValue.isEmpty())
            seriesAxisDD.setSelectedItem(ExperimentParams.valueOf(seriesAxisDDValue));
        if (!xAxisMinSliderValue.isEmpty())
            xAxisMinSlider.getSlider().setValue(Integer.parseInt(xAxisMinSliderValue));
        if (!xAxisMaxSliderValue.isEmpty())
            xAxisMaxSlider.getSlider().setValue(Integer.parseInt(xAxisMaxSliderValue));
    }

}
