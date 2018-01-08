package gameArkanoid;

public class Ball extends BaseObject {
    private double speed;
    private double direction;

    private double dx;
    private double dy;

    private boolean isFrozen;

    public Ball(double x, double y, double speed, double direction) {
        super(x, y, 1);

        this.direction = direction;
        this.speed = speed;

        this.isFrozen = true;
    }

    public double getDy() {
        return dy;
    }


    void setDirection(double direction) {
        this.direction = direction;

        double angel = Math.toRadians(direction);
        dx = Math.cos(angel) * speed;
        dy = -Math.sin(angel) * speed;
    }



    public void move() {
        if (isFrozen) return;
        x += dx;
        y += dy;

        checkRebound(1, _ArkanoidLauncher.game.getWidth(), 1, _ArkanoidLauncher.game.getHeight() + 5);
    }

    void checkRebound(int minX, int maxX, int minY, int maxY) {
        if (x < minX) {
            x = minX + (minX - x);
            dx = -dx;
        }

        if (x > maxX) {
            x = maxX - (x - maxX);
            dx = -dx;
        }

        if (y < minY) {
            y = minY + (minY - y);
            dy = -dy;
        }

        if (y > maxY) {
            y = maxY - (y - maxY);
            dy = -dy;
        }
    }

    void start() {
        this.setDirection(direction);
        this.isFrozen = false;
    }


    @Override
    void draw(Canvas canvas) {
        canvas.setPoint(x, y, 'O');
    }
}
