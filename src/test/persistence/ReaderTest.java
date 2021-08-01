package persistence;

import model.Flashcard;
import model.FlashcardCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Modelled based on JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Tests for Reader
public class ReaderTest {

    @Test
    void testReadFileNotThere() {
        String source = "./data/notThere.json";
        Reader reader = new Reader(source);
        try {
            reader.read();
            fail("Exception not thrown");
        } catch (IOException e) {
            // Exception expected to be caught
        }
    }

    @Test
    void testReadFileEmptyCollection() {
        String source = "./data/testEmptyFlashcardCollectionReader.json";
        Writer writer = new Writer(source);
        Reader reader = new Reader(source);
        FlashcardCollection collection = new FlashcardCollection("Biology");
        try {
            writer.open();
            writer.write(collection);
            writer.close();

            FlashcardCollection collectionRead = reader.read();
            assertEquals("Biology", collectionRead.getName());
            assertEquals(0, collectionRead.getSize());
        } catch (IOException e) {
            fail("Unexpected exception thrown");
        }
    }

    @Test
    void testReadFileNonEmptyCollection() {
        String source = "./data/testNonEmptyFlashcardCollectionReader.json";
        Writer writer = new Writer(source);
        Reader reader = new Reader(source);
        FlashcardCollection collection = new FlashcardCollection("Chemistry");
        Flashcard cardA = new Flashcard("Hydrogen", "First element");
        collection.addFlashcard(cardA);
        try {
            writer.open();
            writer.write(collection);
            writer.close();

            FlashcardCollection collectionRead = reader.read();
            assertEquals("Chemistry", collectionRead.getName());
            assertEquals(1, collectionRead.getSize());
            assertEquals("Hydrogen", collectionRead.getFlashcardAtPosition(1).getKeyword());
            assertEquals("First element", collectionRead.getFlashcardAtPosition(1).getDescription());
        } catch (IOException e) {
            fail("Unexpected exception thrown");
        }
    }
}
