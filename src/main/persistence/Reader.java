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
// Represents a reader that reads a flashcard collection stored as JSON data in file
public class Reader {
    private String source;

    // EFFECTS: constructs a reader that reads from source filepath
    public Reader(String filepath) {
        source = filepath;
    }

    // EFFECTS: reads and returns the flashcard collection read from file
    //          if there is an error with reading the collection, throws IOException
    public FlashcardCollection read() throws IOException {
        String jsonString = parseFileToString(source);
        JSONObject jsonCollection = new JSONObject(jsonString);
        return parseCollection(jsonCollection);
    }

    // EFFECTS: reads and returns file as a string
    //          if there is an error with reading the file, throws IOException
    private String parseFileToString(String file) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> lines = Files.lines(Paths.get(file), StandardCharsets.UTF_8)) {
            lines.forEach(line -> builder.append(line));
        }
        return builder.toString();
    }

    // MODIFIES: collection
    // EFFECTS: parses a flashcard collection from its JSON object
    private FlashcardCollection parseCollection(JSONObject jsonObject) {
        String collectionName = jsonObject.getString("name");
        FlashcardCollection collection = new FlashcardCollection(collectionName);
        addCards(collection, jsonObject);
        return collection;
    }

    // MODIFIES: collection
    // EFFECTS: adds flashcards to a flashcard collection parsed from its JSON object
    private void addCards(FlashcardCollection collection, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("flashcards");
        for (Object jsonFlashcard : jsonArray) {
            JSONObject nextFlashcard = (JSONObject) jsonFlashcard;
            addCard(collection, nextFlashcard);
        }
    }

    private void addCard(FlashcardCollection collection, JSONObject nextFlashcard) {
        String keyword = nextFlashcard.getString("keyword");
        String description = nextFlashcard.getString("description");
        Flashcard newFlashcard = new Flashcard(keyword, description);
        collection.addFlashcard(newFlashcard);
    }
}
