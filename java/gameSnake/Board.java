package gameSnake;

//  Based on this tutorial http://zetcode.com/tutorials/javagamestutorial/snake/
//  learning to write from UML
//  To do:
//  -highscore board
//  -ability to chose level


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener {
    private final int ELEMENT_SIZE = 20;
    private final int BOARD_WIDTH = 400;
    private final int BOARD_HEIGHT = 300;
    private final int MAX_SNAKE_LENGTH = (BOARD_WIDTH * BOARD_HEIGHT) / (ELEMENT_SIZE * ELEMENT_SIZE);
    private final int GAME_SPEED = 150;
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
        addKeyListener(new CustomKeyAdapter());
        setBackground(new Color(0xCC9900));
        setFocusable(true);

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        loadImages();
        initGame();
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
                        break;
                    }
                    break;
                case  KeyEvent.VK_RIGHT:
                    if(!isLeft) {
                        isRight = true;
                        isUp    = false;
                        isDown  = false;
                        break;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(!isUp) {
                        isDown  = true;
                        isLeft  = false;
                        isRight = false;
                        break;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (!isDown) {
                        isUp    = true;
                        isLeft  = false;
                        isRight = false;
                        break;
                    }
                    break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            checkMouse();
            checkCollision();
            move();
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        if (!isGameOver) {
            g.drawImage(mouse, mouseX, mouseY, this);
            for(int i = 1; i < snakeElementCounter; i++)
                g.drawImage(body, snakeX[i], snakeY[i], this);

            g.drawImage(head, snakeX[0], snakeY[0], this);

        Toolkit.getDefaultToolkit().sync();

        } else gameOver(g);
    }

    private void checkMouse() {
        if (snakeX[0] == mouseX && snakeY[0] == mouseY) {
            snakeElementCounter++;
            generateMouse();
        }
    }

    private void checkCollision() {
        // collision with snake body
        for (int i = 1; i < snakeElementCounter; i++)
            if (snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0])
                isGameOver = true;

        // collision with walls
//        if(snakeX[0] < 0)               isGameOver = true;
//        if(snakeX[0] >= BOARD_WIDTH)    isGameOver = true;
//        if(snakeY[0] < 0)               isGameOver = true;
//        if(snakeY[0] >= BOARD_WIDTH)    isGameOver = true;

        if (
            snakeX[0] < 0 |
            snakeY[0] < 0 |
            snakeX[0] >= BOARD_WIDTH  - ELEMENT_SIZE |
            snakeY[0] >= BOARD_HEIGHT - ELEMENT_SIZE
               ) isGameOver = true;

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

    private void gameOver(Graphics g) {
        String message = "GAME OVER";
        Font font = new Font("Helvetica", Font.BOLD, 28);
        int messageSize = getFontMetrics(font).stringWidth(message);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, (BOARD_WIDTH - messageSize) / 2, BOARD_HEIGHT /2 );
    }

    private void initGame() {
        // create snake
        snakeElementCounter = 3;
        for(int i = 0; i < snakeElementCounter; i++) {
            snakeX[i] = (BOARD_WIDTH / ELEMENT_SIZE * 4) - (i * ELEMENT_SIZE);
            snakeY[i] = BOARD_HEIGHT / ELEMENT_SIZE * 4;
        }
            generateMouse();
            isGameOver = false;

            timer = new Timer(GAME_SPEED, this);
            timer.start();

    }

    private void loadImages() {
        // temp absolute file paths

        ImageIcon bodyIcon = new ImageIcon("D:\\Java\\src\\main\\java\\gameSnake\\body.png");
        body = bodyIcon.getImage();

        ImageIcon headIcon = new ImageIcon("D:\\Java\\src\\main\\java\\gameSnake\\head.png");
        head = headIcon.getImage();

        ImageIcon mouseIcon = new ImageIcon("D:\\Java\\src\\main\\java\\gameSnake\\mouse.png");
        mouse = mouseIcon.getImage();
    }

    private XY generateMouse() {
        final int RANDOM_X = BOARD_WIDTH / ELEMENT_SIZE - 1;
        final int RANDOM_Y = BOARD_HEIGHT / ELEMENT_SIZE - 1;

        int tmpX = ((int) (Math.random() * RANDOM_X)) * ELEMENT_SIZE;
        int tmpY = ((int) (Math.random() * RANDOM_Y)) * ELEMENT_SIZE;

        XY xy = new XY(tmpX,tmpY);

        for (int i = 0; i < MAX_SNAKE_LENGTH; i++) {

            if (snakeX[i] == tmpX && snakeY[i] == tmpY)
                xy = generateMouse();
        }

        mouseX = xy.x;
        mouseY = xy.y;
        return xy;
    }

    private class XY {
        int x,y;

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
