package rs.raf.bp.automobili;

import javafx.application.Application;
import javafx.stage.Stage;
import rs.raf.bp.automobili.view.LoginView;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        new LoginView().show();
    }
}
