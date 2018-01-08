package gameSokoban.model;

import gameSokoban.controller.EventListener;

import java.nio.file.Paths;

public class Model {
    public static int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("/resources/Levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        this.gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        this.gameObjects = levelLoader.getLevel(currentLevel);
    }

    public void startNewLevel() {
        currentLevel++;
        restart();
    }
}
