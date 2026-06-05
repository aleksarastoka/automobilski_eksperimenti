package rs.raf.bp.automobili.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LabResearcherRepository {

    public static List<LabResearcherDto> readAll(Connection connection) {
        String query = "SELECT i.istrazivac_id, i.ime, i.prezime, " +
                "l.laboratorija_id, l.naziv AS naziv_laboratorije, rul.radna_uloga " +
                "FROM rad_u_laboratoriji rul " +
                "JOIN istrazivac i ON rul.istrazivac_id = i.istrazivac_id " +
                "JOIN laboratorija l ON rul.laboratorija_id = l.laboratorija_id " +
                "ORDER BY l.laboratorija_id, i.istrazivac_id";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            List<LabResearcherDto> rows = new ArrayList<>();

            while (rs.next()) {
                LabResearcherDto dto = new LabResearcherDto(
                        rs.getInt("istrazivac_id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getInt("laboratorija_id"),
                        rs.getString("naziv_laboratorije"),
                        rs.getString("radna_uloga")
                );
                rows.add(dto);
            }

            return rows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String addResearcherToLab(Connection connection, int researcherId,
                                            int laboratoryId, String workRole) {
        String query = "INSERT INTO rad_u_laboratoriji(istrazivac_id, laboratorija_id, radna_uloga) " +
                "VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, researcherId);
            preparedStatement.setInt(2, laboratoryId);
            preparedStatement.setString(3, workRole);
            preparedStatement.executeUpdate();
            return "Istraživač je dodat u laboratoriju.";
        } catch (SQLException e) {
            return "Dodavanje nije uspelo. Proverite da li postoje istraživač i laboratorija i da li veza već postoji.";
        }
    }

    public static String removeResearcherFromLab(Connection connection, int researcherId, int laboratoryId) {
        String query = "DELETE FROM rad_u_laboratoriji WHERE istrazivac_id = ? AND laboratorija_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, researcherId);
            preparedStatement.setInt(2, laboratoryId);
            int deletedRows = preparedStatement.executeUpdate();

            if (deletedRows == 0) {
                return "Veza istraživača i laboratorije nije pronađena.";
            }

            return "Istraživač je uklonjen iz laboratorije.";
        } catch (SQLException e) {
            return "Brisanje veze nije uspelo.";
        }
    }

    private LabResearcherRepository() {
    }
}
