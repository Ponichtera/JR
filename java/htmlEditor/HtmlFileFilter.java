package htmlEditor;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HtmlFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        else if (f.getName().toLowerCase().endsWith(".html") || f.getName().toLowerCase().endsWith(".htm"))
            return true;

        return false;
    }

    @Override
    public String getDescription() {
        return "html and htm files";
    }
}
