package gameSokoban.view;

import gameSokoban.controller.Controller;
import gameSokoban.controller.EventListener;

import javax.swing.*;

public class View extends JFrame {
    private Controller controller;
    private EventListener eventListener;

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
