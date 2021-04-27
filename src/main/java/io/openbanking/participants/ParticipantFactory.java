package io.openbanking.participants;

import io.openbanking.participants.payload.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParticipantFactory {

    public ParticipantResponse create(Participant participant, String apiFamilyType) {
        ParticipantResponse participantResponse = new ParticipantResponse();

        participantResponse.setOrganisationId(participant.getOrganisationId());
        participantResponse.setRegistrationNumber(participant.getRegistrationNumber());
        participantResponse.setRegisteredName(participant.getRegisteredName());

        AuthorisationServer authorisationServer = participant.getAuthorisationServers().stream()
                .findFirst().orElse(null);

        if (authorisationServer != null) {
            participantResponse.setCustomerFriendlyName(authorisationServer.getCustomerFriendlyName());
            participantResponse.setDeveloperPortalUri(authorisationServer.getDeveloperPortalUri());

            ApiResource apiResource = authorisationServer.getApiResources().stream()
                    .filter(api -> api.getApiFamilyType().equals(apiFamilyType))
                    .findAny()
                    .orElse(null);

            if (apiResource != null) {
                participantResponse.setApiFamilyType(apiResource.getApiFamilyType());
                participantResponse.setApiVersion(apiResource.getApiVersion());

                ApiDiscoveryEndpoint apiDiscoveryEndpoint = apiResource.getApiDiscoveryEndpoints().stream()
                        .findFirst().orElse(null);

                if (apiDiscoveryEndpoint != null) {
                    participantResponse.setApiEndpoint(apiDiscoveryEndpoint.getApiEndpoint());
                }
            }
        }

        OrgDomainRoleClaim orgDomainRoleClaim = participant.getOrgDomainRoleClaims().stream()
                .findFirst().orElse(null);

        if (orgDomainRoleClaim != null) {
            participantResponse.setRole(orgDomainRoleClaim.getRole());
            participantResponse.setStatus(orgDomainRoleClaim.getStatus());
        }

        return participantResponse;
    }

    public List<ParticipantResponse> create(List<Participant> participants, String apiFamilyType) {
        return participants.stream()
                .map(p -> create(p, apiFamilyType))
                .collect(Collectors.toList());
    }
}
