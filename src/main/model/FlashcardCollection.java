package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonWritable;

import java.util.ArrayList;
import java.util.List;

// A collection of flashcards
public class FlashcardCollection implements JsonWritable {
    private String name;
    private List<Flashcard> flashcardList;

    public FlashcardCollection(String name) {
        this.name = name;
        flashcardList = new ArrayList<>();
    }

    // EFFECTS: returns name of this flashcard collection
    public String getName() {
        return name;
    }

    // MODIFIES: THIS
    // EFFECTS: sets this flashcard collection's name to newName
    public void setName(String newName) {
        this.name = newName;
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

    // EFFECTS: returns this flashcard collection as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject jsonCollection = new JSONObject();
        jsonCollection.put("name", name);
        jsonCollection.put("flashcards", toJsonFlashcards());
        return jsonCollection;
    }

    // EFFECTS: returns this collection's list of flashcards as a JSON array
    private JSONArray toJsonFlashcards() {
        JSONArray jsonFlashcardList = new JSONArray();
        for (Flashcard flashcard : flashcardList) {
            jsonFlashcardList.put(flashcard.toJson());
        }
        return jsonFlashcardList;
    }
}
