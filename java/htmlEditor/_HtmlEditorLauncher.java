package htmlEditor;

public class _HtmlEditorLauncher {
    public static void main(String[] args) {
            View view = new View();
            Controller controller = new Controller(view);
            view.setController(controller);
            view.init();
            controller.init();
    }
}
