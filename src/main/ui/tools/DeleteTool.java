package ui.tools;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class DeleteTool extends Tool {

    public DeleteTool(JMenu menu, ActionListener listener) {
        super(menu, "Delete a card", "delete", KeyEvent.VK_D);
        addListener(listener);
    }

    @Override
    protected void addListener(ActionListener listener) {
        menuItem.addActionListener(listener);
    }
}
