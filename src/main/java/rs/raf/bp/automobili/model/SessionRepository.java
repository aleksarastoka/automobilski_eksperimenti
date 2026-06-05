package rs.raf.bp.automobili.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class SessionRepository {

    public static List<ScheduledSessionDto> readScheduledSessions(Connection connection) {
        String query = "SELECT se.eksperiment_id, se.broj_izvodjenja, se.broj_sesije, " +
                "se.laboratorija_id, l.naziv AS naziv_laboratorije, se.datum_sesije, " +
                "se.vreme_pocetka, se.vreme_zavrsetka, se.status_sesije, " +
                "e.naziv AS naziv_eksperimenta, te.naziv AS tip_eksperimenta, " +
                "ie.status_izvodjenja " +
                "FROM sesija_eksperimenta se " +
                "JOIN izvodjenje_eksperimenta ie ON se.eksperiment_id = ie.eksperiment_id " +
                "AND se.broj_izvodjenja = ie.broj_izvodjenja " +
                "JOIN eksperiment e ON se.eksperiment_id = e.eksperiment_id " +
                "JOIN tip_eksperimenta te ON e.tip_eksperimenta_id = te.tip_eksperimenta_id " +
                "JOIN laboratorija l ON se.laboratorija_id = l.laboratorija_id " +
                "WHERE se.status_sesije = 'zakazana' " +
                "ORDER BY se.datum_sesije, se.vreme_pocetka";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            List<ScheduledSessionDto> sessions = new ArrayList<>();

            while (rs.next()) {
                ScheduledSessionDto session = new ScheduledSessionDto(
                        rs.getInt("eksperiment_id"),
                        rs.getInt("broj_izvodjenja"),
                        rs.getInt("broj_sesije"),
                        rs.getInt("laboratorija_id"),
                        rs.getString("naziv_laboratorije"),
                        rs.getString("datum_sesije"),
                        rs.getString("vreme_pocetka"),
                        rs.getString("vreme_zavrsetka"),
                        rs.getString("status_sesije"),
                        rs.getString("naziv_eksperimenta"),
                        rs.getString("tip_eksperimenta"),
                        rs.getString("status_izvodjenja")
                );
                sessions.add(session);
            }

            return sessions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateSession(Connection connection, int experimentId, int executionNumber,
                                     int sessionNumber, int laboratoryId, String sessionDate,
                                     String startTime, String endTime, String status) {
        String query = "UPDATE sesija_eksperimenta " +
                "SET laboratorija_id = ?, datum_sesije = ?, vreme_pocetka = ?, " +
                "vreme_zavrsetka = ?, status_sesije = ? " +
                "WHERE eksperiment_id = ? AND broj_izvodjenja = ? AND broj_sesije = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, laboratoryId);
            preparedStatement.setDate(2, Date.valueOf(sessionDate));
            preparedStatement.setTime(3, Time.valueOf(fixTime(startTime)));
            preparedStatement.setTime(4, Time.valueOf(fixTime(endTime)));
            preparedStatement.setString(5, status);
            preparedStatement.setInt(6, experimentId);
            preparedStatement.setInt(7, executionNumber);
            preparedStatement.setInt(8, sessionNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String fixTime(String time) {
        if (time == null) {
            return "00:00:00";
        }
        String trimmed = time.trim();
        if (trimmed.length() == 5) {
            return trimmed + ":00";
        }
        return trimmed;
    }

    private SessionRepository() {
    }
}
