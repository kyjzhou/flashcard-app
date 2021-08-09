package ui.tools;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class EditDescriptionTool extends Tool {

    public EditDescriptionTool(JMenu menu, ActionListener listener) {
        super(menu, "Flashcard description", "description", KeyEvent.VK_E);
        addListener(listener);
    }

    @Override
    protected void addListener(ActionListener listener) {
        menuItem.addActionListener(listener);
    }
}
