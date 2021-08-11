package ui.dialogs;

import ui.FlashcardGUI;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

// A dialog window for inputting a keyword and description when adding a new flashcard
// Modelled after DialogDemoProject from Java Tutorials for using Swing Components
//         (https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html)
public class AddDialog extends Dialog {
    private static final String FIRST_MESSAGE = "Enter this flashcard's keyword";
    private static final String SECOND_MESSAGE = "Enter this flashcard's description";

    private String inputtedText1;
    private String inputtedText2;
    private JTextField textField1;
    private JTextField textField2;

    // EFFECTS: constructs an add flashcard dialog window for given GUI
    public AddDialog(FlashcardGUI gui) {
        super(gui);
        inputtedText1 = null;
        inputtedText2 = null;
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);

        setTitle("Add new flashcard");

        Object[] components = {FIRST_MESSAGE, textField1, SECOND_MESSAGE, textField2};
        Object[] options = {ENTER_BUTTON_TEXT, CANCEL_BUTTON_TEXT};

        super.initializeOptionPane(components, options);
        setContentPane(optionPane);

        textField1.addActionListener(this);
        textField2.addActionListener(this);
        optionPane.addPropertyChangeListener(this);
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
    @Override
    public void hideDialog() {
        textField1.setText(null);
        textField2.setText(null);
        setVisible(false);
    }
}
