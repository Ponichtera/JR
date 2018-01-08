package gameSokoban.controller;


import gameSokoban.model.Model;
import gameSokoban.view.View;

public class Controller {
    private View view;
    private Model model;


    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        init();
    }

    public void init() {

    }
}
