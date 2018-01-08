package gameArkanoid;

public class Stand extends BaseObject {
    private double speed = 1;
    private double direction = 0;
    private static int[][] matrix = {
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
    };

    public Stand(double x, double y) {
        super(x, y, 3);
    }

    void move() {
        double dx = speed * direction;
        x = x + dx;

        checkBorders(radius, _ArkanoidLauncher.game.getWidth() - radius + 1, 1, _ArkanoidLauncher.game.getHeight() + 1);
    }

    void moveLeft() {
        direction = -1;
    }

    void moveRight() {
        direction = 1;
    }


    @Override
    void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'M');
    }
}
