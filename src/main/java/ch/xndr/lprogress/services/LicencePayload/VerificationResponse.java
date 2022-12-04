package ch.xndr.lprogress.services.LicencePayload;

public class VerificationResponse {
    private String status;

    private String processId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
}
