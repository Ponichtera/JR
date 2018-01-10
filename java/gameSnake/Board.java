package gameSnake;

//  Based on this tutorial http://zetcode.com/tutorials/javagamestutorial/snake/
//  learning to write from UML

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener {
    private final int ELEMENT_SIZE = 20;
    private final int BOARD_WIDTH = 800;
    private final int BOARD_HEIGHT = 600;
    private final int MAX_SNAKE_LENGTH = (BOARD_WIDTH * BOARD_HEIGHT) / (ELEMENT_SIZE * ELEMENT_SIZE);
    private final int GAME_SPEED = 130; // pause between moves (in milliseconds)
    private final int RANDOM_X = BOARD_WIDTH / ELEMENT_SIZE - 1;
    private final int RANDOM_Y = BOARD_HEIGHT / ELEMENT_SIZE - 1;

//      arrays of all snake elements coordinates
    private final int[] snakeX = new int[MAX_SNAKE_LENGTH];
    private final int[] snakeY = new int[MAX_SNAKE_LENGTH];

//      direction flags
    private boolean isLeft;
    private boolean isRight = true;
    private boolean isUp;
    private boolean isDown;

//      images
    private Image mouse;
    private Image head;
    private Image body;

//      others
    private Timer timer;
    private boolean isGameOver = true;
    private int snakeElementCounter;
    private int mouseX;
    private int mouseY;

    // -----------------------------------------------------------

    public Board() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(new Color(0x7BA352));
        setFocusable(true);
        addKeyListener(new CustomKeyAdapter());

        initGame();
        setVisible(true);
    }


    // -----------------------------------------------------------

    private class CustomKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key){
                case KeyEvent.VK_LEFT:
                    if(!isRight) {
                        isLeft  = true;
                        isUp    = false;
                        isDown  = false;
                    }
                case  KeyEvent.VK_RIGHT:
                    if(!isLeft) {
                        isRight = true;
                        isUp    = false;
                        isDown  = false;
                    }
                case KeyEvent.VK_DOWN:
                    if(!isUp) {
                        isDown  = true;
                        isLeft  = false;
                        isRight = false;
                    }
                case KeyEvent.VK_UP:
                    if (!isDown) {
                        isUp    = true;
                        isLeft  = false;
                        isRight = false;
                    }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
    public void paintComponent(Graphics g){}
    private void checkMouse() {
        if (snakeX[0] == mouseX && snakeY[0] == mouseY) {
            snakeElementCounter++;
            drawMouse();
        }
    }
    private void checkCollision() {}
    private void doDrawing(Graphics g) {}
    private void gameOver(Graphics g) {}

    private void initGame() {
        // create snake
        snakeElementCounter = 3;
        for(int i = 0; i < snakeElementCounter; i++) {
            snakeX[i] = (BOARD_WIDTH / ELEMENT_SIZE / 4) - i * ELEMENT_SIZE;
            snakeY[i] = BOARD_HEIGHT / ELEMENT_SIZE / 4;

            drawMouse();
            isGameOver = false;

            timer = new Timer(GAME_SPEED, this);
            timer.start();
        }

    }
    private void loadImages() {}

    private void drawMouse() {
       int tmpX = (int) (Math.random() * RANDOM_X);
       int tmpY = (int) (Math.random() * RANDOM_Y);

       for(int i = 0; i < MAX_SNAKE_LENGTH; i++) {
           if (snakeX[i] == 0) break;
           if (snakeX[i] == tmpX && snakeY[i] == tmpY)
               drawMouse();
       }
       mouseX = tmpX;
       mouseY = tmpY;
    }

    private void move() {
        //  all elements move one position up the chain
        for (int i = snakeElementCounter; i > 0; i--) {
            snakeX[i] = snakeX[i-1];
            snakeY[i] = snakeY[i-1];
        }
        if (isLeft)     snakeX[0] -= ELEMENT_SIZE;
        if (isRight)    snakeX[0] += ELEMENT_SIZE;
        if (isUp)       snakeY[0] -= ELEMENT_SIZE;
        if (isDown)     snakeY[0] += ELEMENT_SIZE;

    }
}
