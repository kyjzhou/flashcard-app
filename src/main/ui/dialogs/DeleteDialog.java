package ui.dialogs;

import model.Flashcard;
import ui.FlashcardGUI;

import java.beans.PropertyChangeEvent;

// A dialog window for confirming to delete a flashcard
// Modelled after DialogDemoProject from Java Tutorials for using Swing Components
//         (https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html)
public class DeleteDialog extends Dialog {
    private String messageSecondLine;
    private String messageThirdLine;

    private static final String MESSAGE_FIRST_LINE = "This flashcard will be deleted";

    // EFFECTS: constructs a delete flashcard dialog window for given GUI
    public DeleteDialog(FlashcardGUI gui) {
        super(gui);

        Flashcard selectedCard = gui.getSelectedButton().getFlashcard();
        messageSecondLine = "Keyword: " + selectedCard.getKeyword();
        messageThirdLine = "Description: " + selectedCard.getDescription();

        setTitle("Delete flashcard");

        Object[] components = {MESSAGE_FIRST_LINE, messageSecondLine, messageThirdLine};
        Object[] options = {ENTER_BUTTON_TEXT, CANCEL_BUTTON_TEXT};

        super.initializeOptionPane(components, options);
        setContentPane(optionPane);
        optionPane.addPropertyChangeListener(this);
    }

    // MODIFIES: this
    // EFFECTS: if OK button is clicked, deletes a flashcard button from this dialog's gui and closes this dialog,
    //          if cancel button is clicked, only closes this dialog
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object value = optionPane.getValue();

        if (value.equals(ENTER_BUTTON_TEXT)) {
            gui.deleteFlashcardButton();
        }
        hideDialog();
    }

    // EFFECTS: hides this dialog
    @Override
    public void hideDialog() {
        setVisible(false);
    }
}
