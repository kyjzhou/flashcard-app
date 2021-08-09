package ui.tools;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AddTool extends Tool {

    public AddTool(JMenu menu, ActionListener listener) {
        super(menu, "Add new card", "add", KeyEvent.VK_A);
        addListener(listener);
    }

    @Override
    protected void addListener(ActionListener listener) {
        menuItem.addActionListener(listener);
    }

}
