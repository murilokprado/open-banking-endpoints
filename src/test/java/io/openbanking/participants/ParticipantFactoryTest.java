package io.openbanking.participants;

import io.openbanking.participants.payload.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class ParticipantFactoryTest {

    private final static String ORGANISATION_ID = "fefac57d-1d50-5615-89b2-0b2d80623a28";
    private final static String REGISTRATION_NUMBER = "07853842";
    private final static String REGISTERED_NAME = "COOPERATIVA DE CRÉDITO RURAL DE OURO   SULCREDI/OURO";
    private final static String CUSTOMER_FRIENDLY_NAME = "Open Banking Cashway";
    private final static String ROLE = "DADOS";
    private final static String STATUS = "Active";
    private final static String DEVELOPER_PORTAL_URI = "https://api.sulcrediouro.com.br/open-banking/naoseaplica";
    private final static String API_FAMILY_TYPE = "channels";
    private final static Integer API_VERSION = 1;
    private final static String API_ENDPOINT_ONE = "https://api.sulcrediouro.com.br/open-banking/channels/v1/branches";
    private final static String API_ENDPOINT_BRANCHES = "branches";
    private final static String API_ENDPOINT_TWO = "https://api.sulcrediouro.com.br/open-banking/channels/v1/electronic-channels";
    private final static String API_ENDPOINT_ELECTRONIC = "electronic-channels";

    @InjectMocks
    private ParticipantFactory participantFactory;

    @Mock
    private Participant participant;

    @Mock
    private AuthorisationServer authorisationServer;

    @Mock
    private OrgDomainRoleClaim orgDomainRoleClaim;

    @Mock
    private ApiResource apiResource;

    @Mock
    private ApiDiscoveryEndpoint apiDiscoveryEndpointOne;

    @Mock
    private ApiDiscoveryEndpoint apiDiscoveryEndpointTwo;

    @Test
    public void shouldCreate() {
        when(participant.getOrganisationId()).thenReturn(ORGANISATION_ID);
        when(participant.getRegistrationNumber()).thenReturn(REGISTRATION_NUMBER);
        when(participant.getRegisteredName()).thenReturn(REGISTERED_NAME);
        when(participant.getAuthorisationServers()).thenReturn(List.of(authorisationServer));
        when(authorisationServer.getCustomerFriendlyName()).thenReturn(CUSTOMER_FRIENDLY_NAME);
        when(participant.getOrgDomainRoleClaims()).thenReturn(List.of(orgDomainRoleClaim));
        when(orgDomainRoleClaim.getRole()).thenReturn(ROLE);
        when(orgDomainRoleClaim.getStatus()).thenReturn(STATUS);
        when(authorisationServer.getDeveloperPortalUri()).thenReturn(DEVELOPER_PORTAL_URI);
        when(authorisationServer.getApiResources()).thenReturn(List.of(apiResource));
        when(apiResource.getApiFamilyType()).thenReturn(API_FAMILY_TYPE);
        when(apiResource.getApiVersion()).thenReturn(API_VERSION);
        when(apiResource.getApiDiscoveryEndpoints()).thenReturn(List.of(apiDiscoveryEndpointOne));
        when(apiDiscoveryEndpointOne.getApiEndpoint()).thenReturn(API_ENDPOINT_ONE);

        ParticipantResponse participantResponse = participantFactory.create(participant, null);

        assertThat(participantResponse.getOrganisationId()).isEqualTo(ORGANISATION_ID);
        assertThat(participantResponse.getRegistrationNumber()).isEqualTo(REGISTRATION_NUMBER);
        assertThat(participantResponse.getRegisteredName()).isEqualTo(REGISTERED_NAME);
        assertThat(participantResponse.getCustomerFriendlyName()).isEqualTo(CUSTOMER_FRIENDLY_NAME);

        OrgDomainRoleClaimResponse orgDomainRoleClaimResponse = participantResponse.getOrgDomainRoleClaims().get(0);

        assertThat(orgDomainRoleClaimResponse.getRole()).isEqualTo(ROLE);
        assertThat(orgDomainRoleClaimResponse.getStatus()).isEqualTo(STATUS);

        AuthorisationServerResponse authorisationServerResponse = participantResponse.getAuthorisationServers().get(0);

        assertThat(authorisationServerResponse.getDeveloperPortalUri()).isEqualTo(DEVELOPER_PORTAL_URI);

        ApiResourceResponse apiResourceResponse = authorisationServerResponse.getApiResourceResponses().get(0);

        assertThat(apiResourceResponse.getApiFamilyType()).isEqualTo(API_FAMILY_TYPE);
        assertThat(apiResourceResponse.getApiVersion()).isEqualTo(API_VERSION);

        ApiDiscoveryEndpointResponse apiDiscoveryEndpointResponse = apiResourceResponse.getApiDiscoveryEndpoints().get(0);

        assertThat(apiDiscoveryEndpointResponse.getApiEndpoint()).isEqualTo(API_ENDPOINT_ONE);
    }

    @Test
    public void createAvailableEndpoint() {
        when(participant.getOrganisationId()).thenReturn(ORGANISATION_ID);
        when(participant.getAuthorisationServers()).thenReturn(List.of(authorisationServer));
        when(authorisationServer.getCustomerFriendlyName()).thenReturn(CUSTOMER_FRIENDLY_NAME);
        when(authorisationServer.getApiResources()).thenReturn(List.of(apiResource));
        when(apiResource.getApiFamilyType()).thenReturn(API_FAMILY_TYPE);
        when(apiResource.getApiDiscoveryEndpoints()).thenReturn(List.of(apiDiscoveryEndpointOne, apiDiscoveryEndpointTwo));
        when(apiDiscoveryEndpointOne.getApiEndpoint()).thenReturn(API_ENDPOINT_ONE);
        when(apiDiscoveryEndpointTwo.getApiEndpoint()).thenReturn(API_ENDPOINT_TWO);

        AvailableApiEndpointResponse availableApiEndpointResponse = participantFactory
                .createAvailableEndpoint(participant, null);

        assertThat(availableApiEndpointResponse.getOrganisationId()).isEqualTo(ORGANISATION_ID);
        assertThat(availableApiEndpointResponse.getCustomerFriendlyName()).isEqualTo(CUSTOMER_FRIENDLY_NAME);
        assertThat(availableApiEndpointResponse.getApiFamilyType()).isEqualTo(API_FAMILY_TYPE);
        assertThat(availableApiEndpointResponse.getAvailableEndpoints())
                .isNotEmpty()
                .hasSize(2)
                .containsExactly(API_ENDPOINT_BRANCHES, API_ENDPOINT_ELECTRONIC);
    }
}
