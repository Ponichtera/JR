package gameArkanoid;

public class _ArkanoidLauncher {
    static Arkanoid game = new Arkanoid(20, 30);

    public static void main(String[] args) {
        Ball ball = new Ball(game.getWidth()/2, game.getHeight()-1, 2, 95);
        game.setBall(ball);

        Stand stand = new Stand(10, 30);
        game.setStand(stand);

        game.getBricks().add(new Brick(3, 3));
        game.getBricks().add(new Brick(7, 5));
        game.getBricks().add(new Brick(12, 5));
        game.getBricks().add(new Brick(16, 3));

        try {
            game.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
