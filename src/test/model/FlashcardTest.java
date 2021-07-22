package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlashcardTest {

    private Flashcard testFlashcard;

    @BeforeEach
    public void runBefore() {
        testFlashcard = new Flashcard("Velocity");
    }

    @Test
    public void testConstructor() {
        assertEquals("Velocity", testFlashcard.getKeyword());
        assertEquals("No description yet", testFlashcard.getDescription());
    }

    @Test
    public void testEditKeywordOnly() {
        testFlashcard.editKeyword("Acceleration");
        assertEquals("Acceleration", testFlashcard.getKeyword());
        assertEquals("No description yet", testFlashcard.getDescription());
    }

    @Test
    public void testEditDescriptionOnly() {
        testFlashcard.editDescription("Change in displacement over time");
        assertEquals("Velocity", testFlashcard.getKeyword());
        assertEquals("Change in displacement over time", testFlashcard.getDescription());
    }

    @Test
    public void testEditKeywordAndDescription() {
        testFlashcard.editKeyword("Displacement");
        testFlashcard.editDescription("Change in position over time");
        assertEquals("Displacement", testFlashcard.getKeyword());
        assertEquals("Change in position over time", testFlashcard.getDescription());
    }
}
