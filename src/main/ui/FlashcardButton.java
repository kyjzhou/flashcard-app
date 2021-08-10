package ui;

import model.Flashcard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A button that represents a flashcard
// Class structure based on ButtonDemoProject from Java Tutorials for using Swing Components
//       (https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html)
public class FlashcardButton extends JButton {

    private Flashcard flashcard;
    private boolean isSelected;
    private FlashcardGUI gui;

    // EFFECTS: constructs a flashcard button representing given flashcard on given GUI
    public FlashcardButton(Flashcard f, FlashcardGUI gui) {
        super(f.getDescription());
        flashcard = f;
        this.gui = gui;
        isSelected = false;
        addActionListener(new SelectButtonListener());
    }

    // getter
    public Flashcard getFlashcard() {
        return flashcard;
    }

    // getter
    public boolean isSelected() {
        return isSelected;
    }

    // MODIFIES: this
    // EFFECTS: if not selected, selects this button
    public void select() {
        if (!isSelected) {
            isSelected = true;
        }
    }

    // MODIFIES: this
    // EFFECTS: if selected, unselects this button
    public void unselect() {
        if (isSelected) {
            isSelected = false;
        }
    }

    // An action listener for this button
    private class SelectButtonListener implements ActionListener {

        // MODIFIES: FlashcardButton.this
        // EFFECTS: sets this flashcard button as its gui's selected button when clicked
        //          and invokes its gui's action
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.setSelectedButton(FlashcardButton.this);
            gui.actionPerformed(e);
        }
    }

}
