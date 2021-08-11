package ui.dialogs;

import ui.FlashcardGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

// A dialog window for handling user actions
// Modelled after DialogDemoProject from Java Tutorials for using Swing Components
//         (https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html)
public abstract class Dialog extends JDialog implements ActionListener, PropertyChangeListener {
    protected static final String ENTER_BUTTON_TEXT = "Enter";
    protected static final String CANCEL_BUTTON_TEXT = "Cancel";

    protected FlashcardGUI gui;
    protected JOptionPane optionPane;

    // EFFECTS: constructs a dialog window for given GUI
    public Dialog(FlashcardGUI gui) {
        super(gui, true);
        this.gui = gui;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: initializes this dialog's option pane
    protected void initializeOptionPane(Object[] components, Object[] options) {
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

    // EFFECTS: hides this dialog
    public abstract void hideDialog();
}
