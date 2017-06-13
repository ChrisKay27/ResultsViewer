package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 1/9/2017.
 */
public class DropDown<E> extends JPanel {


    private final JComboBox<E> comboBox;
    private final JLabel label;


    public DropDown(String labelText){
        this(labelText, new ArrayList<>());
    }


    public DropDown(String labelText, List<E> options) {
        this.label = new JLabel(labelText);
        this.comboBox = new JComboBox<>();
        DefaultComboBoxModel<E> model = new DefaultComboBoxModel<>();
        options.forEach(model::addElement);

        comboBox.setModel(model);
        comboBox.setPreferredSize(new Dimension(100,25));
        comboBox.setEditable(false);

        add(label);
        add(comboBox);

    }



    public void setLabelText(String t) {
        label.setText(t);
    }

    public String getLabelText() {
        return label.getText();
    }

    public String getText() {
        return comboBox.getSelectedItem().toString();
    }

    public E getSelection() {
        return (E) comboBox.getSelectedItem();
    }



    public void addItem(E item) {
        comboBox.addItem(item);
    }

    public void setSelectedItem(E e) {
         comboBox.setSelectedItem(e);
    }

    public Object getSelectedItem() {
        return comboBox.getSelectedItem();
    }

    public void setSelectedIndex(int anIndex) {
        comboBox.setSelectedIndex(anIndex);
    }

    @Transient
    public int getSelectedIndex() {
        return comboBox.getSelectedIndex();
    }

    public void removeItem(Object anObject) {
        comboBox.removeItem(anObject);
    }

    public void removeItemAt(int anIndex) {
        comboBox.removeItemAt(anIndex);
    }

    public void addActionListener(ActionListener l) {
        comboBox.addActionListener(l);
    }

    public void removeActionListener(ActionListener l) {
        comboBox.removeActionListener(l);
    }
//
//    public void setText(String t) {
//        textField.setText(t);
//    }
//
//    public String getText() {
//        return textField.getText();
//    }
//
//    public String getSelectedText() {
//        return textField.getSelectedText();
//    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        comboBox.setEnabled(enabled);
//        comboBox.getEditor().getEditorComponent().setEnabled(enabled);
    }

    public JComboBox<E> getDropDown() {
        return comboBox;
    }

    public void clear() {
        ((DefaultComboBoxModel<E>)comboBox.getModel()).removeAllElements();
    }

    public void addAll(List<? extends Object> items) {
        items.forEach(i -> ((DefaultComboBoxModel<E>)comboBox.getModel()).addElement((E)i));
    }

    public void setDropdownPreferredSize(Dimension size){
        comboBox.setPreferredSize(size);
    }
}