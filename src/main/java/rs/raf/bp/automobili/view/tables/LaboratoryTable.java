package rs.raf.bp.automobili.view.tables;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rs.raf.bp.automobili.model.LaboratoryDto;

import java.util.List;

public class LaboratoryTable extends TableView<LaboratoryDto> {

    public LaboratoryTable(List<LaboratoryDto> laboratories) {
        super(FXCollections.observableArrayList(laboratories));

        TableColumn<LaboratoryDto, Integer> tcLaboratoryId = new TableColumn<>("Lab ID");
        TableColumn<LaboratoryDto, String> tcName = new TableColumn<>("Naziv");
        TableColumn<LaboratoryDto, String> tcLaboratoryType = new TableColumn<>("Tip");
        TableColumn<LaboratoryDto, String> tcLocation = new TableColumn<>("Lokacija");
        TableColumn<LaboratoryDto, String> tcCalibrationStatus = new TableColumn<>("Kalibracija");
        TableColumn<LaboratoryDto, Integer> tcResearcherCount = new TableColumn<>("Broj istrazivaca");

        tcLaboratoryId.setCellValueFactory(new PropertyValueFactory<>("laboratoryId"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcLaboratoryType.setCellValueFactory(new PropertyValueFactory<>("laboratoryType"));
        tcLocation.setCellValueFactory(new PropertyValueFactory<>("locationDescription"));
        tcCalibrationStatus.setCellValueFactory(new PropertyValueFactory<>("calibrationStatus"));
        tcResearcherCount.setCellValueFactory(new PropertyValueFactory<>("researcherCount"));

        super.getColumns().add(tcLaboratoryId);
        super.getColumns().add(tcName);
        super.getColumns().add(tcLaboratoryType);
        super.getColumns().add(tcLocation);
        super.getColumns().add(tcCalibrationStatus);
        super.getColumns().add(tcResearcherCount);

        super.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
    }
}
