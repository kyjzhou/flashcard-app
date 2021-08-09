package ui.tools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Tool {

    protected JMenuItem menuItem;
    protected boolean isSelected;

    public Tool(JMenu menu, String text, String command, int shortcut) {
        menuItem = new JMenuItem(text);
        isSelected = false;
        addMenuItemToMenu(menu, command, shortcut);
    }

    public void select() {
        if (!isSelected) {
            isSelected = true;
        }
    }

    public void unselect() {
        if (isSelected) {
            isSelected = false;
        }
    }

    public void addMenuItemToMenu(JMenu menu, String command, int shortcut) {
        menuItem.setAccelerator(KeyStroke.getKeyStroke(shortcut, ActionEvent.CTRL_MASK));
        menuItem.setActionCommand(command);
        menu.add(menuItem);
    }

    protected abstract void addListener(ActionListener listener);
}
