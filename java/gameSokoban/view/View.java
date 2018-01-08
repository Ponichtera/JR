package gameSokoban.view;

import gameSokoban.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    Controller controller;

    public View(Controller controller) throws HeadlessException {
        this.controller = controller;
    }
}
