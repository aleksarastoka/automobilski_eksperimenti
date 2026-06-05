package rs.raf.bp.automobili.model;

public class LaboratoryDto {

    private final int laboratoryId;
    private final String name;
    private final String laboratoryType;
    private final String locationDescription;
    private final String calibrationStatus;
    private final int researcherCount;

    public LaboratoryDto(int laboratoryId, String name, String laboratoryType,
                         String locationDescription, String calibrationStatus, int researcherCount) {
        this.laboratoryId = laboratoryId;
        this.name = name;
        this.laboratoryType = laboratoryType;
        this.locationDescription = locationDescription;
        this.calibrationStatus = calibrationStatus;
        this.researcherCount = researcherCount;
    }

    public int getLaboratoryId() {
        return laboratoryId;
    }

    public String getName() {
        return name;
    }

    public String getLaboratoryType() {
        return laboratoryType;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public String getCalibrationStatus() {
        return calibrationStatus;
    }

    public int getResearcherCount() {
        return researcherCount;
    }
}
