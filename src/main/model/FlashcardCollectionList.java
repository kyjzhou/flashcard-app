package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of flashcard collections
public class FlashcardCollectionList {

    private List<FlashcardCollection> collections;

    public FlashcardCollectionList() {
        collections = new ArrayList<>();
    }

    // getter
    public List<FlashcardCollection> getCollections() {
        return collections;
    }

    // MODIFIES: this
    // EFFECTS: if not already in this list of flashcard collections, adds collection to this list
    //          and returns true, otherwise returns false
    public boolean addCollection(FlashcardCollection collection) {
        if (!collections.contains(collection)) {
            collections.add(collection);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: if already in this list of flashcard collections, deletes collection from this list
    //          and returns true, otherwise returns false
    public boolean deleteCollection(FlashcardCollection collection) {
        if (collections.contains(collection)) {
            collections.remove(collection);
            return true;
        }
        return false;
    }

    // EFFECTS: returns a list of all flashcard collection titles in this list
    public List<String> getCollectionTitles() {
        List<String> titles = new ArrayList<>();
        for(FlashcardCollection collection : collections) {
            titles.add(collection.getTitle());
        }
        return titles;
    }
}
