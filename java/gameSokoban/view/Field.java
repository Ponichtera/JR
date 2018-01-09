package gameSokoban.view;

import gameSokoban.controller.EventListener;
import gameSokoban.model.Direction;
import gameSokoban.model.DefaultGameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override
    public void paint(Graphics g) {
       g.setColor(Color.BLACK);
       g.fillRect(0, 0, 500, 500);
        Set<DefaultGameObject> gameObjects = view.getGameObjects().getAllGameObjects();

        for (DefaultGameObject gameObject : gameObjects)
            gameObject.draw(g);
    }

    public class KeyHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case (KeyEvent.VK_LEFT):    eventListener.move(Direction.LEFT);     break;
                case (KeyEvent.VK_RIGHT):   eventListener.move(Direction.RIGHT);    break;
                case (KeyEvent.VK_UP):      eventListener.move(Direction.UP);       break;
                case (KeyEvent.VK_DOWN):    eventListener.move(Direction.DOWN);     break;
                case (KeyEvent.VK_R):       eventListener.restart();
            }
        }
    }
}
