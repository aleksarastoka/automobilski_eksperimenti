package rs.raf.bp.automobili.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import rs.raf.bp.automobili.Config;
import rs.raf.bp.automobili.model.LabResearcherDto;
import rs.raf.bp.automobili.model.LabResearcherRepository;

public class RefreshLabResearchersControl implements EventHandler<ActionEvent> {

    private final TableView<LabResearcherDto> labResearcherTable;

    public RefreshLabResearchersControl(TableView<LabResearcherDto> labResearcherTable) {
        this.labResearcherTable = labResearcherTable;
    }

    @Override
    public void handle(ActionEvent event) {
        labResearcherTable.setItems(FXCollections.observableArrayList(
                LabResearcherRepository.readAll(Config.getConnection())));
    }
}
