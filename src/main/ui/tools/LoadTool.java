package ui.tools;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class LoadTool extends Tool {

    public LoadTool(JMenu menu, ActionListener listener) {
        super(menu, "Load a collection", "load", KeyEvent.VK_L);
        addListener(listener);
    }

    @Override
    protected void addListener(ActionListener listener) {
        menuItem.addActionListener(listener);
    }

}
