package io.openbanking.participants.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantResponse {

    private String organisationId;
    private String customerFriendlyName;
    private String registrationNumber;
    private String registeredName;
    private String role;
    private String status;
    private String apiFamilyType;
    private Integer apiVersion;
    private String apiEndpoint;
    private String developerPortalUri;
}
