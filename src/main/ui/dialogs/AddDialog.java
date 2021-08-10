package ui.dialogs;

import ui.FlashcardGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// A dialog window for inputting a keyword and description when adding a new flashcard
// Modelled after DialogDemoProject from Java Tutorials for using Swing Components
//         (https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html)
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

    // EFFECTS: constructs an add flashcard dialog window for given GUI
    public AddDialog(FlashcardGUI gui) {
        super(gui, true);
        inputtedText1 = null;
        inputtedText2 = null;
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        this.gui = gui;

        setTitle("Add new flashcard");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        Object[] components = {FIRST_MESSAGE, textField1, SECOND_MESSAGE, textField2};
        Object[] options = {ENTER_BUTTON_TEXT, CANCEL_BUTTON_TEXT};

        initializeOptionPane(components, options);
        setContentPane(optionPane);

        textField1.addActionListener(this);
        textField2.addActionListener(this);
        optionPane.addPropertyChangeListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes this dialog's option pane
    private void initializeOptionPane(Object[] components, Object[] options) {
        optionPane = new JOptionPane(components,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                null,
                options,
                options[0]);
    }

    // MODIFIES: this
    // EFFECTS: sets this option pane's default value when this dialog is opened
    @Override
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(ENTER_BUTTON_TEXT);
    }

    // MODIFIES: this
    // EFFECTS: if enter button is clicked:
    //             - if at least one text field is empty, displays prompt asking for non-empty input
    //                          and closes this dialog
    //             - otherwise, adds a flashcard button with inputted keyword and description to this dialog's gui
    //                          and closes this dialog
    //          if cancel button is clicked, only closes this dialog
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object value = optionPane.getValue();

        if (value.equals(ENTER_BUTTON_TEXT)) {
            inputtedText1 = textField1.getText();
            inputtedText2 = textField2.getText();
            if (inputtedText1.equals("") || inputtedText2.equals("")) {
                hideDialog();
                JOptionPane.showMessageDialog(AddDialog.this, "Please enter a keyword and description");
            } else {
                gui.addFlashcardButton(inputtedText1, inputtedText2);
                hideDialog();
            }
        } else {
            hideDialog();
        }

    }

    // MODIFIES: this
    // EFFECTS: clears this dialog's text fields and hides this dialog
    public void hideDialog() {
        textField1.setText(null);
        textField2.setText(null);
        setVisible(false);
    }
}
