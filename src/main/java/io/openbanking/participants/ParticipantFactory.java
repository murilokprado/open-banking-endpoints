package io.openbanking.participants;

import io.openbanking.participants.payload.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ParticipantFactory {

    public ParticipantResponse create(Participant participant, String apiFamilyType) {
        ParticipantResponse participantResponse = new ParticipantResponse();

        participantResponse.setOrganisationId(participant.getOrganisationId());
        participantResponse.setRegistrationNumber(participant.getRegistrationNumber());
        participantResponse.setRegisteredName(participant.getRegisteredName());

        String customerFriendlyName = Objects.requireNonNull(participant.getAuthorisationServers().stream()
                .findFirst()
                .orElse(null))
                .getCustomerFriendlyName();

        participantResponse.setCustomerFriendlyName(customerFriendlyName);

        List<OrgDomainRoleClaimResponse> orgDomainRoleClaimResponses = participant.getOrgDomainRoleClaims().stream()
                .map(this::createOrgDomainRoleClaimResponse)
                .collect(Collectors.toList());

        participantResponse.setOrgDomainRoleClaims(orgDomainRoleClaimResponses);

        List<AuthorisationServerResponse> authorisationServerResponses = participant.getAuthorisationServers().stream()
                .map(authorisation -> createAuthorisationServerResponse(authorisation, apiFamilyType))
                .collect(Collectors.toList());

        participantResponse.setAuthorisationServers(authorisationServerResponses);

        return participantResponse;
    }

    public List<ParticipantResponse> create(List<Participant> participants, String apiFamilyType) {
        return participants.stream()
                .map(p -> create(p, apiFamilyType))
                .collect(Collectors.toList());
    }

    private ApiDiscoveryEndpointResponse createApiDiscoveryEndpointResponse(ApiDiscoveryEndpoint apiDiscoveryEndpoint) {
        ApiDiscoveryEndpointResponse apiDiscoveryEndpointResponse = new ApiDiscoveryEndpointResponse();

        apiDiscoveryEndpointResponse.setApiEndpoint(apiDiscoveryEndpoint.getApiEndpoint());

        return apiDiscoveryEndpointResponse;
    }

    private ApiResourceResponse createApiResourceResponse(ApiResource apiResource) {
        ApiResourceResponse apiResourceResponse = new ApiResourceResponse();

        apiResourceResponse.setApiFamilyType(apiResource.getApiFamilyType());
        apiResourceResponse.setApiVersion(apiResource.getApiVersion());

        List<ApiDiscoveryEndpointResponse> apiDiscoveryEndpoints = apiResource.getApiDiscoveryEndpoints().stream()
                .map(this::createApiDiscoveryEndpointResponse)
                .collect(Collectors.toList());

        apiResourceResponse.setApiDiscoveryEndpoints(apiDiscoveryEndpoints);

        return apiResourceResponse;
    }

    private AuthorisationServerResponse createAuthorisationServerResponse(AuthorisationServer authorisationServer,
                                                                          String apiFamilyType) {
        AuthorisationServerResponse authorisationServerResponse = new AuthorisationServerResponse();

        if (authorisationServer != null) {
            authorisationServerResponse.setDeveloperPortalUri(authorisationServer.getDeveloperPortalUri());

            List<ApiResource> apiResources = authorisationServer.getApiResources().stream()
                    .filter(api -> apiFamilyType == null || api.getApiFamilyType().equals(apiFamilyType))
                    .collect(Collectors.toList());

            List<ApiResourceResponse> apiResourceResponses = apiResources.stream()
                    .map(this::createApiResourceResponse)
                    .collect(Collectors.toList());

            authorisationServerResponse.setApiResourceResponses(apiResourceResponses);
        }

        return authorisationServerResponse;
    }

    private OrgDomainRoleClaimResponse createOrgDomainRoleClaimResponse(OrgDomainRoleClaim orgDomainRoleClaim) {
        OrgDomainRoleClaimResponse orgDomainRoleClaimResponse = new OrgDomainRoleClaimResponse();

        orgDomainRoleClaimResponse.setRole(orgDomainRoleClaim.getRole());
        orgDomainRoleClaimResponse.setStatus(orgDomainRoleClaim.getStatus());

        return orgDomainRoleClaimResponse;
    }
}
