package rs.raf.bp.automobili.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import rs.raf.bp.automobili.Config;
import rs.raf.bp.automobili.model.LaboratoryDto;
import rs.raf.bp.automobili.model.LaboratoryRepository;

public class RefreshLaboratoriesControl implements EventHandler<ActionEvent> {

    private final TableView<LaboratoryDto> laboratoryTable;

    public RefreshLaboratoriesControl(TableView<LaboratoryDto> laboratoryTable) {
        this.laboratoryTable = laboratoryTable;
    }

    @Override
    public void handle(ActionEvent event) {
        laboratoryTable.setItems(FXCollections.observableArrayList(
                LaboratoryRepository.readAll(Config.getConnection())));
    }
}
