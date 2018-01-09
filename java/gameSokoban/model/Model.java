package gameSokoban.model;

import gameSokoban.controller.EventListener;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Model {
    final static int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;

    private final Path LEVEL_PATH = Paths.get("src/main/java/gameSokoban/resources/Levels.txt");


    private LevelLoader levelLoader = new LevelLoader(LEVEL_PATH);

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();

        if (checkWallCollision(player, direction)) {
            return;
        }
        if (checkBoxCollisionAndMoveIfAvailable(direction)) {
            return;
        }

        switch (direction) {
            case LEFT:      player.move(-FIELD_CELL_SIZE, 0);   break;
            case RIGHT:     player.move(FIELD_CELL_SIZE, 0);    break;
            case UP:        player.move(0, -FIELD_CELL_SIZE);   break;
            case DOWN:      player.move(0, FIELD_CELL_SIZE);
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls())
            if (gameObject.isCollision(wall, direction))
                return true;

        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        Player player = gameObjects.getPlayer();
        DefaultGameObject stopped = null;

        for (DefaultGameObject gameObject : gameObjects.getAllGameObjects())
            if (!(gameObject instanceof Player) && !(gameObject instanceof Home) && player.isCollision(gameObject, direction))
                stopped = gameObject;

        if (stopped == null) return false;

        if (stopped instanceof Box) {
            if (checkWallCollision((Box) stopped, direction)) return true;

            for (Box box : gameObjects.getBoxes())
                if (((Box) stopped).isCollision(box, direction))
                    return true;

            switch (direction) {
                case LEFT:  ((Box) stopped).move(-FIELD_CELL_SIZE, 0);
                    break;
                case RIGHT: ((Box) stopped).move(FIELD_CELL_SIZE, 0);
                    break;
                case UP:    ((Box) stopped).move(0, -FIELD_CELL_SIZE);
                    break;
                case DOWN:  ((Box) stopped).move(0, FIELD_CELL_SIZE);
            }
        }
        return false;
    }

    public void checkCompletion() {
        boolean isLevelCompleted = true;

        for (Home home : gameObjects.getHomes()) {
            boolean check = false;

            for (Box box : gameObjects.getBoxes())
                if ((box.getX() == home.getX()) && (box.getY() == home.getY()))
                    check = true;

            if (!check) isLevelCompleted = false;
        }

        if (isLevelCompleted)
            eventListener.levelCompleted(currentLevel);
    }
}