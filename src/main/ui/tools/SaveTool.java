package ui.tools;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class SaveTool extends Tool {

    public SaveTool(JMenu menu, ActionListener listener) {
        super(menu, "Save this collection", "save", KeyEvent.VK_S);
        addListener(listener);
    }

    @Override
    protected void addListener(ActionListener listener) {
        menuItem.addActionListener(listener);
    }
}
