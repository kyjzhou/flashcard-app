package persistence;

import model.Flashcard;
import model.FlashcardCollection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Modelled based on JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a reader that reads a flashcard collection stored as a JSON object from file
public class Reader {
    private String source;

    // EFFECTS: constructs a reader that reads from the source filepath
    public Reader(String filepath) {
        source = filepath;
    }

    // EFFECTS: reads and returns a flashcard collection read from file
    //          throws IOException if there is an error with reading from file
    public FlashcardCollection read() throws IOException {
        String jsonString = parseFileToString(source);
        JSONObject jsonCollection = new JSONObject(jsonString);
        return parseCollection(jsonCollection);
    }

    // EFFECTS: reads and returns file as a string
    //          throws IOException if there is an error with reading file
    private String parseFileToString(String file) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> lines = Files.lines(Paths.get(file), StandardCharsets.UTF_8)) {
            lines.forEach(line -> builder.append(line));
        }
        return builder.toString();
    }

    // EFFECTS: parses a flashcard collection from jsonCollection
    private FlashcardCollection parseCollection(JSONObject jsonCollection) {
        String collectionName = jsonCollection.getString("name");
        FlashcardCollection collection = new FlashcardCollection(collectionName);
        addCards(collection, jsonCollection);
        return collection;
    }

    // MODIFIES: collection
    // EFFECTS: adds flashcards parsed from jsonCollection to collection
    private void addCards(FlashcardCollection collection, JSONObject jsonCollection) {
        JSONArray jsonArray = jsonCollection.getJSONArray("flashcards");
        for (Object jsonFlashcard : jsonArray) {
            JSONObject nextFlashcard = (JSONObject) jsonFlashcard;
            addCard(collection, nextFlashcard);
        }
    }

    // MODIFIES: collection
    // EFFECTS: adds flashcard parsed from jsonFlashcard to collection
    private void addCard(FlashcardCollection collection, JSONObject jsonFlashcard) {
        String keyword = jsonFlashcard.getString("keyword");
        String description = jsonFlashcard.getString("description");
        Flashcard newFlashcard = new Flashcard(keyword, description);
        collection.addFlashcard(newFlashcard);
    }
}
