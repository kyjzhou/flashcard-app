package ui.tools;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class EditTitleTool extends Tool {

    public EditTitleTool(JMenu menu, ActionListener listener) {
        super(menu, "Collection title", "title", KeyEvent.VK_T);
        addListener(listener);
    }

    @Override
    protected void addListener(ActionListener listener) {
        menuItem.addActionListener(listener);
    }
}
