package rs.raf.bp.automobili.model;

public class LabResearcherDto {

    private final int researcherId;
    private final String firstName;
    private final String lastName;
    private final int laboratoryId;
    private final String laboratoryName;
    private final String workRole;

    public LabResearcherDto(int researcherId, String firstName, String lastName,
                            int laboratoryId, String laboratoryName, String workRole) {
        this.researcherId = researcherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.laboratoryId = laboratoryId;
        this.laboratoryName = laboratoryName;
        this.workRole = workRole;
    }

    public int getResearcherId() {
        return researcherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getLaboratoryId() {
        return laboratoryId;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public String getWorkRole() {
        return workRole;
    }
}
