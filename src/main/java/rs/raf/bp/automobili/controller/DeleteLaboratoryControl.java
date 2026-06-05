package rs.raf.bp.automobili.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import rs.raf.bp.automobili.Config;
import rs.raf.bp.automobili.model.DeleteLaboratoryResult;
import rs.raf.bp.automobili.model.LaboratoryDto;
import rs.raf.bp.automobili.model.LaboratoryRepository;

public class DeleteLaboratoryControl implements EventHandler<ActionEvent> {

    private final TableView<LaboratoryDto> laboratoryTable;

    public DeleteLaboratoryControl(TableView<LaboratoryDto> laboratoryTable) {
        this.laboratoryTable = laboratoryTable;
    }

    @Override
    public void handle(ActionEvent event) {
        LaboratoryDto laboratory = laboratoryTable.getSelectionModel().getSelectedItem();
        if (laboratory == null) {
            new Alert(Alert.AlertType.ERROR, "Prvo izaberite laboratoriju iz tabele.", ButtonType.OK).showAndWait();
            return;
        }

        DeleteLaboratoryResult result = LaboratoryRepository.deleteIfNoResearchers(
                Config.getConnection(), laboratory.getLaboratoryId());

        laboratoryTable.setItems(FXCollections.observableArrayList(
                LaboratoryRepository.readAll(Config.getConnection())));

        Alert.AlertType alertType = result.isSuccess() ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR;
        new Alert(alertType, result.getMessage(), ButtonType.OK).showAndWait();
    }
}
