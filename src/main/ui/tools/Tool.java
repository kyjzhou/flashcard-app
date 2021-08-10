package ui.tools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A tool for performing actions when a menu item is clicked
// Class structure based on Tool class in SimpleDrawingPlayer
//      (https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter)
public abstract class Tool {

    protected JMenuItem menuItem;

    // EFFECTS: constructs a tool with given text, command, and keyboard shortcut in given menu
    public Tool(JMenu menu, String text, String command, int shortcut) {
        menuItem = new JMenuItem(text);
        addMenuItemToMenu(menu, command, shortcut);
    }

    // MODIFIES: this
    // EFFECTS: sets this tool's menu item's command and keyboard shortcut and adds it to given menu
    public void addMenuItemToMenu(JMenu menu, String command, int shortcut) {
        menuItem.setAccelerator(KeyStroke.getKeyStroke(shortcut, ActionEvent.CTRL_MASK));
        menuItem.setActionCommand(command);
        menu.add(menuItem);
    }

    // MODIFIES: this
    // EFFECTS: adds given action listener to this tool's menu item
    protected abstract void addListener(ActionListener listener);
}
