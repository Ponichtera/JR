package gameSokoban.view;

import gameSokoban.model.Box;
import gameSokoban.model.Player;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {
    private View view;

    public Field(View view) {
        this.view = view;
    }


    @Override
    public void print(Graphics g) {
        Player p = new Player(20, 20);
        p.draw(g);
        Box b = new Box(60, 60);
        b.draw(g);
    }
}
