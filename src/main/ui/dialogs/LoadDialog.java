package ui.dialogs;

import ui.FlashcardGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// A dialog window for inputting a collection title when loading a flashcard collection
// Modelled after DialogDemoProject from Java Tutorials for using Swing Components
//         (https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html)
public class LoadDialog extends JDialog implements ActionListener, PropertyChangeListener {
    private String inputtedText;
    private JTextField textField;
    private FlashcardGUI gui;
    private JOptionPane optionPane;

    private static final String ENTER_BUTTON_TEXT = "Enter";
    private static final String CANCEL_BUTTON_TEXT = "Cancel";
    private static final String MESSAGE = "Enter the collection's title";

    // EFFECTS: constructs a load collection dialog window for given GUI
    public LoadDialog(FlashcardGUI gui) {
        super(gui, true);
        inputtedText = null;
        textField = new JTextField(10);
        this.gui = gui;

        setTitle("Load a collection");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        Object[] components = {MESSAGE, textField};
        Object[] options = {ENTER_BUTTON_TEXT, CANCEL_BUTTON_TEXT};

        initializeOptionPane(components, options);
        setContentPane(optionPane);

        textField.addActionListener(this);
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
    //             - if this dialog's text field is empty, displays prompt asking for non-empty input
    //                          and closes this dialog
    //             - otherwise, loads flashcard collection with inputted title to this dialog's gui's collection title
    //                          and closes this dialog
    //          if cancel button is clicked, only closes this dialog
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object value = optionPane.getValue();

        if (value.equals(ENTER_BUTTON_TEXT)) {
            inputtedText = textField.getText();
            if (inputtedText.equals("")) {
                hideDialog();
                JOptionPane.showMessageDialog(LoadDialog.this, "Please enter a collection title");
            } else {
                gui.loadCollection(inputtedText);
                hideDialog();
            }
        } else {
            hideDialog();
        }
    }

    // MODIFIES: this
    // EFFECTS: clears this dialog's text field and hides this dialog
    public void hideDialog() {
        textField.setText(null);
        setVisible(false);
    }
}
