package rs.raf.bp.automobili.model;

public class DeleteLaboratoryResult {

    private final boolean success;
    private final String message;

    public DeleteLaboratoryResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
