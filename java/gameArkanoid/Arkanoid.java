package gameArkanoid;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Arkanoid {
    private int width;
    private int height;
    private Ball ball;
    private Stand stand;
    private ArrayList<Brick> bricks = new ArrayList<>();
    private boolean isGameOver = false;

    Arkanoid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    void setStand(Stand stand) {
        this.stand = stand;
    }

    void setBall(Ball ball) {
        this.ball = ball;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    private void draw(Canvas canvas) {
        drawBorders(canvas);

        for (Brick brick : bricks) {
            brick.draw(canvas);
        }

        ball.draw(canvas);
        stand.draw(canvas);
    }

    private void drawBorders(Canvas canvas) {
        for (int i = 0; i < width + 2; i++) {
            for (int j = 0; j < height + 2; j++) {
                canvas.setPoint(i, j, '.');
            }
        }

        for (int i = 0; i < width + 2; i++) {
            canvas.setPoint(i, 0, '-');
            canvas.setPoint(i, height + 1, '-');
        }

        for (int i = 0; i < height + 2; i++) {
            canvas.setPoint(0, i, '|');
            canvas.setPoint(width + 1, i, '|');
        }
    }

    void run() throws Exception {
        Canvas canvas = new Canvas(width, height);

        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        while (!isGameOver) {
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();

                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    stand.moveLeft();
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    stand.moveRight();
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    ball.start();
            }
            move();

            checkBricksBump();
            checkStandBump();
            checkEndGame();

            canvas.clear();
            draw(canvas);
            canvas.print();

            Thread.sleep(300);
        }
        System.out.println("Game Over!");
    }

    private void move() {
        ball.move();
        stand.move();
    }

    private void checkBricksBump() {
        for (Brick brick : new ArrayList<>(bricks)) {
            if (ball.isIntersec(brick)) {
                double angle = Math.random() * 360;
                ball.setDirection(angle);
                bricks.remove(brick);
            }
        }
    }

    private void checkStandBump() {
        if (ball.isIntersec(stand)) {
            double angle = 90 + 20 * (Math.random() - 0.5);
            ball.setDirection(angle);
        }
    }

    private void checkEndGame() {
        if (ball.getY() > height && ball.getDy() > 0)
            isGameOver = true;
    }
}



















