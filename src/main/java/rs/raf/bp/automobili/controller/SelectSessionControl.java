package rs.raf.bp.automobili.controller;

import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import rs.raf.bp.automobili.model.ScheduledSessionDto;

public class SelectSessionControl implements EventHandler<MouseEvent> {

    private final TableView<ScheduledSessionDto> sessionTable;
    private final TextField experimentIdField;
    private final TextField executionNumberField;
    private final TextField sessionNumberField;
    private final TextField laboratoryIdField;
    private final TextField dateField;
    private final TextField startTimeField;
    private final TextField endTimeField;
    private final ComboBox<String> statusComboBox;

    public SelectSessionControl(TableView<ScheduledSessionDto> sessionTable,
                                TextField experimentIdField,
                                TextField executionNumberField,
                                TextField sessionNumberField,
                                TextField laboratoryIdField,
                                TextField dateField,
                                TextField startTimeField,
                                TextField endTimeField,
                                ComboBox<String> statusComboBox) {
        this.sessionTable = sessionTable;
        this.experimentIdField = experimentIdField;
        this.executionNumberField = executionNumberField;
        this.sessionNumberField = sessionNumberField;
        this.laboratoryIdField = laboratoryIdField;
        this.dateField = dateField;
        this.startTimeField = startTimeField;
        this.endTimeField = endTimeField;
        this.statusComboBox = statusComboBox;
    }

    @Override
    public void handle(MouseEvent event) {
        ScheduledSessionDto session = sessionTable.getSelectionModel().getSelectedItem();
        if (session == null) {
            return;
        }

        experimentIdField.setText(String.valueOf(session.getExperimentId()));
        executionNumberField.setText(String.valueOf(session.getExecutionNumber()));
        sessionNumberField.setText(String.valueOf(session.getSessionNumber()));
        laboratoryIdField.setText(String.valueOf(session.getLaboratoryId()));
        dateField.setText(session.getSessionDate());
        startTimeField.setText(session.getStartTime());
        endTimeField.setText(session.getEndTime());
        statusComboBox.setValue(session.getSessionStatus());
    }
}
