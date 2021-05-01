package io.openbanking.participants.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ParticipantResponse {

    private String organisationId;
    private String customerFriendlyName;
    private String registrationNumber;
    private String registeredName;
    private List<OrgDomainRoleClaimResponse> orgDomainRoleClaims;
    private List<AuthorisationServerResponse> authorisationServers;
}
