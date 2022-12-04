package ch.xndr.lprogress.services.LicencePayload;

import java.util.List;
import java.util.jar.Attributes;

public class VerificationRequest {
    private String credentialDefinitionId;

    private List<String> attributes;

    private String connectionId;

    public String getCredentialDefinitionId() {
        return credentialDefinitionId;
    }

    public void setCredentialDefinitionId(String credentialDefinitionId) {
        this.credentialDefinitionId = credentialDefinitionId;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }
}
