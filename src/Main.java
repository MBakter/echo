import controller.Commands;
import controller.Controller;
import view.MainWindow;

public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller("maps");

        Commands commands = new Commands(controller);

        MainWindow view = new MainWindow(controller, "`(*>﹏<*)′");
        view.setVisible(true);

        controller.startGameWithGUI(view);

    } 
}
