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
        testCollection = new FlashcardCollection("Physics");
        testFlashcards = testCollection.getFlashcards();
    }

    @Test
    public void testConstructor() {
        assertEquals("Physics", testCollection.getTitle());
        assertEquals(0, testFlashcards.size());
    }

    @Test
    public void testEditTitle() {
        testCollection.editTitle("Math");
        assertEquals("Math", testCollection.getTitle());
    }

    @Test
    public void testAddOneFlashcardNotAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Normal force");
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertEquals(1, testFlashcards.size());
        assertEquals(flashcardA, testFlashcards.get(0));
    }

    @Test
    public void testAddOneFlashcardAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Gravity");
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertEquals(1, testFlashcards.size());
        assertEquals(flashcardA, testFlashcards.get(0));

        assertFalse(testCollection.addFlashcard(flashcardA));
        assertEquals(1, testFlashcards.size());
        assertEquals(flashcardA, testFlashcards.get(0));
    }

    @Test
    public void testAddManyFlashcardsNotAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Normal force");
        Flashcard flashcardB = new Flashcard("Friction");
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertTrue(testCollection.addFlashcard(flashcardB));
        assertEquals(2, testFlashcards.size());
        assertEquals(flashcardA, testFlashcards.get(0));
        assertEquals(flashcardB, testFlashcards.get(1));
    }

    @Test
    public void testAddManyFlashcardsAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Mass");
        Flashcard flashcardB = new Flashcard("Weight");
        assertTrue(testCollection.addFlashcard(flashcardB));
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertEquals(2, testFlashcards.size());
        assertEquals(flashcardB, testFlashcards.get(0));
        assertEquals(flashcardA, testFlashcards.get(1));

        assertFalse(testCollection.addFlashcard(flashcardA));
        assertEquals(2, testFlashcards.size());
        assertEquals(flashcardA, testFlashcards.get(1));

        assertFalse(testCollection.addFlashcard(flashcardB));
        assertEquals(2, testFlashcards.size());
        assertEquals(flashcardB, testFlashcards.get(0));
    }

    @Test
    public void testDeleteOneFlashcardAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Acceleration");
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertEquals(1, testFlashcards.size());
        assertEquals(flashcardA, testFlashcards.get(0));

        assertTrue(testCollection.deleteFlashcard(flashcardA));
        assertEquals(0, testFlashcards.size());
        assertFalse(testFlashcards.contains(flashcardA));
    }

    @Test
    public void testDeleteOneFlashcardNotInCollection() {
        Flashcard flashcardA = new Flashcard("Acceleration");
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertEquals(1, testFlashcards.size());
        assertEquals(flashcardA, testFlashcards.get(0));

        assertTrue(testCollection.deleteFlashcard(flashcardA));
        assertEquals(0, testFlashcards.size());
        assertFalse(testFlashcards.contains(flashcardA));

        assertFalse(testCollection.deleteFlashcard(flashcardA));
        assertEquals(0, testFlashcards.size());
        assertFalse(testFlashcards.contains(flashcardA));
    }

    @Test
    public void testDeleteManyFlashcardsAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Acceleration");
        Flashcard flashcardB = new Flashcard("Velocity");
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertTrue(testCollection.addFlashcard(flashcardB));
        assertEquals(2, testFlashcards.size());
        assertEquals(flashcardA, testFlashcards.get(0));
        assertEquals(flashcardB, testFlashcards.get(1));

        assertTrue(testCollection.deleteFlashcard(flashcardA));
        assertEquals(1, testFlashcards.size());
        assertFalse(testFlashcards.contains(flashcardA));
        assertEquals(flashcardB, testFlashcards.get(0));

        assertTrue(testCollection.deleteFlashcard(flashcardB));
        assertEquals(0, testFlashcards.size());
        assertFalse(testFlashcards.contains(flashcardB));
    }

    @Test
    public void testDeleteManyFlashcardsNotInCollection() {
        Flashcard flashcardA = new Flashcard("Acceleration");
        Flashcard flashcardB = new Flashcard("Velocity");
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertTrue(testCollection.addFlashcard(flashcardB));
        assertEquals(2, testFlashcards.size());
        assertEquals(flashcardA, testFlashcards.get(0));
        assertEquals(flashcardB, testFlashcards.get(1));

        assertTrue(testCollection.deleteFlashcard(flashcardA));
        assertEquals(1, testFlashcards.size());
        assertFalse(testFlashcards.contains(flashcardA));
        assertEquals(flashcardB, testFlashcards.get(0));

        assertFalse(testCollection.deleteFlashcard(flashcardA));
        assertEquals(1, testFlashcards.size());
        assertFalse(testFlashcards.contains(flashcardA));
        assertEquals(flashcardB, testFlashcards.get(0));

        assertTrue(testCollection.deleteFlashcard(flashcardB));
        assertEquals(0, testFlashcards.size());
        assertFalse(testFlashcards.contains(flashcardB));

        assertFalse(testCollection.deleteFlashcard(flashcardB));
        assertEquals(0, testFlashcards.size());
        assertFalse(testFlashcards.contains(flashcardA));
    }

    @Test
    public void testGetFlashcardAtPosition() {
        Flashcard flashcardA = new Flashcard("Displacement");
        Flashcard flashcardB = new Flashcard("Distance");
        Flashcard flashcardC = new Flashcard("Speed");
        assertTrue(testCollection.addFlashcard(flashcardA));
        assertTrue(testCollection.addFlashcard(flashcardB));
        assertTrue(testCollection.addFlashcard(flashcardC));
        assertEquals(3, testFlashcards.size());

        assertEquals(flashcardA, testCollection.getFlashcardAtPosition(1));
        assertEquals(flashcardB, testCollection.getFlashcardAtPosition(2));
        assertEquals(flashcardC, testCollection.getFlashcardAtPosition(3));
    }

}