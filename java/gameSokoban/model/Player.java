package gameSokoban.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(0xff0000));
        graphics.drawOval(getX()-getWidth()/2, getY()-getHeight()/2, getWidth(), getHeight());
        graphics.fillOval(getX()-getWidth()/2, getY()-getHeight()/2, getWidth(), getHeight());
    }
}
