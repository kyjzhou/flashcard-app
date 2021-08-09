package ui.dialogs;

import ui.FlashcardGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoadDialog extends JDialog implements ActionListener, PropertyChangeListener {
    private String inputtedText;
    private JTextField textField;
    private FlashcardGUI gui;
    private JOptionPane optionPane;

    private static final String ENTER_BUTTON_TEXT = "Enter";
    private static final String CANCEL_BUTTON_TEXT = "Cancel";
    private static final String MESSAGE = "Enter the collection's title";

    public LoadDialog(FlashcardGUI gui) {
        super(gui, true);
        inputtedText = null;
        textField = new JTextField(10);
        this.gui = gui;

        setTitle("Load a collection");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        Object[] components = {MESSAGE, textField};
        Object[] options = {ENTER_BUTTON_TEXT, CANCEL_BUTTON_TEXT};

        initOptionPane(components, options);
        setContentPane(optionPane);

        textField.addActionListener(this);
        optionPane.addPropertyChangeListener(this);
    }

    private void initOptionPane(Object[] components, Object[] options) {
        optionPane = new JOptionPane(components,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                null,
                options,
                options[0]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(ENTER_BUTTON_TEXT);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object value = optionPane.getValue();
        // TODO: see if reset is necessary
        //optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);

        if (value.equals(ENTER_BUTTON_TEXT)) {
            inputtedText = textField.getText();
            if (inputtedText == null) {
                JOptionPane.showMessageDialog(LoadDialog.this, "Please enter a collection title");
            } else {
                gui.loadCollection(inputtedText);
                clearTextHideDialog();
            }
        } else {
            clearTextHideDialog();
        }
    }

    public void clearTextHideDialog() {
        textField.setText(null);
        setVisible(false);
    }
}
