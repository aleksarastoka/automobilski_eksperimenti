package rs.raf.bp.automobili.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import rs.raf.bp.automobili.Config;
import rs.raf.bp.automobili.model.ScheduledSessionDto;
import rs.raf.bp.automobili.model.SessionRepository;

public class RefreshSessionsControl implements EventHandler<ActionEvent> {

    private final TableView<ScheduledSessionDto> sessionTable;

    public RefreshSessionsControl(TableView<ScheduledSessionDto> sessionTable) {
        this.sessionTable = sessionTable;
    }

    @Override
    public void handle(ActionEvent event) {
        sessionTable.setItems(FXCollections.observableArrayList(
                SessionRepository.readScheduledSessions(Config.getConnection())));
    }
}
