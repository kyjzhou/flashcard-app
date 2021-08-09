package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardCollectionTest {

    private FlashcardCollection testCollection;

    @BeforeEach
    public void runBefore() {
        testCollection = new FlashcardCollection("Physics");
    }

    @Test
    public void testConstructor() {
        assertEquals("Physics", testCollection.getTitle());
        assertEquals(0, testCollection.getFlashcardList().size());
        assertEquals(0, testCollection.getSize());
    }

    @Test
    public void testSetTitle() {
        testCollection.setTitle("Math");
        assertEquals("Math", testCollection.getTitle());
    }

    @Test
    public void testAddOneFlashcardNotAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Normal force", "Perpendicular force from surface");
        checkCanAddFirstFlashcard(flashcardA);
    }

    @Test
    public void testAddOneFlashcardAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Gravity", "Force of attraction between objects");
        checkCanAddFirstFlashcard(flashcardA);
        checkCannotAddFlashcardAgain(flashcardA, 1, 1);
    }

    @Test
    public void testAddManyFlashcardsNotAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Normal force", "Perpendicular force from surface");
        Flashcard flashcardB = new Flashcard("Friction", "Opposing force on an object");
        checkCanAddFirstTwoFlashcards(flashcardA, flashcardB);
    }

    @Test
    public void testAddManyFlashcardsAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Mass", "Quantity of matter");
        Flashcard flashcardB = new Flashcard("Weight", "Force of gravity on an object");
        checkCanAddFirstTwoFlashcards(flashcardB, flashcardA);
        checkCannotAddFlashcardAgain(flashcardA, 2, 2);
        checkCannotAddFlashcardAgain(flashcardB, 2, 1);
    }

    @Test
    public void testDeleteOneFlashcardAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Acceleration", "Change in speed over time");
        checkCanAddFirstFlashcard(flashcardA);
        checkCanDeleteFlashcard(flashcardA, 0);
    }

    @Test
    public void testDeleteOneFlashcardNotInCollection() {
        Flashcard flashcardA = new Flashcard("Acceleration", "Change in speed over time");
        checkCanAddFirstFlashcard(flashcardA);
        checkCanDeleteFlashcard(flashcardA, 0);
        checkCannotDeleteFlashcardAgain(flashcardA, 0);
    }

    @Test
    public void testDeleteManyFlashcardsAlreadyInCollection() {
        Flashcard flashcardA = new Flashcard("Acceleration", "Change in speed over time");
        Flashcard flashcardB = new Flashcard("Velocity", "Change in displacement over time");
        checkCanAddFirstTwoFlashcards(flashcardA, flashcardB);
        checkCanDeleteFlashcard(flashcardA, 1);
        assertEquals(flashcardB, testCollection.getFlashcardAtPosition(1));
        checkCanDeleteFlashcard(flashcardB, 0);
    }

    @Test
    public void testDeleteManyFlashcardsNotInCollection() {
        Flashcard flashcardA = new Flashcard("Acceleration", "Change in speed over time");
        Flashcard flashcardB = new Flashcard("Velocity", "Change in displacement over time");
        checkCanAddFirstTwoFlashcards(flashcardA, flashcardB);
        checkCanDeleteFlashcard(flashcardA, 1);
        assertEquals(flashcardB, testCollection.getFlashcardAtPosition(1));
        checkCannotDeleteFlashcardAgain(flashcardA, 1);
        assertEquals(flashcardB, testCollection.getFlashcardAtPosition(1));
        checkCanDeleteFlashcard(flashcardB, 0);
        checkCannotDeleteFlashcardAgain(flashcardB, 0);
    }

    private void checkCanAddFirstFlashcard(Flashcard flashcard) {
        assertTrue(testCollection.addFlashcard(flashcard));
        assertEquals(1, testCollection.getSize());
        assertEquals(flashcard, testCollection.getFlashcardAtPosition(1));
    }

    private void checkCanAddFirstTwoFlashcards(Flashcard first, Flashcard second) {
        assertTrue(testCollection.addFlashcard(first));
        assertTrue(testCollection.addFlashcard(second));
        assertEquals(2, testCollection.getSize());
        assertEquals(first, testCollection.getFlashcardAtPosition(1));
        assertEquals(second, testCollection.getFlashcardAtPosition(2));
    }

    private void checkCannotAddFlashcardAgain(Flashcard flashcard, int listSize, int cardPosition) {
        assertFalse(testCollection.addFlashcard(flashcard));
        assertEquals(listSize, testCollection.getSize());
        assertEquals(flashcard, testCollection.getFlashcardAtPosition(cardPosition));
    }

    private void checkCanDeleteFlashcard(Flashcard flashcard, int listSize) {
        assertTrue(testCollection.deleteFlashcard(flashcard));
        assertEquals(listSize, testCollection.getSize());
    }

    private void checkCannotDeleteFlashcardAgain(Flashcard flashcard, int listSize) {
        assertFalse(testCollection.deleteFlashcard(flashcard));
        assertEquals(listSize, testCollection.getSize());
    }
}