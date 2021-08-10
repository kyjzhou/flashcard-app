package ui.tools;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// A tool that deletes a flashcard button
// Class structure based on subclasses of Tool class in SimpleDrawingPlayer
//      (https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter)
public class DeleteTool extends Tool {

    // EFFECTS: constructs a delete tool with given action listener in given menu
    public DeleteTool(JMenu menu, ActionListener listener) {
        super(menu, "Delete selected card", "delete", KeyEvent.VK_DELETE);
        addListener(listener);
    }

    // MODIFIES: this
    // EFFECTS: adds given action listener to this tool's menu item
    @Override
    protected void addListener(ActionListener listener) {
        menuItem.addActionListener(listener);
    }
}
