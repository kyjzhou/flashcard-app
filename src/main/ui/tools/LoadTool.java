package ui.tools;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// A tool that loads a flashcard collection from file
// Class structure based on subclasses of Tool class in SimpleDrawingPlayer
//      (https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter)
public class LoadTool extends Tool {

    // EFFECTS: constructs a load tool with given action listener in given menu
    public LoadTool(JMenu menu, ActionListener listener) {
        super(menu, "Load a collection", "load", KeyEvent.VK_L);
        addListener(listener);
    }

    // MODIFIES: this
    // EFFECTS: adds given action listener to this tool's menu item
    @Override
    protected void addListener(ActionListener listener) {
        menuItem.addActionListener(listener);
    }

}
