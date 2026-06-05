package rs.raf.bp.automobili.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import rs.raf.bp.automobili.Config;
import rs.raf.bp.automobili.model.ScheduledSessionDto;
import rs.raf.bp.automobili.model.SessionRepository;

import java.sql.Date;
import java.sql.Time;

public class UpdateSessionControl implements EventHandler<ActionEvent> {

    private final TableView<ScheduledSessionDto> sessionTable;
    private final TextField experimentIdField;
    private final TextField executionNumberField;
    private final TextField sessionNumberField;
    private final TextField laboratoryIdField;
    private final TextField dateField;
    private final TextField startTimeField;
    private final TextField endTimeField;
    private final ComboBox<String> statusComboBox;

    public UpdateSessionControl(TableView<ScheduledSessionDto> sessionTable,
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
    public void handle(ActionEvent event) {
        if (experimentIdField.getText().isBlank()
                || executionNumberField.getText().isBlank()
                || sessionNumberField.getText().isBlank()) {
            new Alert(Alert.AlertType.ERROR, "Prvo izaberite sesiju iz tabele.", ButtonType.OK).showAndWait();
            return;
        }

        try {
            int experimentId = Integer.parseInt(experimentIdField.getText().trim());
            int executionNumber = Integer.parseInt(executionNumberField.getText().trim());
            int sessionNumber = Integer.parseInt(sessionNumberField.getText().trim());
            int laboratoryId = Integer.parseInt(laboratoryIdField.getText().trim());
            String date = dateField.getText().trim();
            String startTime = startTimeField.getText().trim();
            String endTime = endTimeField.getText().trim();
            String status = statusComboBox.getValue();

            if (date.isBlank() || startTime.isBlank() || endTime.isBlank() || status == null) {
                new Alert(Alert.AlertType.ERROR, "Sva polja za izmenu moraju biti popunjena.", ButtonType.OK).showAndWait();
                return;
            }

            String formattedDate = Date.valueOf(date).toString();
            String formattedStartTime = Time.valueOf(fixTime(startTime)).toString();
            String formattedEndTime = Time.valueOf(fixTime(endTime)).toString();

            ScheduledSessionDto selectedSession = sessionTable.getSelectionModel().getSelectedItem();
            if (selectedSession != null
                    && selectedSession.getExperimentId() == experimentId
                    && selectedSession.getExecutionNumber() == executionNumber
                    && selectedSession.getSessionNumber() == sessionNumber
                    && selectedSession.getLaboratoryId() == laboratoryId
                    && selectedSession.getSessionDate().equals(formattedDate)
                    && selectedSession.getStartTime().equals(formattedStartTime)
                    && selectedSession.getEndTime().equals(formattedEndTime)
                    && selectedSession.getSessionStatus().equals(status)) {
                return;
            }

            SessionRepository.updateSession(Config.getConnection(), experimentId, executionNumber,
                    sessionNumber, laboratoryId, formattedDate, formattedStartTime, formattedEndTime, status);

            sessionTable.setItems(FXCollections.observableArrayList(
                    SessionRepository.readScheduledSessions(Config.getConnection())));

            new Alert(Alert.AlertType.INFORMATION, "Podaci o sesiji su promenjeni.", ButtonType.OK).showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,
                    "Greska pri promeni sesije. Proverite format datuma, vremena i ID laboratorije.",
                    ButtonType.OK).showAndWait();
        }
    }

    private String fixTime(String time) {
        if (time == null) {
            return "00:00:00";
        }
        String trimmed = time.trim();
        if (trimmed.length() == 5) {
            return trimmed + ":00";
        }
        return trimmed;
    }
}
