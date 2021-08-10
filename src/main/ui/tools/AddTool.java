package ui.tools;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// A tool that adds a flashcard button
// Class structure based on subclasses of Tool class in SimpleDrawingPlayer
//      (https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter)
public class AddTool extends Tool {

    // EFFECTS: constructs an add tool with given action listener in given menu
    public AddTool(JMenu menu, ActionListener listener) {
        super(menu, "Add new card", "add", KeyEvent.VK_A);
        addListener(listener);
    }

    // MODIFIES: this
    // EFFECTS: adds given action listener to this tool's menu item
    @Override
    protected void addListener(ActionListener listener) {
        menuItem.addActionListener(listener);
    }

}
