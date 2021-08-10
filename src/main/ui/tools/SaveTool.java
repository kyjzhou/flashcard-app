package ui.tools;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// A tool that saves a flashcard collection to file
// Class structure based on subclasses of Tool class in SimpleDrawingPlayer
//      (https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter)
public class SaveTool extends Tool {

    // EFFECTS: constructs a save tool with given action listener in given menu
    public SaveTool(JMenu menu, ActionListener listener) {
        super(menu, "Save this collection", "save", KeyEvent.VK_S);
        addListener(listener);
    }

    // MODIFIES: this
    // EFFECTS: adds given action listener to this tool's menu item
    @Override
    protected void addListener(ActionListener listener) {
        menuItem.addActionListener(listener);
    }
}
