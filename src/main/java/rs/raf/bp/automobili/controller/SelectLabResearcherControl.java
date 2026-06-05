package rs.raf.bp.automobili.controller;

import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import rs.raf.bp.automobili.model.LabResearcherDto;

public class SelectLabResearcherControl implements EventHandler<MouseEvent> {

    private final TableView<LabResearcherDto> labResearcherTable;
    private final TextField researcherIdField;
    private final TextField laboratoryIdField;
    private final TextField workRoleField;

    public SelectLabResearcherControl(TableView<LabResearcherDto> labResearcherTable,
                                      TextField researcherIdField,
                                      TextField laboratoryIdField,
                                      TextField workRoleField) {
        this.labResearcherTable = labResearcherTable;
        this.researcherIdField = researcherIdField;
        this.laboratoryIdField = laboratoryIdField;
        this.workRoleField = workRoleField;
    }

    @Override
    public void handle(MouseEvent event) {
        LabResearcherDto row = labResearcherTable.getSelectionModel().getSelectedItem();
        if (row == null) {
            return;
        }

        researcherIdField.setText(String.valueOf(row.getResearcherId()));
        laboratoryIdField.setText(String.valueOf(row.getLaboratoryId()));
        workRoleField.setText(row.getWorkRole());
    }
}
