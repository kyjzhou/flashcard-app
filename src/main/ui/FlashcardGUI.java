package ui;

import model.Flashcard;
import model.FlashcardCollection;
import persistence.Reader;
import persistence.Writer;
import ui.dialogs.AddDialog;
import ui.dialogs.DeleteDialog;
import ui.dialogs.EditTitleDialog;
import ui.dialogs.LoadDialog;
import ui.sound.MidiSynth;
import ui.tools.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// A graphical user interface for an app that generates flashcards
// Modelled after FrameDemoProject and MenuDemoProject from Java Tutorials for using Swing Components
//         (https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html)
public class FlashcardGUI extends JFrame implements ActionListener {

    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 100;

    private FlashcardCollection collection;
    private JLabel topLabel;
    private List<FlashcardButton> buttons;
    private FlashcardButton selectedButton;
    private MidiSynth midiSynth;


    // EFFECTS: Creates and sets up the graphical user interface for the flashcard app
    public FlashcardGUI() {
        super("Flashcard Collection: Untitled");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        collection = new FlashcardCollection("Untitled");
        buttons = new ArrayList<>();
        selectedButton = null;

        setContentPane(generateContentPane());
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setJMenuBar(generateMenuBar());
        topLabel = new JLabel("No flashcard selected");
        add(topLabel, TOP_ALIGNMENT);
        pack();
        setUpSound();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: sets up and returns a content pane
    private JPanel generateContentPane() {
        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        return contentPane;
    }

    // EFFECTS: sets up and returns a menu bar
    private JMenuBar generateMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        new LoadTool(fileMenu, this);
        new SaveTool(fileMenu, this);
        menuBar.add(fileMenu);

        JMenu editMenu = new JMenu("Edit");
        new AddTool(editMenu, this);
        new DeleteTool(editMenu, this);
        new EditTitleTool(editMenu, this);
        menuBar.add(editMenu);
        return menuBar;
    }

    // MODIFIES: this
    // EFFECTS: sets up and opens this GUI's MidiSynth
    private void setUpSound() {
        midiSynth = new MidiSynth();
        midiSynth.open();
    }

    // MODIFIES: this
    // EFFECTS: sets the given button as this GUI's selected button
    public void setSelectedButton(FlashcardButton button) {
        if (selectedButton != null) {
            selectedButton.unselect();
        }
        selectedButton = button;
        selectedButton.select();
    }

    // MODIFIES: this
    // EFFECTS: adds a new flashcard button with given keyword and description to this GUI, and plays a sound when done
    public void addFlashcardButton(String keyword, String description) {
        Flashcard newFlashcard = new Flashcard(keyword, description);
        FlashcardButton newButton = new FlashcardButton(newFlashcard, this);
        buttons.add(newButton);
        collection.addFlashcard(newFlashcard);
        add(newButton, CENTER_ALIGNMENT);
        validate();
        midiSynth.play(10, 60, 80);
    }

    // MODIFIES: this
    // EFFECTS: adds and displays a dialog window for adding a new flashcard button to this GUI
    public void processAddFlashcardButton() {
        AddDialog addDialog = new AddDialog(this);
        addDialog.setSize(300, 200);
        addDialog.setLocationRelativeTo(this);
        addDialog.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: deletes this GUI's selected button and plays a sound when done
    public void deleteFlashcardButton() {
        Flashcard selectedFlashcard = selectedButton.getFlashcard();
        buttons.remove(selectedButton);
        collection.deleteFlashcard(selectedFlashcard);
        remove(selectedButton);
        revalidate();
        repaint();
        topLabel.setText("Flashcard deleted successfully");
        midiSynth.play(12, 60, 60);
    }

    // MODIFIES: this
    // EFFECTS: adds and displays a dialog window for deleting this GUI's selected button,
    //          if no button is selected, this GUI's top label indicates that no button has been deleted
    public void processDeleteFlashcardButton() {
        try {
            DeleteDialog deleteDialog = new DeleteDialog(this);
            deleteDialog.setSize(300, 150);
            deleteDialog.setLocationRelativeTo(this);
            deleteDialog.setVisible(true);
        } catch (NullPointerException e) {
            topLabel.setText("No flashcard deleted - select one to delete it");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds and displays a dialog window for saving this GUI's collection to file,
    //          and plays a sound if the collection is saved successfully
    //          if the collection cannot be saved, this GUI's top label indicates that it is not saved
    public void processSaveCollection() {
        try {
            String jsonFile = "./data/" + collection.getTitle().toLowerCase() + ".json";
            Writer writer = new Writer(jsonFile);
            writer.open();
            writer.write(collection);
            writer.close();
            topLabel.setText("Flashcards saved successfully");
            validate();
            midiSynth.play(28, 60, 80);
        } catch (FileNotFoundException | NullPointerException e) {
            topLabel.setText("Unable to save flashcards - this collection is NOT saved");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds and displays a dialog window for loading a collection from file,
    //          and plays a sound if the collection is loaded successfully
    //          if the collection cannot be loaded, this GUI's top label indicates that it is not loaded
    public void loadCollection(String title) {
        String jsonFile = "./data/" + title.toLowerCase() + ".json";
        try {
            Reader reader = new Reader(jsonFile);
            collection = reader.read();
            addButtons(collection);
            setTitle("Flashcard collection: " + collection.getTitle());
            topLabel.setText("Flashcards loaded successfully - click on a flashcard to view its keyword");
            validate();
            midiSynth.play(88, 60, 60);
            midiSynth.stop(88, 60);
        } catch (IOException e) {
            topLabel.setText("Unable to load flashcards - check if the collection title is correct");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds and displays a dialog window for loading a flashcard collection to this GUI
    public void processLoadCollection() {
        LoadDialog loadDialog = new LoadDialog(this);
        loadDialog.setSize(300, 150);
        loadDialog.setLocationRelativeTo(this);
        loadDialog.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: generates flashcard buttons from given collection's flashcards, and adds those buttons to this GUI
    private void addButtons(FlashcardCollection collection) {
        for (Flashcard flashcard : collection.getFlashcardList()) {
            FlashcardButton button = new FlashcardButton(flashcard, this);
            buttons.add(button);
            add(button, CENTER_ALIGNMENT);
        }
        validate();
    }

    // MODIFIES: this
    // EFFECTS: sets the given new title as this GUI's collection title
    public void editCollectionTitle(String newTitle) {
        collection.setTitle(newTitle);
        setTitle("Flashcard collection: " + collection.getTitle());
        topLabel.setText("This collection's title is now: " + collection.getTitle());
        midiSynth.play(8, 60, 80);
    }

    // MODIFIES: this
    // EFFECTS: adds and displays a dialog window for editing this GUI's collection title
    public void processEditCollectionTitle() {
        EditTitleDialog titleDialog = new EditTitleDialog(this);
        titleDialog.setSize(300, 150);
        titleDialog.setLocationRelativeTo(this);
        titleDialog.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: handles events when buttons or menu items are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(selectedButton)) {
            String keyword = selectedButton.getFlashcard().getKeyword();
            topLabel.setText("Flashcard keyword: " + keyword);
        } else if (e.getActionCommand().equals("add")) {
            processAddFlashcardButton();
        } else if (e.getActionCommand().equals("delete")) {
            processDeleteFlashcardButton();
        } else if (e.getActionCommand().equals("save")) {
            processSaveCollection();
        } else if (e.getActionCommand().equals("load")) {
            processLoadCollection();
        } else if (e.getActionCommand().equals("title")) {
            processEditCollectionTitle();
        }
    }

    // getter
    public FlashcardButton getSelectedButton() {
        return selectedButton;
    }

}
