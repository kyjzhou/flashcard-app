package persistence;

import model.FlashcardCollection;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Modelled based on JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a writer that writes a flashcard collection as JSON data to file
public class Writer {
    private PrintWriter writer;
    private String destination;
    private static final int INDENT = 4;

    // EFFECTS: constructs writer that writes to the destination filepath
    public Writer(String filepath) {
        destination = filepath;
    }

    // MODIFIES: this
    // EFFECTS: opens the writer, and if the destination file is not found, throws FileNotFoundException
    public void open() throws FileNotFoundException {
        File file = new File(destination);
        writer = new PrintWriter(file);
    }

    // MODIFIES: this
    // EFFECTS: writes collection as a JSON object to file
    public void write(FlashcardCollection collection) {
        JSONObject jsonCollection = collection.toJson();
        String jsonString = jsonCollection.toString(INDENT);
        writer.print(jsonString);
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();
    }
}
