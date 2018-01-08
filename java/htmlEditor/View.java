package htmlEditor;

import htmlEditor.listeners.FrameListener;
import htmlEditor.listeners.TabbedPaneChangeListener;
import htmlEditor.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane textPane = new JTextPane();
    private JEditorPane htmlPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);


    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { ExceptionHandler.log(e); }
    }

    public Controller getController() {
        return controller;
    }

    public void setController (Controller controller) {
        this.controller = controller;
    }

    public void init() {
        initGUI();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);
    }

    public void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        this.add(menuBar, BorderLayout.NORTH);
    }

    public void initEditor() {
        htmlPane.setContentType("text/html");
        tabbedPane.add("Html", new JScrollPane(htmlPane));
        tabbedPane.add("Text", new JScrollPane(textPane));

        tabbedPane.setPreferredSize(new Dimension(1000, 800));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGUI() {
        initEditor();
        initMenuBar();
        this.pack();
    }

    public void selectedTabChanged() {
        if (isHtmlTabSelected())
            controller.setPlainText(textPane.getText());
        else textPane.setText(controller.getPlainText());

        resetUndo();
    }

    public boolean isHtmlTabSelected () {
        return tabbedPane.getSelectedIndex() == 0;
    }

    public void exit () {
        System.exit(0);
    }

    public UndoListener getUndoListener () {
        return undoListener;
    }

    public void resetUndo () {
        undoManager.discardAllEdits();
    }

    public boolean canUndo () {
        return undoManager.canUndo();
    }

    public void undo ()  {
        try {
            undoManager.undo();
        } catch (CannotUndoException e) { ExceptionHandler.log(e); }
    }

    public boolean canRedo () {
        return undoManager.canRedo();
    }

    public void redo () {
        try {
            undoManager.redo();
        } catch (CannotRedoException e) { ExceptionHandler.log(e); }
    }

    public void selectHtmlTab () {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update () {
        htmlPane.setDocument(controller.getDocument());
    }

    public void about () {
        JOptionPane.showMessageDialog(this, "Html editor coded in java", "About", JOptionPane.INFORMATION_MESSAGE);
    }


    @Override
    public void actionPerformed (ActionEvent e) {
       String commandName = e.getActionCommand();

       switch (commandName) {
           case "New":
               controller.createNewDocument();
               break;
           case "Open":
               controller.openDocument();
               break;
           case "Save":
               controller.saveDocument();
               break;
           case "Save as ...":
               controller.saveDocumentAs();
               break;
           case "Exit":
               controller.exit();
               break;
           case "About":
               about();
       }
    }
}
