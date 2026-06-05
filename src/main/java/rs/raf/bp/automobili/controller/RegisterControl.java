package rs.raf.bp.automobili.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import rs.raf.bp.automobili.model.UserRepository;

public class RegisterControl implements EventHandler<ActionEvent> {

    private final TextField usernameField;
    private final PasswordField passwordField;

    public RegisterControl(TextField usernameField, PasswordField passwordField) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
    }

    @Override
    public void handle(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            new Alert(Alert.AlertType.ERROR, "Korisnicko ime i lozinka ne smeju biti prazni.", ButtonType.OK).showAndWait();
            return;
        }

        boolean registered = UserRepository.register(username.trim(), password.trim());
        if (!registered) {
            new Alert(Alert.AlertType.ERROR, "Korisnicko ime vec postoji.", ButtonType.OK).showAndWait();
            return;
        }

        usernameField.clear();
        passwordField.clear();
        new Alert(Alert.AlertType.INFORMATION, "Korisnik je registrovan.", ButtonType.OK).showAndWait();
    }
}
