package persistence;

import model.Flashcard;
import model.FlashcardCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Modelled based on JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Tests for Writer
public class WriterTest {

    @Test
    void testWriteFileInvalidFormat() {
        String destination = "./data/invalidFileFormat.?json";
        Writer writer = new Writer(destination);
        try {
            writer.open();
            fail("Exception not thrown");
        } catch (IOException e) {
            // Exception expected to be caught
        }
    }

    @Test
    void testWriteFileEmptyCollection() {
        String destination = "./data/testEmptyFlashcardCollectionWriter.json";
        Writer writer = new Writer(destination);
        Reader reader = new Reader(destination);
        FlashcardCollection collection = new FlashcardCollection("Math");
        try {
            writer.open();
            writer.write(collection);
            writer.close();

            reader.read();
            assertEquals("Math", collection.getTitle());
            assertEquals(0, collection.getSize());
        } catch (IOException e) {
            fail("Unexpected exception caught");
        }
    }

    @Test
    void testWriteFileNonEmptyCollection() {
        String destination = "./data/testNonEmptyFlashcardCollectionWriter.json";
        Writer writer = new Writer(destination);
        Reader reader = new Reader(destination);
        FlashcardCollection collection = new FlashcardCollection("Physics");
        Flashcard cardA = new Flashcard("Speed", "Change in distance");
        collection.addFlashcard(cardA);
        try {
            writer.open();
            writer.write(collection);
            writer.close();

            FlashcardCollection collectionWritten = reader.read();
            assertEquals("Physics", collectionWritten.getTitle());
            assertEquals(1, collectionWritten.getSize());
            assertEquals("Speed", collectionWritten.getFlashcardAtPosition(1).getKeyword());
            assertEquals("Change in distance", collectionWritten.getFlashcardAtPosition(1).getDescription());
        } catch (IOException e) {
            fail("Unexpected exception caught");
        }
    }
}
