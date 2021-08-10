package ui.dialogs;

import model.Flashcard;
import ui.FlashcardGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// A dialog window for confirming to delete a flashcard
// Modelled after DialogDemoProject from Java Tutorials for using Swing Components
//         (https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html)
public class DeleteDialog extends JDialog implements ActionListener, PropertyChangeListener {
    private String inputtedText;
    private FlashcardGUI gui;
    private JOptionPane optionPane;
    private String messageSecondLine;
    private String messageThirdLine;

    private static final String OK_BUTTON_TEXT = "OK";
    private static final String CANCEL_BUTTON_TEXT = "Cancel";
    private static final String MESSAGE_FIRST_LINE = "This flashcard will be deleted";

    // EFFECTS: constructs a delete flashcard dialog window for given GUI
    public DeleteDialog(FlashcardGUI gui) {
        super(gui, true);
        inputtedText = null;
        this.gui = gui;

        Flashcard selectedCard = gui.getSelectedButton().getFlashcard();
        messageSecondLine = "Keyword: " + selectedCard.getKeyword();
        messageThirdLine = "Description: " + selectedCard.getDescription();

        setTitle("Delete flashcard");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        Object[] components = {MESSAGE_FIRST_LINE, messageSecondLine, messageThirdLine};
        Object[] options = {OK_BUTTON_TEXT, CANCEL_BUTTON_TEXT};

        initializeOptionPane(components, options);
        setContentPane(optionPane);
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
        optionPane.setValue(OK_BUTTON_TEXT);
    }

    // MODIFIES: this
    // EFFECTS: if OK button is clicked, deletes a flashcard button from this dialog's gui and closes this dialog,
    //          if cancel button is clicked, only closes this dialog
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object value = optionPane.getValue();

        if (value.equals(OK_BUTTON_TEXT)) {
            gui.deleteFlashcardButton();
        }
        hideDialog();
    }

    // EFFECTS: hides this dialog
    public void hideDialog() {
        setVisible(false);
    }
}
