package ui;

import model.Flashcard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlashcardButton extends JButton {

    private Flashcard flashcard;
    private boolean isSelected;
    private FlashcardGUI gui;

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

    // TODO: use select and unselect somewhere
    public void select() {
        if (!isSelected) {
            isSelected = true;
        }
    }

    public void unselect() {
        if (isSelected) {
            isSelected = false;
        }
    }

    private class SelectButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gui.setSelectedButton(FlashcardButton.this);
            gui.actionPerformed(e);
        }
    }

}
