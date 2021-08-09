package ui;

import model.Flashcard;
import model.FlashcardCollection;
import persistence.Reader;
import persistence.Writer;
import ui.dialogs.AddDialog;
import ui.dialogs.EditTitleDialog;
import ui.dialogs.LoadDialog;
import ui.tools.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// A graphical user interface for a flashcard app
public class FlashcardGUI extends JFrame implements ActionListener {

    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 100;

    private FlashcardCollection collection;
    private JLabel topLabel;
    private List<FlashcardButton> buttons;
    private FlashcardButton selectedButton;
    private Tool selectedTool;


    // EFFECTS: Creates the graphical user interface for the flashcard app
    public FlashcardGUI() {
        super("Flashcard Collection: Untitled");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        collection = new FlashcardCollection("Untitled");
        buttons = new ArrayList<>();
        selectedButton = null;
        selectedTool = null;

        setContentPane(generateContentPane());
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setJMenuBar(generateMenuBar());
        topLabel = new JLabel("No flashcard selected");
        add(topLabel, TOP_ALIGNMENT);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel generateContentPane() {
        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        return contentPane;
    }

    private JMenuBar generateMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        new LoadTool(fileMenu, this);
        new SaveTool(fileMenu, this);
        menuBar.add(fileMenu);

        JMenu editMenu = new JMenu("Edit");
        new AddTool(editMenu, this);
        new DeleteTool(editMenu, this);
        editMenu.addSeparator();

        JMenu subEditMenu = new JMenu("Edit...");
        new EditTitleTool(subEditMenu, this);
        new EditDescriptionTool(subEditMenu, this);
        new EditKeywordTool(subEditMenu, this);
        editMenu.add(subEditMenu);
        menuBar.add(editMenu);
        return menuBar;
    }

    private void addButtons(FlashcardCollection collection) {
        for (Flashcard flashcard : collection.getFlashcardList()) {
            FlashcardButton button = new FlashcardButton(flashcard, this);
            add(button, CENTER_ALIGNMENT);
        }
        validate();
    }

    public void setSelectedButton(FlashcardButton button) {
        if (selectedButton != null) {
            selectedButton.unselect();
        }
        selectedButton = button;
        selectedButton.select();
    }

    public void setSelectedTool(Tool tool) {
        if (selectedTool != null) {
            selectedTool.unselect();
        }
        selectedTool = tool;
        selectedTool.select();
    }

    public void addFlashcardButton(String keyword, String description) {
        Flashcard newFlashcard = new Flashcard(keyword, description);
        FlashcardButton newButton = new FlashcardButton(newFlashcard, this);
        buttons.add(newButton);
        collection.addFlashcard(newFlashcard);
        add(newButton, CENTER_ALIGNMENT);
        validate();
    }

    public void processAddFlashcardButton() {
        AddDialog addDialog = new AddDialog(this);
        addDialog.setSize(300, 200);
        addDialog.setLocationRelativeTo(this);
        addDialog.setVisible(true);
    }

    public void processSaveCollection() {
        try {
            String jsonFile = "./data/" + collection.getTitle().toLowerCase() + ".json";
            Writer writer = new Writer(jsonFile);
            writer.open();
            writer.write(collection);
            writer.close();
            topLabel.setText("Flashcards saved successfully");
            validate();
        } catch (FileNotFoundException e) {
            topLabel.setText("Unable to save flashcards - this collection is NOT saved");
        }
    }

    public void loadCollection(String title) {
        String jsonFile = "./data/" + title.toLowerCase() + ".json";
        try {
            Reader reader = new Reader(jsonFile);
            collection = reader.read();
            addButtons(collection);
            setTitle("Flashcard collection: " + collection.getTitle());
            topLabel.setText("Flashcards loaded successfully - click on a flashcard to view its keyword");
        } catch (IOException e) {
            topLabel.setText("Unable to load flashcards - check if the collection title is correct");
        }
    }

    public void processLoadCollection() {
        LoadDialog loadDialog = new LoadDialog(this);
        loadDialog.setSize(300, 150);
        loadDialog.setLocationRelativeTo(this);
        loadDialog.setVisible(true);
    }

    public void editCollectionTitle(String newTitle) {
        collection.setTitle(newTitle);
        setTitle("Flashcard collection: " + collection.getTitle());
        topLabel.setText("This collection's title is now: " + collection.getTitle());
    }

    public void processEditCollectionTitle() {
        EditTitleDialog titleDialog = new EditTitleDialog(this);
        titleDialog.setSize(300, 150);
        titleDialog.setLocationRelativeTo(this);
        titleDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(selectedButton)) {
            String keyword = selectedButton.getFlashcard().getKeyword();
            topLabel.setText("Flashcard keyword: " + keyword);
        } else if (e.getActionCommand().equals("add")) {
            processAddFlashcardButton();
        } else if (e.getActionCommand().equals("save")) {
            processSaveCollection();
        } else if (e.getActionCommand().equals("load")) {
            processLoadCollection();
        } else if (e.getActionCommand().equals("title")) {
            processEditCollectionTitle();
        }
    }

}
