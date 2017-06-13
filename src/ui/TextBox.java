package ui;

import utils.TextUtils;


import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by chris_000 on 3/15/2016.
 */
public class TextBox extends JPanel {
    private String propertyName;
    private final JTextField textField;
    private final JLabel label;

    public TextBox(String labelText, String textBoxText, int columns) {
        this.textField = new JTextField(textBoxText,columns);

        this.label = new JLabel(TextUtils.splitCamelCase(labelText));
        propertyName = labelText;

        add(label);

        add(textField);
    }

    //TODO add mode (only ints allowed etc)


    /**
     * MIN-MAX:INC
     * @return IF the textbox is in the format 4.2-5.2:0.2 then a list is returned with the elements 4.2,4.4,4.8,5,5.2
     */
    public List<Double> getDoubles() {
        String[] split = getText().split(":");
        String[] minMax = split[0].split("-");

        List<Double> values = new ArrayList<>();

        if( !getText().contains(":")){
            values.add(Double.parseDouble(getText()));
            return values;
        }

        double min = Double.parseDouble(minMax[0]);
        double max = Double.parseDouble(minMax[1]);
        double inc = Double.parseDouble(split[1]);

        for (double i = min; i <= max; i+=inc)
            values.add(i);

        return values;
    }

    /**
     * MIN-MAX:INC
     * @return IF the textbox is in the format 4-10:2 then a list is returned with the elements 4,6,8,10
     */
    public List<Integer> getInts() {

        String[] split = getText().split(":");
        String[] minMax = split[0].split("-");

        List<Integer> values = new ArrayList<>();

        if( !getText().contains(":")){
            values.add(Integer.parseInt(getText()));
            return values;
        }

        int min = Integer.parseInt(minMax[0]);
        int max = Integer.parseInt(minMax[1]);
        int inc = Integer.parseInt(split[1]);

        for (int i = min; i <= max; i+=inc)
            values.add(i);

        return values;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        textField.setEditable(enabled);
    }

    public JTextField getTextField() {
        return textField;
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

    public void setColumns(int columns) {
        textField.setColumns(columns);
    }

    public int getColumns() {
        return textField.getColumns();
    }

    public void addActionListener(ActionListener l) {
        textField.addActionListener(l);
    }

    public void removeActionListener(ActionListener l) {
        textField.removeActionListener(l);
    }

    public void setText(String t) {
        textField.setText(t);
    }

    public String getText() {
        return textField.getText();
    }

    public String getSelectedText() {
        return textField.getSelectedText();
    }

    public void setEditable(boolean b) {
        textField.setEditable(b);
    }

    public boolean isEditable() {
        return textField.isEditable();
    }

    public int getInt(){
        return Integer.parseInt(textField.getText());
    }

    public double getDouble() {
        return Double.parseDouble(getText());
    }

}