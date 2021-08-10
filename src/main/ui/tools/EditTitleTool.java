package ui.tools;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// A tool that edits a flashcard collection title
// Class structure based on subclasses of Tool class in SimpleDrawingPlayer
//      (https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter)
public class EditTitleTool extends Tool {

    // EFFECTS: constructs an edit collection title tool with given action listener in given menu
    public EditTitleTool(JMenu menu, ActionListener listener) {
        super(menu, "Edit collection title", "title", KeyEvent.VK_T);
        addListener(listener);
    }

    // MODIFIES: this
    // EFFECTS: adds given action listener to this tool's menu item
    @Override
    protected void addListener(ActionListener listener) {
        menuItem.addActionListener(listener);
    }
}
