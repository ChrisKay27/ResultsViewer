package ui;

import utils.TextUtils;


import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by chris_000 on 3/15/2016.
 */
public class Slider extends JPanel {
    private String propertyName;
    private final JSlider slider;
    private final JLabel label;

    public Slider(String labelText, int min, int max) {
        slider = new JSlider(min,max);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(200);
        slider.setMinorTickSpacing(50);
        slider.setPaintTicks(true);

        label = new JLabel(TextUtils.splitCamelCase(labelText));
        propertyName = labelText;

        add(label);
        add(slider);
    }




    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        slider.setEnabled(enabled);
    }

    public JSlider getSlider() {
        return slider;
    }

    public JLabel getLabel() {
        return label;
    }

    public String getLabelText() {
        return label.getText();
    }
    public void setLabelText(String t) {
        label.setText(t);
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    public String getPropertyName() {
        return propertyName;
    }



    public int getValue(){
        return slider.getValue();
    }



}