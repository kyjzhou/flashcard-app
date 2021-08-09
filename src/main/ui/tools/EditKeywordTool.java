package ui.tools;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class EditKeywordTool extends Tool {

    public EditKeywordTool(JMenu menu, ActionListener listener) {
        super(menu, "Flashcard keyword", "keyword", KeyEvent.VK_K);
        addListener(listener);
    }

    @Override
    protected void addListener(ActionListener listener) {
        menuItem.addActionListener(listener);
    }
}
