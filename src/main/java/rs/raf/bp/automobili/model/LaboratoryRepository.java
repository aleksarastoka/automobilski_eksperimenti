package rs.raf.bp.automobili.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LaboratoryRepository {

    public static List<LaboratoryDto> readAll(Connection connection) {
        String query = "SELECT l.laboratorija_id, l.naziv, tl.naziv AS tip_laboratorije, " +
                "l.opis_lokacije, l.status_kalibracije, COUNT(rul.istrazivac_id) AS broj_istrazivaca " +
                "FROM laboratorija l " +
                "JOIN tip_laboratorije tl ON l.tip_laboratorije_id = tl.tip_laboratorije_id " +
                "LEFT JOIN rad_u_laboratoriji rul ON l.laboratorija_id = rul.laboratorija_id " +
                "GROUP BY l.laboratorija_id, l.naziv, tl.naziv, l.opis_lokacije, l.status_kalibracije " +
                "ORDER BY l.laboratorija_id";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            List<LaboratoryDto> laboratories = new ArrayList<>();

            while (rs.next()) {
                LaboratoryDto laboratory = new LaboratoryDto(
                        rs.getInt("laboratorija_id"),
                        rs.getString("naziv"),
                        rs.getString("tip_laboratorije"),
                        rs.getString("opis_lokacije"),
                        rs.getString("status_kalibracije"),
                        rs.getInt("broj_istrazivaca")
                );
                laboratories.add(laboratory);
            }

            return laboratories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DeleteLaboratoryResult deleteIfNoResearchers(Connection connection, int laboratoryId) {
        String countResearchersQuery = "SELECT COUNT(*) AS broj FROM rad_u_laboratoriji WHERE laboratorija_id = ?";
        String deleteSessionsQuery = "DELETE FROM sesija_eksperimenta WHERE laboratorija_id = ?";
        String deleteToolsQuery = "DELETE FROM alat WHERE laboratorija_id = ?";
        String deleteLaboratoryQuery = "DELETE FROM laboratorija WHERE laboratorija_id = ?";

        try {
            PreparedStatement countStatement = connection.prepareStatement(countResearchersQuery);
            countStatement.setInt(1, laboratoryId);
            ResultSet rs = countStatement.executeQuery();

            int researcherCount = 0;
            if (rs.next()) {
                researcherCount = rs.getInt("broj");
            }

            if (researcherCount > 0) {
                return new DeleteLaboratoryResult(false,
                        "Laboratorija ne sme da se obrise jer u njoj radi istrazivac.");
            }

            connection.setAutoCommit(false);

            PreparedStatement deleteSessionsStatement = connection.prepareStatement(deleteSessionsQuery);
            deleteSessionsStatement.setInt(1, laboratoryId);
            deleteSessionsStatement.executeUpdate();

            PreparedStatement deleteToolsStatement = connection.prepareStatement(deleteToolsQuery);
            deleteToolsStatement.setInt(1, laboratoryId);
            deleteToolsStatement.executeUpdate();

            PreparedStatement deleteLaboratoryStatement = connection.prepareStatement(deleteLaboratoryQuery);
            deleteLaboratoryStatement.setInt(1, laboratoryId);
            int deletedRows = deleteLaboratoryStatement.executeUpdate();

            if (deletedRows == 0) {
                connection.rollback();
                connection.setAutoCommit(true);
                return new DeleteLaboratoryResult(false, "Laboratorija nije pronadjena.");
            }

            connection.commit();
            connection.setAutoCommit(true);
            return new DeleteLaboratoryResult(true, "Laboratorija je obrisana.");
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return new DeleteLaboratoryResult(false, "Brisanje laboratorije nije uspelo zbog povezanih podataka.");
        }
    }

    private LaboratoryRepository() {
    }
}
