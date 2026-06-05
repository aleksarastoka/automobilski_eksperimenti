package rs.raf.bp.automobili.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rs.raf.bp.automobili.model.UserRepository;
import rs.raf.bp.automobili.view.AdminView;

public class LoginControl implements EventHandler<ActionEvent> {

    private final TextField usernameField;
    private final PasswordField passwordField;
    private final Stage currentStage;

    public LoginControl(TextField usernameField, PasswordField passwordField, Stage currentStage) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.currentStage = currentStage;
    }

    @Override
    public void handle(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            new Alert(Alert.AlertType.ERROR, "Unesite korisnicko ime i lozinku.", ButtonType.OK).showAndWait();
            return;
        }

        boolean validLogin = UserRepository.login(username.trim(), password.trim());
        if (!validLogin) {
            new Alert(Alert.AlertType.ERROR, "Pogresno korisnicko ime ili lozinka.", ButtonType.OK).showAndWait();
            return;
        }

        currentStage.close();
        new AdminView(username.trim()).show();
    }
}
