package model;

// Represents a flashcard having a keyword and a description
public class Flashcard {

    private String keyword;
    private String description;

    public Flashcard(String keyword) {
        this.keyword = keyword;
        description = "No description yet";
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
