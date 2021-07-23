package model;

// Represents a flashcard having a keyword and a description
public class Flashcard {

    private String keyword;
    private String description;

    public Flashcard(String keyword, String description) {
        this.keyword = keyword;
        this.description = description;
    }

    // getter
    public String getKeyword() {
        return keyword;
    }

    // getter
    public String getDescription() {
        return description;
    }

    // MODIFIES: this
    // EFFECTS: edits this flashcard's keyword to newKeyword
    public void editKeyword(String newKeyword) {
        keyword = newKeyword;
    }

    // MODIFIES: this
    // EFFECTS: edits this flashcard's description to newDescription
    public void editDescription(String newDescription) {
        description = newDescription;
    }
}
