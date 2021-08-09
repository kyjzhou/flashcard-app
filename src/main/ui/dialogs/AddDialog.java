package ui.dialogs;

import ui.FlashcardGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AddDialog extends JDialog implements ActionListener, PropertyChangeListener {
    private String inputtedText1;
    private String inputtedText2;
    private JTextField textField1;
    private JTextField textField2;
    private FlashcardGUI gui;
    private JOptionPane optionPane;

    private static final String ENTER_BUTTON_TEXT = "Enter";
    private static final String CANCEL_BUTTON_TEXT = "Cancel";
    private static final String FIRST_MESSAGE = "Enter this flashcard's keyword";
    private static final String SECOND_MESSAGE = "Enter this flashcard's description";

    public AddDialog(FlashcardGUI gui) {
        super(gui, true);
        inputtedText1 = null;
        inputtedText2 = null;
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        this.gui = gui;

        setTitle("Add new flashcard");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        Object[] components = {FIRST_MESSAGE, textField1, SECOND_MESSAGE, textField2};
        Object[] options = {ENTER_BUTTON_TEXT, CANCEL_BUTTON_TEXT};

        initOptionPane(components, options);
        setContentPane(optionPane);

        textField1.addActionListener(this);
        textField2.addActionListener(this);
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
            inputtedText1 = textField1.getText();
            inputtedText2 = textField2.getText();
            if (inputtedText1 == null || inputtedText2 == null) {
                JOptionPane.showMessageDialog(AddDialog.this, "Please enter a keyword and description");
            } else {
                gui.addFlashcardButton(inputtedText1, inputtedText2);
                clearTextHideDialog();
            }
        } else {
            clearTextHideDialog();
        }

    }

    public void clearTextHideDialog() {
        textField1.setText(null);
        textField2.setText(null);
        setVisible(false);
    }
}
