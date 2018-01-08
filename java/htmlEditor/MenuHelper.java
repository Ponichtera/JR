package htmlEditor;

import htmlEditor.actions.*;
import htmlEditor.listeners.TextEditMenuListener;
import htmlEditor.listeners.UndoMenuListener;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuHelper {

    public static JMenuItem addMenuItem(JMenu parent, String text, ActionListener actionListener) {
        JMenuItem menuItem =  new JMenuItem(text);
        menuItem.addActionListener(actionListener);
        parent.add(menuItem);
        return menuItem;
    }

    public static JMenuItem addMenuItem (JMenu parent, String text, Action action) {
        JMenuItem menuItem = addMenuItem(parent, action);
        menuItem.setText(text);
        return menuItem;
    }
    public static JMenuItem addMenuItem(JMenu parent, Action action) {
        JMenuItem menuItem = new JMenuItem(action);
        parent.add(menuItem);
        return menuItem;
    }

    public static void initFileMenu(View view, JMenuBar menuBar) {
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        addMenuItem(fileMenu, "New", view);
        addMenuItem(fileMenu, "Open", view);
        addMenuItem(fileMenu,"Save", view);
        addMenuItem(fileMenu,"Save as ...", view);
        fileMenu.addSeparator();
        addMenuItem(fileMenu,"Exit", view);
    }

    public static void initEditMenu(View view, JMenuBar menuBar) {
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        JMenuItem undoItem = addMenuItem(editMenu, "Undo", new UndoAction(view));
        JMenuItem redoItem = addMenuItem(editMenu, "Redo", new RedoAction(view));

        addMenuItem(editMenu,"Cut" , new DefaultEditorKit.CutAction());
        addMenuItem(editMenu, "Copy", new DefaultEditorKit.CopyAction());
        addMenuItem(editMenu, "Paste", new DefaultEditorKit.PasteAction());

        editMenu.addMenuListener(new UndoMenuListener(view, undoItem, redoItem));
    }

    public static void initStyleMenu(View view, JMenuBar menuBar) {
        JMenu styleMenu = new JMenu("Style");
        menuBar.add(styleMenu);

        addMenuItem(styleMenu, "Bold", new StyledEditorKit.BoldAction());
        addMenuItem(styleMenu, "Italic", new StyledEditorKit.ItalicAction());
        addMenuItem(styleMenu, "Underline", new StyledEditorKit.UnderlineAction());

        styleMenu.addSeparator();
        addMenuItem(styleMenu, "Subscript", new SubscriptAction());
        addMenuItem(styleMenu, "Superscript", new SuperscriptAction());
        addMenuItem(styleMenu, "Strikethrough", new StrikeThroughAction());
        
        styleMenu.addMenuListener(new TextEditMenuListener(view));
    }

    public static void initAlignMenu(View view, JMenuBar menuBar) {
        JMenu alignMenu = new JMenu("Align");
        menuBar.add(alignMenu);

        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("Left margin", StyleConstants.ALIGN_LEFT));
        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("Center", StyleConstants.ALIGN_CENTER));
        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("Right margin", StyleConstants.ALIGN_RIGHT));

        alignMenu.addMenuListener(new TextEditMenuListener(view));
    }

    public static void initFontMenu(View view, JMenuBar menuBar) {
        JMenu fontMenu = new JMenu("Font");
        menuBar.add(fontMenu);

        JMenu fontTypeMenu = new JMenu("Font type");
        fontMenu.add(fontTypeMenu);

        String[] fontTypes = {
                Font.SANS_SERIF,
                Font.SERIF,
                Font.MONOSPACED,
                Font.DIALOG,
                Font.DIALOG_INPUT
        };
        for(String fontType : fontTypes)
            addMenuItem(fontTypeMenu, fontType, new StyledEditorKit.FontFamilyAction(fontType, fontType));

        JMenu fontSizeMenu = new JMenu("Font size");
        fontMenu.add(fontSizeMenu);

        String[] fontSizes = { "6", "8", "10", "12", "14", "16", "20", "24", "32", "36", "48", "72"};
        for(String fontSize : fontSizes)
            addMenuItem(fontSizeMenu, fontSize, new StyledEditorKit.FontSizeAction(fontSize, Integer.parseInt(fontSize)));

        fontMenu.addMenuListener(new TextEditMenuListener(view));
    }

    public static void initColorMenu(View view, JMenuBar menuBar) {
        JMenu colorMenu = new JMenu("Color");
        menuBar.add(colorMenu);

        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Red", Color.RED));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Orange", Color.ORANGE));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Yellow", Color.YELLOW));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Green", Color.GREEN));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Blue", Color.BLUE));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Cyan", Color.CYAN));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Purple", Color.MAGENTA));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Black", Color.BLACK));

        colorMenu.addMenuListener(new TextEditMenuListener(view));
    }

    public static void initHelpMenu(View view, JMenuBar menuBar) {
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        addMenuItem(helpMenu, "About", view);
    }
}
