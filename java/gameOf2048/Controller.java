package gameOf2048;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    public final int WINNING_TILE = 2048;
    private Model model;
    private View view;


    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    public int getScore() {
       return model.getScore();
    }

    public View getView() {
        return view;
    }

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    public  void resetGame() {
        model.setScore(0);
        view.isGameLost = false;
        view.isGameWon = false;
        model.resetTiles();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        view.isGameLost = !model.canMove();

        if ( !view.isGameLost && !view.isGameWon)
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:    resetGame();    break;
                case KeyEvent.VK_UP:        model.up();     break;
                case KeyEvent.VK_DOWN:      model.down();   break;
                case KeyEvent.VK_LEFT:      model.left();   break;
                case KeyEvent.VK_RIGHT:     model.right();  break;
                case KeyEvent.VK_Z:         model.rollback(); break;
            }

        view.isGameWon = (model.getMaxTile() == WINNING_TILE);
        view.repaint();
    }
}
