package gameSokoban.controller;


import gameSokoban.model.Direction;
import gameSokoban.model.GameObjects;
import gameSokoban.model.Model;
import gameSokoban.view.View;

public class Controller implements EventListener{
    private View view;
    private Model model;


    public Controller() {
        view = new View(this);
        model = new Model();
        view.init();
        model.setEventListener(this);
        model.restart();
        view.setEventListener(this);
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }


    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }
}
