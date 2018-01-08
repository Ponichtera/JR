package htmlEditor.actions;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;

public class SuperscriptAction extends StyledEditorKit.StyledTextAction {

    public SuperscriptAction() {
        super(StyleConstants.Superscript.toString());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JEditorPane editorPane = getEditor(e);
        if(editorPane != null) {
            MutableAttributeSet mutableAttributeSet = getStyledEditorKit(editorPane).getInputAttributes();
            SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
            StyleConstants.setSuperscript(mutableAttributeSet, !StyleConstants.isSuperscript(mutableAttributeSet));
            setCharacterAttributes(editorPane, simpleAttributeSet, false);
        }
    }
}
