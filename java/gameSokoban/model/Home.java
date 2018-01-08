package gameSokoban.model;


import java.awt.*;

public class Home extends defaultGameObject {
    public Home(int x, int y) {
        super(x, y);
        setHeight(2);
        setWidth(2);
    }


    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(0x00aa00));
        graphics.drawRect(getX()-getWidth()/2, getY()-getHeight()/2, getWidth(), getHeight());
        graphics.fillRect(getX()-getWidth()/2, getY()-getHeight()/2, getWidth(), getHeight());
    }
}
