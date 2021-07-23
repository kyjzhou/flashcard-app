package model;

// A flashcard with a keyword and a description
public class Flashcard {

    private String keyword;
    private String description;

    public Flashcard(String keyword, String description) {
        this.keyword = keyword;
        this.description = description;
    }

    // getter for this flashcard's keyword
    public String getKeyword() {
        return keyword;
    }

    // getter for this flashcard's description
    public String getDescription() {
        return description;
    }

    // MODIFIES: this
    // EFFECTS: changes this flashcard's keyword to newKeyword
    public void editKeyword(String newKeyword) {
        keyword = newKeyword;
    }

    // MODIFIES: this
    // EFFECTS: changes this flashcard's description to newDescription
    public void editDescription(String newDescription) {
        description = newDescription;
    }
}
