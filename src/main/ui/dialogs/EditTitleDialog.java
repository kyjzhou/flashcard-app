package ui.dialogs;

import ui.FlashcardGUI;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

// A dialog window for inputting a new title when editing a flashcard collection's title
// Modelled after DialogDemoProject from Java Tutorials for using Swing Components
//         (https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html)
public class EditTitleDialog extends Dialog {
    private String inputtedText;
    private JTextField textField;

    private static final String MESSAGE = "Enter a new title for this collection";

    // EFFECTS: constructs an edit collection title dialog window for given GUI
    public EditTitleDialog(FlashcardGUI gui) {
        super(gui);
        inputtedText = null;
        textField = new JTextField(10);

        setTitle("Edit collection title");

        Object[] components = {MESSAGE, textField};
        Object[] options = {ENTER_BUTTON_TEXT, CANCEL_BUTTON_TEXT};

        super.initializeOptionPane(components, options);
        setContentPane(optionPane);

        textField.addActionListener(this);
        optionPane.addPropertyChangeListener(this);
    }

    // MODIFIES: this
    // EFFECTS: if enter button is clicked:
    //             - if this dialog's text field is empty, displays prompt asking for non-empty input
    //                       and closes this dialog
    //             - otherwise, sets this dialog's gui's collection title to inputted title and closes this dialog
    //          if cancel button is clicked, only closes this dialog
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object value = optionPane.getValue();

        if (value.equals(ENTER_BUTTON_TEXT)) {
            inputtedText = textField.getText();
            if (inputtedText.equals("")) {
                hideDialog();
                JOptionPane.showMessageDialog(EditTitleDialog.this, "Please enter a valid title");
            } else {
                gui.editCollectionTitle(inputtedText);
                hideDialog();
            }
        } else {
            hideDialog();
        }
    }

    // MODIFIES: this
    // EFFECTS: clears this dialog's text field and hides this dialog
    @Override
    public void hideDialog() {
        textField.setText(null);
        setVisible(false);
    }
}
