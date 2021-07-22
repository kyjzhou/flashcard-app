package model;

import java.util.ArrayList;
import java.util.List;

// Represents a collection of flashcards having a title and a list of flashcards
public class FlashcardCollection {

    private String title;
    private List<Flashcard> flashcards;

    public FlashcardCollection(String title) {
        this.title = title;
        flashcards = new ArrayList<>();
    }

    // getter
    public String getTitle() {
        return title;
    }

    // getter
    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    // REQUIRES: title is not an empty string
    // MODIFIES: this
    // EFFECTS: edits this collection's title to newTitle
    public void editTitle(String newTitle) {
        title = newTitle;
    }

    // MODIFIES: this
    // EFFECTS: if not already in this collection, adds flashcard to this collection
    //          and returns true, otherwise returns false
    public boolean addFlashcard(Flashcard flashcard) {
        if (!flashcards.contains(flashcard)) {
            flashcards.add(flashcard);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: if already in this collection, deletes flashcard from this collection
    //          and returns true, otherwise returns false
    public boolean deleteFlashcard(Flashcard flashcard) {
        if (flashcards.contains(flashcard)) {
            flashcards.remove(flashcard);
            return true;
        }
        return false;
    }

    // REQUIRES: position >= 1
    // EFFECTS: returns the flashcard at position in the collection
    public Flashcard getFlashcardAtPosition(int position) {
        return flashcards.get(position - 1);
    }

}
