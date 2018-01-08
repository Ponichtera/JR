package gameSokoban.controller;


import gameSokoban.model.Direction;
import gameSokoban.model.Model;
import gameSokoban.view.View;

public class Controller implements EventListener{
    private View view;
    private Model model;


    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        init();
    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public void restart() {

    }

    @Override
    public void startNextLevel() {

    }

    @Override
    public void levelCompleted(int level) {

    }

    public void init() {

    }
}
