package rs.raf.bp.automobili.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rs.raf.bp.automobili.controller.AddLabResearcherControl;
import rs.raf.bp.automobili.controller.DeleteLaboratoryControl;
import rs.raf.bp.automobili.controller.RefreshLabResearchersControl;
import rs.raf.bp.automobili.controller.RefreshLaboratoriesControl;
import rs.raf.bp.automobili.controller.RemoveLabResearcherControl;
import rs.raf.bp.automobili.controller.RefreshSessionsControl;
import rs.raf.bp.automobili.controller.SelectLabResearcherControl;
import rs.raf.bp.automobili.controller.SelectSessionControl;
import rs.raf.bp.automobili.controller.UpdateSessionControl;
import rs.raf.bp.automobili.view.tables.LabResearcherTable;
import rs.raf.bp.automobili.view.tables.LaboratoryTable;
import rs.raf.bp.automobili.view.tables.ScheduledSessionTable;

import java.util.ArrayList;

public class AdminView extends Stage {

    public AdminView(String username) {
        super.setTitle("Administrator - automobilski eksperimenti");

        Label userLabel = new Label("Ulogovani korisnik: " + username + " | Uloga: administrator");

        TabPane tabPane = new TabPane();
        tabPane.getTabs().add(makeSessionTab());
        tabPane.getTabs().add(makeLaboratoryTab());
        tabPane.getTabs().add(makeResearcherLaboratoryTab());

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setTop(userLabel);
        root.setCenter(tabPane);

        Scene scene = new Scene(root, 1200, 700);
        super.setScene(scene);
    }

    private Tab makeSessionTab() {
        ScheduledSessionTable sessionTable = new ScheduledSessionTable(new ArrayList<>());

        TextField experimentIdField = new TextField();
        TextField executionNumberField = new TextField();
        TextField sessionNumberField = new TextField();
        TextField laboratoryIdField = new TextField();
        TextField dateField = new TextField();
        TextField startTimeField = new TextField();
        TextField endTimeField = new TextField();
        ComboBox<String> statusComboBox = new ComboBox<>();

        statusComboBox.getItems().add("zakazana");
        statusComboBox.getItems().add("u_toku");
        statusComboBox.getItems().add("zavrsena");
        statusComboBox.getItems().add("otkazana");

        experimentIdField.setEditable(false);
        executionNumberField.setEditable(false);
        sessionNumberField.setEditable(false);

        dateField.setPromptText("YYYY-MM-DD");
        startTimeField.setPromptText("HH:MM:SS");
        endTimeField.setPromptText("HH:MM:SS");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));
        form.add(new Label("Eksperiment ID:"), 0, 0);
        form.add(experimentIdField, 1, 0);
        form.add(new Label("Broj izvodjenja:"), 0, 1);
        form.add(executionNumberField, 1, 1);
        form.add(new Label("Broj sesije:"), 0, 2);
        form.add(sessionNumberField, 1, 2);
        form.add(new Label("Laboratorija ID:"), 0, 3);
        form.add(laboratoryIdField, 1, 3);
        form.add(new Label("Datum sesije:"), 0, 4);
        form.add(dateField, 1, 4);
        form.add(new Label("Vreme pocetka:"), 0, 5);
        form.add(startTimeField, 1, 5);
        form.add(new Label("Vreme zavrsetka:"), 0, 6);
        form.add(endTimeField, 1, 6);
        form.add(new Label("Status sesije:"), 0, 7);
        form.add(statusComboBox, 1, 7);

        Button updateButton = new Button("Sacuvaj izmenu sesije");

        updateButton.setOnAction(new UpdateSessionControl(sessionTable, experimentIdField,
                executionNumberField, sessionNumberField, laboratoryIdField, dateField,
                startTimeField, endTimeField, statusComboBox));

        sessionTable.setOnMouseClicked(new SelectSessionControl(sessionTable, experimentIdField,
                executionNumberField, sessionNumberField, laboratoryIdField, dateField,
                startTimeField, endTimeField, statusComboBox));

        new RefreshSessionsControl(sessionTable).handle(null);

        HBox buttons = new HBox(10);
        buttons.getChildren().add(updateButton);

        VBox rightSide = new VBox(10);
        rightSide.setPadding(new Insets(10));
        rightSide.getChildren().add(new Label("Promena podataka izabrane zakazane sesije"));
        rightSide.getChildren().add(form);
        rightSide.getChildren().add(buttons);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10));
        pane.setCenter(sessionTable);
        pane.setRight(rightSide);

        Tab tab = new Tab("Zakazane sesije");
        tab.setClosable(false);
        tab.setContent(pane);
        return tab;
    }

    private Tab makeLaboratoryTab() {
        LaboratoryTable laboratoryTable = new LaboratoryTable(new ArrayList<>());

        Button deleteButton = new Button("Obrisi izabranu laboratoriju");

        deleteButton.setOnAction(new DeleteLaboratoryControl(laboratoryTable));

        new RefreshLaboratoriesControl(laboratoryTable).handle(null);

        Label helpLabel = new Label("Brisanje je dozvoljeno samo ako u laboratoriji ne radi nijedan istrazivac.");

        HBox buttons = new HBox(10);
        buttons.getChildren().add(deleteButton);

        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        content.getChildren().add(helpLabel);
        content.getChildren().add(laboratoryTable);
        content.getChildren().add(buttons);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);

        Tab tab = new Tab("Laboratorije");
        tab.setClosable(false);
        tab.setContent(scrollPane);
        return tab;
    }

    private Tab makeResearcherLaboratoryTab() {
        LabResearcherTable labResearcherTable = new LabResearcherTable(new ArrayList<>());

        TextField researcherIdField = new TextField();
        TextField laboratoryIdField = new TextField();
        TextField workRoleField = new TextField();

        researcherIdField.setPromptText("npr. 1");
        laboratoryIdField.setPromptText("npr. 1");
        workRoleField.setPromptText("npr. laboratorijski tehnicar");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));
        form.add(new Label("Istraživač ID:"), 0, 0);
        form.add(researcherIdField, 1, 0);
        form.add(new Label("Laboratorija ID:"), 0, 1);
        form.add(laboratoryIdField, 1, 1);
        form.add(new Label("Radna uloga:"), 0, 2);
        form.add(workRoleField, 1, 2);

        Button addButton = new Button("Dodaj istraživača u laboratoriju");
        Button removeButton = new Button("Ukloni istraživača iz laboratorije");

        addButton.setOnAction(new AddLabResearcherControl(researcherIdField, laboratoryIdField,
                workRoleField, labResearcherTable));
        removeButton.setOnAction(new RemoveLabResearcherControl(researcherIdField, laboratoryIdField,
                labResearcherTable));

        labResearcherTable.setOnMouseClicked(new SelectLabResearcherControl(labResearcherTable,
                researcherIdField, laboratoryIdField, workRoleField));

        new RefreshLabResearchersControl(labResearcherTable).handle(null);

        Label helpLabel = new Label("Ovde se uređuje tabela rad_u_laboratoriji. " +
                "Da bi se laboratorija obrisala, prvo uklonite sve istraživače iz te laboratorije.");

        HBox buttons = new HBox(10);
        buttons.getChildren().add(addButton);
        buttons.getChildren().add(removeButton);    

        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        content.getChildren().add(helpLabel);
        content.getChildren().add(labResearcherTable);
        content.getChildren().add(form);
        content.getChildren().add(buttons);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);

        Tab tab = new Tab("Istraživači u laboratorijama");
        tab.setClosable(false);
        tab.setContent(scrollPane);
        return tab;
    }

}
