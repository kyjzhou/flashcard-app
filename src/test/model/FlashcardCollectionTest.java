package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardCollectionTest {

    private FlashcardCollection testCollection;
    private List<Flashcard> testFlashcards;

    @BeforeEach
    public void runBefore() {
        testCollection = new FlashcardCollection();
        testFlashcards = testCollection.getFlashcards();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testCollection.getSize());
    }

    @Test
    public void testAddOneFlashcardNotAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Normal force", "Perpendicular force from surface");
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertEquals(1, testCollection.getSize());
        assertEquals(flashcardA, testCollection.getFlashcardAtPosition(1));
    }

    @Test
    public void testAddOneFlashcardAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Gravity", "Force of attraction between objects");
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertEquals(1, testCollection.getSize());
        assertEquals(flashcardA, testCollection.getFlashcardAtPosition(1));

        assertFalse(testCollection.addFlashcard(flashcardA));
        assertEquals(1, testCollection.getSize());
        assertEquals(flashcardA, testCollection.getFlashcardAtPosition(1));
    }

    @Test
    public void testAddManyFlashcardsNotAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Normal force", "Perpendicular force from surface");
        Flashcard flashcardB = new Flashcard("Friction", "Opposing force on an object");
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertTrue(testCollection.addFlashcard(flashcardB));
        assertEquals(2, testCollection.getSize());
        assertEquals(flashcardA, testCollection.getFlashcardAtPosition(1));
        assertEquals(flashcardB, testCollection.getFlashcardAtPosition(2));
    }

    @Test
    public void testAddManyFlashcardsAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Mass", "Quantity of matter");
        Flashcard flashcardB = new Flashcard("Weight", "Force of gravity on an object");
        assertTrue(testCollection.addFlashcard(flashcardB));
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertEquals(2, testCollection.getSize());
        assertEquals(flashcardB, testCollection.getFlashcardAtPosition(1));
        assertEquals(flashcardA, testCollection.getFlashcardAtPosition(2));

        assertFalse(testCollection.addFlashcard(flashcardA));
        assertEquals(2, testCollection.getSize());
        assertEquals(flashcardA, testCollection.getFlashcardAtPosition(2));

        Flashcard flashcardC = new Flashcard("Gravity", "Force of attraction between objects");
        assertTrue(testCollection.addFlashcard(flashcardC));
        assertEquals(3, testCollection.getSize());
        assertEquals(flashcardC, testCollection.getFlashcardAtPosition(3));
        assertEquals(flashcardA, testCollection.getFlashcardAtPosition(2));
        assertEquals(flashcardB, testCollection.getFlashcardAtPosition(1));

        assertFalse(testCollection.addFlashcard(flashcardB));
        assertEquals(3, testCollection.getSize());
        assertEquals(flashcardB, testCollection.getFlashcardAtPosition(1));
    }

    @Test
    public void testDeleteOneFlashcardAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Acceleration", "Change in speed over time");
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertEquals(1, testCollection.getSize());
        assertEquals(flashcardA, testCollection.getFlashcardAtPosition(1));

        assertTrue(testCollection.deleteFlashcard(flashcardA));
        assertEquals(0, testCollection.getSize());
    }

    @Test
    public void testDeleteOneFlashcardNotInCollection() {
        Flashcard flashcardA = new Flashcard("Acceleration", "Change in speed over time");
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertEquals(1, testCollection.getSize());
        assertEquals(flashcardA, testCollection.getFlashcardAtPosition(1));

        assertTrue(testCollection.deleteFlashcard(flashcardA));
        assertEquals(0, testCollection.getSize());

        assertFalse(testCollection.deleteFlashcard(flashcardA));
        assertEquals(0, testCollection.getSize());
    }

    @Test
    public void testDeleteManyFlashcardsAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Acceleration", "Change in speed over time");
        Flashcard flashcardB = new Flashcard("Velocity", "Change in displacement over time");
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertTrue(testCollection.addFlashcard(flashcardB));
        assertEquals(2, testCollection.getSize());
        assertEquals(flashcardA, testCollection.getFlashcardAtPosition(1));
        assertEquals(flashcardB, testCollection.getFlashcardAtPosition(2));

        assertTrue(testCollection.deleteFlashcard(flashcardA));
        assertEquals(1, testCollection.getSize());
        assertEquals(flashcardB, testCollection.getFlashcardAtPosition(1));

        assertTrue(testCollection.deleteFlashcard(flashcardB));
        assertEquals(0, testCollection.getSize());
    }

    @Test
    public void testDeleteManyFlashcardsNotInCollection() {
        Flashcard flashcardA = new Flashcard("Acceleration", "Change in speed over time");
        Flashcard flashcardB = new Flashcard("Velocity", "Change in displacement over time");
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertTrue(testCollection.addFlashcard(flashcardB));
        assertEquals(2, testCollection.getSize());
        assertEquals(flashcardA, testCollection.getFlashcardAtPosition(1));
        assertEquals(flashcardB, testCollection.getFlashcardAtPosition(2));

        assertTrue(testCollection.deleteFlashcard(flashcardA));
        assertEquals(1, testCollection.getSize());
        assertEquals(flashcardB, testCollection.getFlashcardAtPosition(1));

        assertFalse(testCollection.deleteFlashcard(flashcardA));
        assertEquals(1, testCollection.getSize());
        assertEquals(flashcardB, testCollection.getFlashcardAtPosition(1));

        assertTrue(testCollection.deleteFlashcard(flashcardB));
        assertEquals(0, testCollection.getSize());

        assertFalse(testCollection.deleteFlashcard(flashcardB));
        assertEquals(0, testCollection.getSize());
    }
}