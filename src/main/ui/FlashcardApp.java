package ui;

import model.Flashcard;
import model.FlashcardCollection;
import persistence.Reader;
import persistence.Writer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

// An app for generating flashcards
public class FlashcardApp {
    private static final String JSON_FILE = "./data/flashcards.json";
    private static final String QUIT_INSTRUCTION = "To quit, enter q";
    private static final String GO_BACK_INSTRUCTION = "To go back, enter b";
    private static final String NON_VALID_RESPONSE = "Sorry, that is not a valid response";

    private Scanner userInput;
    private FlashcardCollection collection;
    private Reader reader;
    private Writer writer;

    // EFFECTS: constructs and runs the flashcard app
    public FlashcardApp() {
        userInput = new Scanner(System.in);
        collection = new FlashcardCollection("Untitled");
        reader = new Reader(JSON_FILE);
        writer = new Writer(JSON_FILE);
        processMainMenu();
    }

    // EFFECTS: processes user actions in the main menu of the app
    private void processMainMenu() {
        while (true) {
            printMainInstructions();
            String userResponse = userInput.nextLine();

            if (userResponse.equals("a")) {
                processAddFlashcard();
            } else if (userResponse.equals("c")) {
                processChooseFlashcard();
            } else if (userResponse.equals("q")) {
                break;
            } else if (userResponse.equals("e")) {
                processEditCollectionName();
            } else if (userResponse.equals("l")) {
                processLoadCollection();
            } else if (userResponse.equals("s")) {
                processSaveCollection();
            } else {
                System.out.println(NON_VALID_RESPONSE);
            }
        }
        exitApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user actions when adding a flashcard
    private void processAddFlashcard() {
        printAddNewKeywordInstructions();
        String userKeyword = userInput.nextLine();
        if (userKeyword.equals("b")) {
            processMainMenu();
        }
        printAddNewDescriptionInstructions();
        String userDescription = userInput.nextLine();
        if (userDescription.equals("b")) {
            processMainMenu();
        } else {
            Flashcard newFlashcard = new Flashcard(userKeyword, userDescription);
            collection.addFlashcard(newFlashcard);
            System.out.println("Added new flashcard");
        }
    }

    // EFFECTS: processes user actions when selecting a flashcard
    private void processChooseFlashcard() {
        printChooseFlashcardInstructions();
        try {
            int cardPositionResponse = userInput.nextInt();
            Flashcard flashcard = collection.getFlashcardAtPosition(cardPositionResponse);
            processSelectFlashcard(flashcard);
        } catch (InputMismatchException | IndexOutOfBoundsException exception) {
            String userResponseToIgnore = userInput.nextLine();
            System.out.println("You are at the main menu");
        }
    }

    // REQUIRES: selectedFlashcard must be in collection
    // EFFECTS: processes user actions when selecting a flashcard
    private void processSelectFlashcard(Flashcard selectedFlashcard) {
        String previousUserResponseToIgnore = userInput.nextLine();
        while (true) {
            printSelectedFlashcardInstructions();
            String userResponse = userInput.nextLine();
            if (userResponse.equals("vd") || userResponse.equals("vk")) {
                processViewFlashcard(selectedFlashcard, userResponse);
            } else if (userResponse.equals("ed") || userResponse.equals("ek")) {
                processEditFlashcard(selectedFlashcard, userResponse);
            } else if (userResponse.equals("d")) {
                processDeleteFlashcard(selectedFlashcard);
            } else if (userResponse.equals("b")) {
                break;
            } else if (userResponse.equals("q")) {
                exitApp();
            } else {
                System.out.println(NON_VALID_RESPONSE);
            }
        }
    }

    // REQUIRES: flashcard must be in collection; command must equal "vd" or "vk"
    // EFFECTS: processes user actions when viewing a flashcard's keyword or description
    private void processViewFlashcard(Flashcard flashcard, String command) {
        if (command.equals("vd")) {
            System.out.println(flashcard.getDescription());
        } else {
            System.out.println(flashcard.getKeyword());
        }
    }

    // REQUIRES: flashcard must be in collection; command must equal "ed" or "ek"
    // MODIFIES: this
    // EFFECTS: processes user actions when editing a flashcard's keyword or description
    private void processEditFlashcard(Flashcard flashcard, String command) {
        if (command.equals("ed")) {
            printAddNewDescriptionInstructions();
            String userDescription = userInput.nextLine();
            flashcard.editDescription(userDescription);
            System.out.println("Description edited successfully!");
        } else {
            printAddNewKeywordInstructions();
            String userKeyword = userInput.nextLine();
            flashcard.editKeyword(userKeyword);
            System.out.println("Keyword edited successfully!");
        }
    }

    // REQUIRES: flashcard must be in collection
    // MODIFIES: this
    // EFFECTS: processes user actions when deleting a flashcard
    private void processDeleteFlashcard(Flashcard flashcard) {
        while (true) {
            printDeleteFlashcardInstructions();
            String userResponse = userInput.nextLine();
            if (userResponse.equals("d")) {
                collection.deleteFlashcard(flashcard);
                System.out.println("Deleted successfully!");
                processMainMenu();
            } else if (userResponse.equals("b")) {
                break;
            } else {
                System.out.println(NON_VALID_RESPONSE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: edits the flashcard collection's name
    private void processEditCollectionName() {
        System.out.println("Enter a new name for this collection");
        System.out.println(GO_BACK_INSTRUCTION);
        String userResponse = userInput.nextLine();
        if (userResponse.equals("b")) {
            processMainMenu();
        } else {
            collection.setName(userResponse);
        }
    }

    // EFFECTS: save the flashcard collection from file
    private void processSaveCollection() {
        try {
            writer.open();
            writer.write(collection);
            writer.close();
            System.out.println("Flashcards saved successfully");
        } catch (FileNotFoundException e) {
            System.out.println("Error with saving flashcards - no flashcards saved");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the flashcard collection from file
    private void processLoadCollection() {
        try {
            collection = reader.read();
            System.out.println("Flashcards loaded successfully");
        } catch (IOException e) {
            System.out.println("Error with loading flashcards - no flashcards loaded");
        }
    }

    // EFFECTS: prints the instructions for the main menu
    private void printMainInstructions() {
        if (collection.getSize() == 0) {
            System.out.println("This collection (" + collection.getName() + ") is now empty");
            System.out.println("To load your existing flashcard collection from file, enter l");
            System.out.println("To add a new flashcard, enter a");
        } else {
            System.out.println("Flashcard collection: " + collection.getName());
            System.out.println("To save your current flashcard collection to file, enter s");
            System.out.println("To choose an existing flashcard, enter c");
            System.out.println("To add a new flashcard, enter a");
        }
        System.out.println("To edit this flashcard collection's name, enter e");
        System.out.println(QUIT_INSTRUCTION);
    }

    // EFFECTS: prints instructions before the user selects a flashcard
    private void printChooseFlashcardInstructions() {
        System.out.println("Enter the flashcard's position (a number from 1 to " + collection.getSize() + ")");
        System.out.println("Enter any other command to return to the main menu");
    }

    // EFFECTS: prints instructions after the user selects a flashcard
    private void printSelectedFlashcardInstructions() {
        System.out.println("To view this flashcard's description, enter vd");
        System.out.println("To view this flashcard's keyword, enter vk");
        System.out.println("To edit this flashcard's description, enter ed");
        System.out.println("To edit this flashcard's keyword, enter ek");
        System.out.println("To delete this flashcard, enter d");
        System.out.println(GO_BACK_INSTRUCTION);
        System.out.println(QUIT_INSTRUCTION);
    }

    // EFFECTS: prints instructions before adding or editing a flashcard's keyword
    private void printAddNewKeywordInstructions() {
        System.out.println("Enter this flashcard's new keyword");
        System.out.println("To return to the main menu, enter b");
    }

    // EFFECTS: prints instructions before adding or editing a flashcard's description
    private void printAddNewDescriptionInstructions() {
        System.out.println("Enter this flashcard's new description");
        System.out.println("To return to the main menu, enter b");
    }

    // EFFECTS: prints instructions before deleting a flashcard
    private void printDeleteFlashcardInstructions() {
        System.out.println("Do you want to delete this flashcard? Enter d again to confirm");
        System.out.println(GO_BACK_INSTRUCTION);
    }

    // EFFECTS: ends the execution of the app
    private void exitApp() {
        System.out.println("Goodbye. Happy studying!");
        System.exit(0);
    }
}
