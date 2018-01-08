package gameSokoban;

import gameSokoban.controller.Controller;
import gameSokoban.model.Model;
import gameSokoban.view.Field;
import gameSokoban.view.View;

public class _SokobanLauncher {
    public static void main(String[] args) {
        Model m  = new Model();
        View v = new View();
        Controller c = new Controller(v, m);
        v.setController(c);

        Field f =new Field(v);
    }
}
