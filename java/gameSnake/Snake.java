package gameSnake;

import javax.swing.*;
import java.awt.*;


public class Snake extends JFrame {
    Snake() {
        add(new Board());
        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ex = new Snake();
                ex.setVisible(true);
            }
        });
    }
}
