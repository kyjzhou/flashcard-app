package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlashcardTest {

    private Flashcard testFlashcard;

    @BeforeEach
    public void runBefore() {
        testFlashcard = new Flashcard("Velocity", "Change in displacement over time");
    }

    @Test
    public void testConstructor() {
        assertEquals("Velocity", testFlashcard.getKeyword());
        assertEquals("Change in displacement over time", testFlashcard.getDescription());
    }

    @Test
    public void testEditKeywordOnly() {
        testFlashcard.editKeyword("Acceleration");
        assertEquals("Acceleration", testFlashcard.getKeyword());
        assertEquals("Change in displacement over time", testFlashcard.getDescription());
    }

    @Test
    public void testEditDescriptionOnly() {
        testFlashcard.editDescription("Slope of displacement-time graph");
        assertEquals("Velocity", testFlashcard.getKeyword());
        assertEquals("Slope of displacement-time graph", testFlashcard.getDescription());
    }

    @Test
    public void testEditKeywordAndDescription() {
        testFlashcard.editKeyword("Displacement");
        testFlashcard.editDescription("Change in position");
        assertEquals("Displacement", testFlashcard.getKeyword());
        assertEquals("Change in position", testFlashcard.getDescription());
    }
}
