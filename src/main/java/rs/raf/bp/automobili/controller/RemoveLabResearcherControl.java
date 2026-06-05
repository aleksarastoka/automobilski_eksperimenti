package rs.raf.bp.automobili.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import rs.raf.bp.automobili.Config;
import rs.raf.bp.automobili.model.LabResearcherDto;
import rs.raf.bp.automobili.model.LabResearcherRepository;

public class RemoveLabResearcherControl implements EventHandler<ActionEvent> {

    private final TextField researcherIdField;
    private final TextField laboratoryIdField;
    private final TableView<LabResearcherDto> labResearcherTable;

    public RemoveLabResearcherControl(TextField researcherIdField, TextField laboratoryIdField,
                                      TableView<LabResearcherDto> labResearcherTable) {
        this.researcherIdField = researcherIdField;
        this.laboratoryIdField = laboratoryIdField;
        this.labResearcherTable = labResearcherTable;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            int researcherId = Integer.parseInt(researcherIdField.getText().trim());
            int laboratoryId = Integer.parseInt(laboratoryIdField.getText().trim());

            String message = LabResearcherRepository.removeResearcherFromLab(
                    Config.getConnection(), researcherId, laboratoryId);

            labResearcherTable.setItems(FXCollections.observableArrayList(
                    LabResearcherRepository.readAll(Config.getConnection())));

            new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK).showAndWait();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "ID istraživača i ID laboratorije moraju biti brojevi.", ButtonType.OK).showAndWait();
        }
    }
}
