package rs.raf.bp.automobili.view.tables;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rs.raf.bp.automobili.model.ScheduledSessionDto;

import java.util.List;

public class ScheduledSessionTable extends TableView<ScheduledSessionDto> {

    public ScheduledSessionTable(List<ScheduledSessionDto> sessions) {
        super(FXCollections.observableArrayList(sessions));

        TableColumn<ScheduledSessionDto, Integer> tcExperimentId = new TableColumn<>("Eksperiment ID");
        TableColumn<ScheduledSessionDto, Integer> tcExecutionNumber = new TableColumn<>("Broj izvodjenja");
        TableColumn<ScheduledSessionDto, Integer> tcSessionNumber = new TableColumn<>("Broj sesije");
        TableColumn<ScheduledSessionDto, String> tcExperimentName = new TableColumn<>("Eksperiment");
        TableColumn<ScheduledSessionDto, String> tcExperimentType = new TableColumn<>("Tip eksperimenta");
        TableColumn<ScheduledSessionDto, Integer> tcLaboratoryId = new TableColumn<>("Lab ID");
        TableColumn<ScheduledSessionDto, String> tcLaboratoryName = new TableColumn<>("Laboratorija");
        TableColumn<ScheduledSessionDto, String> tcSessionDate = new TableColumn<>("Datum");
        TableColumn<ScheduledSessionDto, String> tcStartTime = new TableColumn<>("Pocetak");
        TableColumn<ScheduledSessionDto, String> tcEndTime = new TableColumn<>("Kraj");
        TableColumn<ScheduledSessionDto, String> tcSessionStatus = new TableColumn<>("Status sesije");
        TableColumn<ScheduledSessionDto, String> tcExecutionStatus = new TableColumn<>("Status izvodjenja");

        tcExperimentId.setCellValueFactory(new PropertyValueFactory<>("experimentId"));
        tcExecutionNumber.setCellValueFactory(new PropertyValueFactory<>("executionNumber"));
        tcSessionNumber.setCellValueFactory(new PropertyValueFactory<>("sessionNumber"));
        tcExperimentName.setCellValueFactory(new PropertyValueFactory<>("experimentName"));
        tcExperimentType.setCellValueFactory(new PropertyValueFactory<>("experimentType"));
        tcLaboratoryId.setCellValueFactory(new PropertyValueFactory<>("laboratoryId"));
        tcLaboratoryName.setCellValueFactory(new PropertyValueFactory<>("laboratoryName"));
        tcSessionDate.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
        tcStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        tcEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        tcSessionStatus.setCellValueFactory(new PropertyValueFactory<>("sessionStatus"));
        tcExecutionStatus.setCellValueFactory(new PropertyValueFactory<>("executionStatus"));

        super.getColumns().add(tcExperimentId);
        super.getColumns().add(tcExecutionNumber);
        super.getColumns().add(tcSessionNumber);
        super.getColumns().add(tcExperimentName);
        super.getColumns().add(tcExperimentType);
        super.getColumns().add(tcLaboratoryId);
        super.getColumns().add(tcLaboratoryName);
        super.getColumns().add(tcSessionDate);
        super.getColumns().add(tcStartTime);
        super.getColumns().add(tcEndTime);
        super.getColumns().add(tcSessionStatus);
        super.getColumns().add(tcExecutionStatus);

        super.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
    }
}
