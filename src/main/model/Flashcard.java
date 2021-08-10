package model;

import org.json.JSONObject;
import persistence.JsonWritable;

// A flashcard with a keyword and a description
public class Flashcard implements JsonWritable {

    private String keyword;
    private String description;

    // EFFECTS: constructs flashcard with given keyword and description
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

    // EFFECTS: returns this flashcard as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject jsonCard = new JSONObject();
        jsonCard.put("keyword", keyword);
        jsonCard.put("description", description);
        return jsonCard;
    }
}
