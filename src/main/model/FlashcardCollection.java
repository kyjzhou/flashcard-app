package model;

import java.util.ArrayList;
import java.util.List;

// A collection of flashcards
public class FlashcardCollection {

    private List<Flashcard> flashcardList;

    public FlashcardCollection() {
        flashcardList = new ArrayList<>();
    }

    // EFFECTS: returns size of this flashcard collection
    public int getSize() {
        return flashcardList.size();
    }

    // REQUIRES: position >= 1
    // EFFECTS: returns the flashcard at position in the collection
    public Flashcard getFlashcardAtPosition(int position) {
        return flashcardList.get(position - 1);
    }

    // MODIFIES: this
    // EFFECTS: if not already in this collection, adds flashcard to this collection
    //          and returns true, otherwise returns false
    public boolean addFlashcard(Flashcard flashcard) {
        if (!flashcardList.contains(flashcard)) {
            flashcardList.add(flashcard);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: if already in this collection, deletes flashcard from this collection
    //          and returns true, otherwise returns false
    public boolean deleteFlashcard(Flashcard flashcard) {
        if (flashcardList.contains(flashcard)) {
            flashcardList.remove(flashcard);
            return true;
        }
        return false;
    }

}
