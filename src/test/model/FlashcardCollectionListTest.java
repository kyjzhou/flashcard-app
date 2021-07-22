package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlashcardCollectionListTest {

    private FlashcardCollectionList testCollectionList;
    private List<FlashcardCollection> testCollections;

    @BeforeEach
    public void runBefore() {
        testCollectionList = new FlashcardCollectionList();
        testCollections = testCollectionList.getCollections();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testCollections.size());
    }

    @Test
    public void testAddOneCollectionNotAlreadyInList() {
        FlashcardCollection collectionA = new FlashcardCollection("Physics");

        assertTrue(testCollectionList.addCollection(collectionA));
        assertEquals(1, testCollections.size());
        assertTrue(testCollections.contains(collectionA));
    }

    @Test
    public void testAddOneCollectionAlreadyInList() {
        FlashcardCollection collectionA = new FlashcardCollection("Physics");

        assertTrue(testCollectionList.addCollection(collectionA));
        assertEquals(1, testCollections.size());
        assertEquals(collectionA, testCollections.get(0));

        assertFalse(testCollectionList.addCollection(collectionA));
        assertEquals(1, testCollections.size());
        assertEquals(collectionA, testCollections.get(0));
    }

    @Test
    public void testAddManyCollectionsNotAlreadyInList() {
        FlashcardCollection collectionA = new FlashcardCollection("French");
        FlashcardCollection collectionB = new FlashcardCollection("Spanish");

        assertTrue(testCollectionList.addCollection(collectionA));
        assertTrue(testCollectionList.addCollection(collectionB));
        assertEquals(2, testCollections.size());
        assertEquals(collectionA, testCollections.get(0));
        assertEquals(collectionB, testCollections.get(1));
    }

    @Test
    public void testAddManyCollectionsAlreadyInList() {
        FlashcardCollection collectionA = new FlashcardCollection("French");
        FlashcardCollection collectionB = new FlashcardCollection("Spanish");

        assertTrue(testCollectionList.addCollection(collectionA));
        assertTrue(testCollectionList.addCollection(collectionB));
        assertEquals(2, testCollections.size());
        assertEquals(collectionA, testCollections.get(0));
        assertEquals(collectionB, testCollections.get(1));

        assertFalse(testCollectionList.addCollection(collectionA));
        assertFalse(testCollectionList.addCollection(collectionB));
        assertEquals(2, testCollections.size());
        assertEquals(collectionA, testCollections.get(0));
        assertEquals(collectionB, testCollections.get(1));
    }

    @Test
    public void testDeleteOneCollectionAlreadyInList() {
        FlashcardCollection collectionA = new FlashcardCollection("Biology");

        assertTrue(testCollectionList.addCollection(collectionA));
        assertEquals(1, testCollections.size());
        assertTrue(testCollections.contains(collectionA));

        assertTrue(testCollectionList.deleteCollection(collectionA));
        assertEquals(0, testCollections.size());
        assertFalse(testCollections.contains(collectionA));
    }

    @Test
    public void testDeleteOneCollectionNotInList() {
        FlashcardCollection collectionA = new FlashcardCollection("Biology");

        assertTrue(testCollectionList.addCollection(collectionA));
        assertEquals(1, testCollections.size());
        assertTrue(testCollections.contains(collectionA));

        assertTrue(testCollectionList.deleteCollection(collectionA));
        assertEquals(0, testCollections.size());
        assertFalse(testCollections.contains(collectionA));

        assertFalse(testCollectionList.deleteCollection(collectionA));
        assertEquals(0, testCollections.size());
        assertFalse(testCollections.contains(collectionA));
    }

    @Test
    public void testDeleteManyCollectionsAlreadyInList() {
        FlashcardCollection collectionA = new FlashcardCollection("Math");
        FlashcardCollection collectionB = new FlashcardCollection("Chemistry");

        assertTrue(testCollectionList.addCollection(collectionA));
        assertTrue(testCollectionList.addCollection(collectionB));
        assertEquals(2, testCollections.size());
        assertEquals(collectionA, testCollections.get(0));
        assertEquals(collectionB, testCollections.get(1));

        assertTrue(testCollectionList.deleteCollection(collectionA));
        assertEquals(1, testCollections.size());
        assertFalse(testCollections.contains(collectionA));
        assertTrue(testCollections.contains(collectionB));

        assertTrue(testCollectionList.deleteCollection(collectionB));
        assertEquals(0, testCollections.size());
        assertFalse(testCollections.contains(collectionA));
        assertFalse(testCollections.contains(collectionB));
    }

    @Test
    public void testDeleteManyCollectionsNotInList() {
        FlashcardCollection collectionA = new FlashcardCollection("Math");
        FlashcardCollection collectionB = new FlashcardCollection("Chemistry");

        assertTrue(testCollectionList.addCollection(collectionA));
        assertTrue(testCollectionList.addCollection(collectionB));
        assertEquals(2, testCollections.size());
        assertEquals(collectionA, testCollections.get(0));
        assertEquals(collectionB, testCollections.get(1));

        assertTrue(testCollectionList.deleteCollection(collectionA));
        assertEquals(1, testCollections.size());
        assertFalse(testCollections.contains(collectionA));
        assertEquals(collectionB, testCollections.get(0));

        assertFalse(testCollectionList.deleteCollection(collectionA));
        assertEquals(1, testCollections.size());
        assertFalse(testCollections.contains(collectionA));
        assertEquals(collectionB, testCollections.get(0));

        assertTrue(testCollectionList.deleteCollection(collectionB));
        assertEquals(0, testCollections.size());
        assertFalse(testCollections.contains(collectionA));
        assertFalse(testCollections.contains(collectionB));

        assertFalse(testCollectionList.deleteCollection(collectionB));
        assertEquals(0, testCollections.size());
        assertFalse(testCollections.contains(collectionA));
        assertFalse(testCollections.contains(collectionB));
    }

    @Test
    public void testGetNoCollectionTitles() {
        List<String> collectionTitles = testCollectionList.getCollectionTitles();
        assertEquals(0, collectionTitles.size());
    }

    @Test
    public void testGetOneCollectionTitle() {
        FlashcardCollection collectionA = new FlashcardCollection("Psychology");
        assertTrue(testCollectionList.addCollection(collectionA));
        assertEquals(1, testCollections.size());
        assertEquals(collectionA, testCollections.get(0));

        List<String> collectionTitles = testCollectionList.getCollectionTitles();
        assertEquals(1, collectionTitles.size());
        assertEquals("Psychology", collectionTitles.get(0));
    }

    @Test
    public void testGetManyCollectionTitles() {
        FlashcardCollection collectionA = new FlashcardCollection("Chinese");
        FlashcardCollection collectionB = new FlashcardCollection("French");
        FlashcardCollection collectionC = new FlashcardCollection("Spanish");

        assertTrue(testCollectionList.addCollection(collectionA));
        assertTrue(testCollectionList.addCollection(collectionB));
        assertTrue(testCollectionList.addCollection(collectionC));
        assertEquals(3, testCollections.size());
        assertEquals(collectionA, testCollections.get(0));
        assertEquals(collectionB, testCollections.get(1));
        assertEquals(collectionC, testCollections.get(2));

        List<String> collectionTitles = testCollectionList.getCollectionTitles();
        assertEquals(3, collectionTitles.size());
        assertEquals("Chinese", collectionTitles.get(0));
        assertEquals("French", collectionTitles.get(1));
        assertEquals("Spanish", collectionTitles.get(2));
    }

}
