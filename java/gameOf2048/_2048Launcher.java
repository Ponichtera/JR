package gameOf2048;


import javax.swing.*;

public class _2048Launcher extends JFrame{
    _2048Launcher() {
        Controller controller = new Controller(new Model());

        setTitle("2048");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(450, 500);
        setResizable(false);
        add(controller.getView());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        _2048Launcher game = new _2048Launcher();
    }
}
