package htmlEditor.listeners;

import htmlEditor.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class UndoMenuListener implements MenuListener {
    private JMenuItem undoMenu, redoMenu;
    private View view;

    public UndoMenuListener(View view,JMenuItem undoMenu, JMenuItem redoMenu) {
        this.undoMenu = undoMenu;
        this.redoMenu = redoMenu;
        this.view = view;
    }


    @Override
    public void menuSelected(MenuEvent e) {
        undoMenu.setEnabled(view.canUndo());
        redoMenu.setEnabled(view.canRedo());
    }

    @Override
    public void menuDeselected(MenuEvent e) {}

    @Override
    public void menuCanceled(MenuEvent e) {}
}
