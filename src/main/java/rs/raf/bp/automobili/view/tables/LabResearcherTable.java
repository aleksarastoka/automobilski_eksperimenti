package rs.raf.bp.automobili.view.tables;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rs.raf.bp.automobili.model.LabResearcherDto;

import java.util.List;

public class LabResearcherTable extends TableView<LabResearcherDto> {

    public LabResearcherTable(List<LabResearcherDto> rows) {
        super(FXCollections.observableArrayList(rows));

        TableColumn<LabResearcherDto, Integer> tcResearcherId = new TableColumn<>("Istraživač ID");
        TableColumn<LabResearcherDto, String> tcFirstName = new TableColumn<>("Ime");
        TableColumn<LabResearcherDto, String> tcLastName = new TableColumn<>("Prezime");
        TableColumn<LabResearcherDto, Integer> tcLaboratoryId = new TableColumn<>("Lab ID");
        TableColumn<LabResearcherDto, String> tcLaboratoryName = new TableColumn<>("Laboratorija");
        TableColumn<LabResearcherDto, String> tcWorkRole = new TableColumn<>("Radna uloga");

        tcResearcherId.setCellValueFactory(new PropertyValueFactory<>("researcherId"));
        tcFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcLaboratoryId.setCellValueFactory(new PropertyValueFactory<>("laboratoryId"));
        tcLaboratoryName.setCellValueFactory(new PropertyValueFactory<>("laboratoryName"));
        tcWorkRole.setCellValueFactory(new PropertyValueFactory<>("workRole"));

        super.getColumns().add(tcResearcherId);
        super.getColumns().add(tcFirstName);
        super.getColumns().add(tcLastName);
        super.getColumns().add(tcLaboratoryId);
        super.getColumns().add(tcLaboratoryName);
        super.getColumns().add(tcWorkRole);

        super.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
    }
}
