package rs.raf.bp.automobili.model;

public class ScheduledSessionDto {

    private final int experimentId;
    private final int executionNumber;
    private final int sessionNumber;
    private final int laboratoryId;
    private final String laboratoryName;
    private final String sessionDate;
    private final String startTime;
    private final String endTime;
    private final String sessionStatus;
    private final String experimentName;
    private final String experimentType;
    private final String executionStatus;

    public ScheduledSessionDto(int experimentId, int executionNumber, int sessionNumber,
                               int laboratoryId, String laboratoryName, String sessionDate,
                               String startTime, String endTime, String sessionStatus,
                               String experimentName, String experimentType, String executionStatus) {
        this.experimentId = experimentId;
        this.executionNumber = executionNumber;
        this.sessionNumber = sessionNumber;
        this.laboratoryId = laboratoryId;
        this.laboratoryName = laboratoryName;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sessionStatus = sessionStatus;
        this.experimentName = experimentName;
        this.experimentType = experimentType;
        this.executionStatus = executionStatus;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public int getExecutionNumber() {
        return executionNumber;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public int getLaboratoryId() {
        return laboratoryId;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public String getExperimentType() {
        return experimentType;
    }

    public String getExecutionStatus() {
        return executionStatus;
    }
}
