package rs.raf.bp.automobili.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import rs.raf.bp.automobili.controller.LoginControl;
import rs.raf.bp.automobili.controller.RegisterControl;

public class LoginView extends Stage {

    public LoginView() {
        super.setTitle("Prijava i registracija korisnika");

        TextField loginUsernameField = new TextField();
        PasswordField loginPasswordField = new PasswordField();
        Button loginButton = new Button("Prijavi se");

        TextField registerUsernameField = new TextField();
        PasswordField registerPasswordField = new PasswordField();
        Button registerButton = new Button("Registruj korisnika");

        int labelWidth = 135;
        int fieldWidth = 175;

        Label loginUsernameLabel = createLabel("Korisnicko ime:", labelWidth);
        Label loginPasswordLabel = createLabel("Lozinka:", labelWidth);
        Label registerUsernameLabel = createLabel("Novo korisnicko ime:", labelWidth);
        Label registerPasswordLabel = createLabel("Nova lozinka:", labelWidth);

        loginUsernameField.setPrefWidth(fieldWidth);
        loginPasswordField.setPrefWidth(fieldWidth);
        registerUsernameField.setPrefWidth(fieldWidth);
        registerPasswordField.setPrefWidth(fieldWidth);
        loginButton.setPrefWidth(fieldWidth);
        registerButton.setPrefWidth(fieldWidth);

        loginButton.setOnAction(new LoginControl(loginUsernameField, loginPasswordField, this));
        registerButton.setOnAction(new RegisterControl(registerUsernameField, registerPasswordField));

        Separator separator = new Separator();
        separator.setPrefWidth(labelWidth + 12 + fieldWidth);

        GridPane root = new GridPane();
        root.setPadding(new Insets(48, 30, 30, 55));
        root.setHgap(12);
        root.setVgap(14);
        root.setAlignment(Pos.TOP_LEFT);

        root.add(loginUsernameLabel, 0, 0);
        root.add(loginUsernameField, 1, 0);

        root.add(loginPasswordLabel, 0, 1);
        root.add(loginPasswordField, 1, 1);

        root.add(loginButton, 1, 2);

        root.add(separator, 0, 3, 2, 1);
        GridPane.setMargin(separator, new Insets(10, 0, 6, 0));

        root.add(registerUsernameLabel, 0, 4);
        root.add(registerUsernameField, 1, 4);

        root.add(registerPasswordLabel, 0, 5);
        root.add(registerPasswordField, 1, 5);

        root.add(registerButton, 1, 6);

        Scene scene = new Scene(root, 430, 315);
        super.setScene(scene);
    }

    private Label createLabel(String text, int width) {
        Label label = new Label(text);
        label.setMinWidth(width);
        label.setPrefWidth(width);
        label.setMaxWidth(width);
        label.setAlignment(Pos.CENTER_LEFT);
        return label;
    }
}
