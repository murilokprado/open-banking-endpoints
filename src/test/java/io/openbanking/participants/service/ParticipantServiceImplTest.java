package io.openbanking.participants.service;

import feign.FeignException;
import io.openbanking.participants.ParticipantClient;
import io.openbanking.participants.ParticipantFactory;
import io.openbanking.participants.payload.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParticipantServiceImplTest {

    @InjectMocks
    private ParticipantServiceImpl participantService;

    @Mock
    private ParticipantClient participantClient;

    @Mock
    private ParticipantFactory participantFactory;

    @Test
    public void shouldGetApiFamilyTypes() {
        when(participantService.getParticipants()).thenReturn(createParticipant());
        
        List<String> apiFamilyTypesResponse = participantService.getApiFamilyTypes();

        assertThat(apiFamilyTypesResponse)
                .isEqualTo(List.of("channels", "discovery", "admin", "products-services", "other"));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowsGetParticipants() {
        doThrow(FeignException.class).when(participantService.getParticipants());
    }

    @Test
    public void shouldGetParticipants() {
        when(participantClient.getParticipants()).thenReturn(createParticipant());

        List<Participant> participantsResponse = participantService.getParticipants();

        assertThat(participantsResponse.size()).isOne();

        Participant participant = participantsResponse.get(0);

        assertThat(participant.getOrganisationId()).isEqualTo("fefac57d-1d50-5615-89b2-0b2d80623a28");
        assertThat(participant.getStatus()).isEqualTo("Active");
        assertThat(participant.getOrganisationName()).isEqualTo("CCR DE OURO");
        assertThat(participant.getCreatedOn()).isEqualTo("2021-01-11T17:11:30.743Z");
        assertThat(participant.getLegalEntityName()).isEqualTo("COOPERATIVA DE CRÉDITO RURAL DE OURO   SULCREDI/OURO");
        assertThat(participant.getCountryOfRegistration()).isEqualTo("BR");
        assertThat(participant.getCompanyRegister()).isEqualTo("Cadastro Nacional da Pessoa Jurídica");
        assertThat(participant.getRegistrationNumber()).isEqualTo("07853842");
        assertThat(participant.getRegistrationId()).isEqualTo("07853842");
        assertThat(participant.getRegisteredName()).isEqualTo("COOPERATIVA DE CRÉDITO RURAL DE OURO   SULCREDI/OURO");
        assertThat(participant.getAddressLine1()).isEqualTo("Rua Felipe Schmidt 1882");
        assertThat(participant.getAddressLine2()).isEqualTo("Centro");
        assertThat(participant.getCity()).isEqualTo("Ouro, SC");
        assertThat(participant.getPostcode()).isEqualTo("89663-000");
        assertThat(participant.getCountry()).isEqualTo("BR");
        assertThat(participant.getParentOrganisationReference()).isEmpty();
        assertThat(participant.getContacts()).isNull();
        assertThat(participant.getAuthorisationServers().size()).isOne();

        AuthorisationServer authorisationServer = participant.getAuthorisationServers().get(0);

        assertThat(authorisationServer.getAuthorisationServerId()).isEqualTo("780f2093-e531-4ac0-b7b3-016d55812b4d");
        assertThat(authorisationServer.getOrganisationId()).isNull();
        assertThat(authorisationServer.getAutoRegistrationSupported()).isTrue();
        assertThat(authorisationServer.getCustomerFriendlyDescription()).isEqualTo("Cashway");
        assertThat(authorisationServer.getCustomerFriendlyLogoUri()).isEqualTo("https://api.sulcrediouro.com.br/open-banking/naoseaplica.png");
        assertThat(authorisationServer.getCustomerFriendlyName()).isEqualTo("Open Banking Cashway");
        assertThat(authorisationServer.getDeveloperPortalUri()).isEqualTo("https://api.sulcrediouro.com.br/open-banking/naoseaplica");
        assertThat(authorisationServer.getTermsOfServiceUri()).isEqualTo("https://api.sulcrediouro.com.br/open-banking/naoseaplica");
        assertThat(authorisationServer.getNotificationWebhook()).isNull();
        assertThat(authorisationServer.getNotificationWebhookStatus()).isNull();
        assertThat(authorisationServer.getOpenIDDiscoveryDocument()).isEqualTo("https://api.sulcrediouro.com.br/open-banking/naoseaplica");
        assertThat(authorisationServer.getPayloadSigningCertLocationUri()).isEqualTo("https://api.sulcrediouro.com.br/open-banking/naoseaplica");
        assertThat(authorisationServer.getParentAuthorisationServerId()).isNull();
        assertThat(authorisationServer.getApiResources().size()).isEqualTo(5);
        assertThat(participant.getOrgDomainClaims().size()).isOne();
        assertThat(participant.getOrgDomainRoleClaims().size()).isEqualTo(3);

        ApiResource apiResourceZero = authorisationServer.getApiResources().get(0);

        assertThat(apiResourceZero.getApiResourceId()).isEqualTo("1c506bea-ed19-4289-81b7-f00ab48d8a1d");
        assertThat(apiResourceZero.getApiFamilyType()).isEqualTo("channels");
        assertThat(apiResourceZero.getApiVersion()).isOne();
        assertThat(apiResourceZero.getApiDiscoveryEndpoints().size()).isEqualTo(4);

        ApiResource apiResourceOne = authorisationServer.getApiResources().get(1);

        assertThat(apiResourceOne.getApiResourceId()).isEqualTo("336bdf44-cf95-4112-b455-7f2d9ee5404d");
        assertThat(apiResourceOne.getApiFamilyType()).isEqualTo("discovery");
        assertThat(apiResourceOne.getApiVersion()).isOne();
        assertThat(apiResourceOne.getApiDiscoveryEndpoints().size()).isEqualTo(2);

        ApiResource apiResourceTwo = authorisationServer.getApiResources().get(2);

        assertThat(apiResourceTwo.getApiResourceId()).isEqualTo("192a141a-93ff-4f2b-ab52-6d00a9c312fe");
        assertThat(apiResourceTwo.getApiFamilyType()).isEqualTo("admin");
        assertThat(apiResourceTwo.getApiVersion()).isOne();
        assertThat(apiResourceTwo.getApiDiscoveryEndpoints().size()).isEqualTo(1);

        ApiResource apiResourceThree = authorisationServer.getApiResources().get(3);

        assertThat(apiResourceThree.getApiResourceId()).isEqualTo("d27d866a-d33e-4841-afd5-029eee87d1d2");
        assertThat(apiResourceThree.getApiFamilyType()).isEqualTo("products-services");
        assertThat(apiResourceThree.getApiVersion()).isOne();
        assertThat(apiResourceThree.getApiDiscoveryEndpoints().size()).isEqualTo(3);

        ApiResource apiResourceFour = authorisationServer.getApiResources().get(4);

        assertThat(apiResourceFour.getApiResourceId()).isEqualTo("3f36ab86-7a8e-4d2e-9f7b-077f233d4bd8");
        assertThat(apiResourceFour.getApiFamilyType()).isEqualTo("other");
        assertThat(apiResourceFour.getApiVersion()).isOne();
        assertThat(apiResourceFour.getApiDiscoveryEndpoints().size()).isZero();

        OrgDomainClaim orgDomainClaim = participant.getOrgDomainClaims().get(0);

        assertThat(orgDomainClaim.getOrganisationAuthorityDomainClaimId()).isNull();
        assertThat(orgDomainClaim.getAuthorisationDomainName()).isEqualTo("Open Banking");
        assertThat(orgDomainClaim.getAuthorityId()).isNull();
        assertThat(orgDomainClaim.getAuthorityName()).isEqualTo("Banco Central do Brasil");
        assertThat(orgDomainClaim.getRegistrationId()).isEmpty();
        assertThat(orgDomainClaim.getStatus()).isEqualTo("Active");

        OrgDomainRoleClaim orgDomainRoleClaimZero = participant.getOrgDomainRoleClaims().get(0);

        assertThat(orgDomainRoleClaimZero.getOrganisationId()).isNull();
        assertThat(orgDomainRoleClaimZero.getOrganisationAuthorityClaimId()).isNull();
        assertThat(orgDomainRoleClaimZero.getAuthorityId()).isNull();
        assertThat(orgDomainRoleClaimZero.getStatus()).isEqualTo("Active");
        assertThat(orgDomainRoleClaimZero.getAuthorisationDomain()).isEqualTo("Open Banking");
        assertThat(orgDomainRoleClaimZero.getRole()).isEqualTo("PAGTO");
        assertThat(orgDomainRoleClaimZero.getRegistrationId()).isEqualTo("07853842");
        assertThat(orgDomainRoleClaimZero.getUniqueTechnicalIdenifier()).isNull();

        OrgDomainRoleClaim orgDomainRoleClaimOne = participant.getOrgDomainRoleClaims().get(1);

        assertThat(orgDomainRoleClaimOne.getOrganisationId()).isNull();
        assertThat(orgDomainRoleClaimOne.getOrganisationAuthorityClaimId()).isNull();
        assertThat(orgDomainRoleClaimOne.getAuthorityId()).isNull();
        assertThat(orgDomainRoleClaimOne.getStatus()).isEqualTo("Active");
        assertThat(orgDomainRoleClaimOne.getAuthorisationDomain()).isEqualTo("Open Banking");
        assertThat(orgDomainRoleClaimOne.getRole()).isEqualTo("DADOS");
        assertThat(orgDomainRoleClaimOne.getRegistrationId()).isEqualTo("07853842");
        assertThat(orgDomainRoleClaimOne.getUniqueTechnicalIdenifier()).isNull();

        OrgDomainRoleClaim orgDomainRoleClaimTwo = participant.getOrgDomainRoleClaims().get(2);

        assertThat(orgDomainRoleClaimTwo.getOrganisationId()).isNull();
        assertThat(orgDomainRoleClaimTwo.getOrganisationAuthorityClaimId()).isNull();
        assertThat(orgDomainRoleClaimTwo.getAuthorityId()).isNull();
        assertThat(orgDomainRoleClaimTwo.getStatus()).isEqualTo("Active");
        assertThat(orgDomainRoleClaimTwo.getAuthorisationDomain()).isEqualTo("Open Banking");
        assertThat(orgDomainRoleClaimTwo.getRole()).isEqualTo("CONTA");
        assertThat(orgDomainRoleClaimTwo.getRegistrationId()).isEqualTo("07853842");
        assertThat(orgDomainRoleClaimTwo.getUniqueTechnicalIdenifier()).isNull();
    }

    private List<Participant> createParticipant() {
        Participant participant = new Participant();

        participant.setOrganisationId("fefac57d-1d50-5615-89b2-0b2d80623a28");
        participant.setStatus("Active");
        participant.setOrganisationName("CCR DE OURO");
        participant.setCreatedOn("2021-01-11T17:11:30.743Z");
        participant.setLegalEntityName("COOPERATIVA DE CRÉDITO RURAL DE OURO   SULCREDI/OURO");
        participant.setCountryOfRegistration("BR");
        participant.setCompanyRegister("Cadastro Nacional da Pessoa Jurídica");
        participant.setRegistrationNumber("07853842");
        participant.setRegistrationId("07853842");
        participant.setRegisteredName("COOPERATIVA DE CRÉDITO RURAL DE OURO   SULCREDI/OURO");
        participant.setAddressLine1("Rua Felipe Schmidt 1882");
        participant.setAddressLine2("Centro");
        participant.setCity("Ouro, SC");
        participant.setPostcode("89663-000");
        participant.setCountry("BR");
        participant.setParentOrganisationReference("");
        participant.setContacts(null);
        participant.setAuthorisationServers(createAuthorisationServer());
        participant.setOrgDomainClaims(createOrgDomainClaims());
        participant.setOrgDomainRoleClaims(createOrgDomainRoleClaims());

        return Collections.singletonList(participant);
    }

    private List<OrgDomainClaim> createOrgDomainClaims() {
        OrgDomainClaim orgDomainClaim = new OrgDomainClaim();

        orgDomainClaim.setOrganisationAuthorityDomainClaimId(null);
        orgDomainClaim.setAuthorisationDomainName("Open Banking");
        orgDomainClaim.setAuthorityId(null);
        orgDomainClaim.setAuthorityName("Banco Central do Brasil");
        orgDomainClaim.setRegistrationId("");
        orgDomainClaim.setStatus("Active");

        return Collections.singletonList(orgDomainClaim);
    }

    private List<OrgDomainRoleClaim> createOrgDomainRoleClaims() {
        return List.of(
                createOrgDomainRoleClaim("PAGTO"),
                createOrgDomainRoleClaim("DADOS"),
                createOrgDomainRoleClaim("CONTA"));
    }

    private OrgDomainRoleClaim createOrgDomainRoleClaim(String role) {
        OrgDomainRoleClaim orgDomainRoleClaim = new OrgDomainRoleClaim();

        orgDomainRoleClaim.setOrganisationId(null);
        orgDomainRoleClaim.setOrganisationAuthorityClaimId(null);
        orgDomainRoleClaim.setAuthorityId(null);
        orgDomainRoleClaim.setStatus("Active");
        orgDomainRoleClaim.setAuthorisationDomain("Open Banking");
        orgDomainRoleClaim.setRole(role);
        orgDomainRoleClaim.setAuthorisations(Collections.emptyList());
        orgDomainRoleClaim.setRegistrationId("07853842");
        orgDomainRoleClaim.setUniqueTechnicalIdenifier(null);

        return orgDomainRoleClaim;
    }

    private List<AuthorisationServer> createAuthorisationServer() {
        AuthorisationServer authorisationServer = new AuthorisationServer();

        authorisationServer.setAuthorisationServerId("780f2093-e531-4ac0-b7b3-016d55812b4d");
        authorisationServer.setOrganisationId(null);
        authorisationServer.setAutoRegistrationSupported(true);
        authorisationServer.setApiResources(createApiResources());
        authorisationServer.setCustomerFriendlyDescription("Cashway");
        authorisationServer.setCustomerFriendlyLogoUri("https://api.sulcrediouro.com.br/open-banking/naoseaplica.png");
        authorisationServer.setCustomerFriendlyName("Open Banking Cashway");
        authorisationServer.setDeveloperPortalUri("https://api.sulcrediouro.com.br/open-banking/naoseaplica");
        authorisationServer.setTermsOfServiceUri("https://api.sulcrediouro.com.br/open-banking/naoseaplica");
        authorisationServer.setNotificationWebhook(null);
        authorisationServer.setNotificationWebhookStatus(null);
        authorisationServer.setOpenIDDiscoveryDocument("https://api.sulcrediouro.com.br/open-banking/naoseaplica");
        authorisationServer.setPayloadSigningCertLocationUri("https://api.sulcrediouro.com.br/open-banking/naoseaplica");
        authorisationServer.setParentAuthorisationServerId(null);

        return Collections.singletonList(authorisationServer);
    }

    private List<ApiResource> createApiResources() {
        return List.of(
                createApiResourcesChannels(),
                createApiResourcesDiscovery(),
                createApiResourcesAdmin(),
                createApiResourcesProductsServices(),
                createApiResourcesOther());
    }

    private ApiResource createApiResourcesChannels() {
        ApiResource apiResource = new ApiResource();

        apiResource.setApiResourceId("1c506bea-ed19-4289-81b7-f00ab48d8a1d");
        apiResource.setApiFamilyType("channels");
        apiResource.setApiVersion(1);
        apiResource.setApiDiscoveryEndpoints(createApiDiscoveryEndpointsChannel());

        return apiResource;
    }

    private List<ApiDiscoveryEndpoint> createApiDiscoveryEndpointsChannel() {
        ApiDiscoveryEndpoint apiDiscoveryEndpointOne = new ApiDiscoveryEndpoint();

        apiDiscoveryEndpointOne.setApiDiscoveryId("e84d8015-253d-4d8c-8861-0b904f4ade6a");
        apiDiscoveryEndpointOne.setApiEndpoint("https://api.sulcrediouro.com.br/open-banking/channels/v1/branches");

        ApiDiscoveryEndpoint apiDiscoveryEndpointTwo = new ApiDiscoveryEndpoint();

        apiDiscoveryEndpointTwo.setApiDiscoveryId("ac7683f5-34ff-4012-b5b3-fd03d393dde1");
        apiDiscoveryEndpointTwo.setApiEndpoint("https://api.sulcrediouro.com.br/open-banking/channels/v1/electronic-channels");

        ApiDiscoveryEndpoint apiDiscoveryEndpointThree = new ApiDiscoveryEndpoint();

        apiDiscoveryEndpointThree.setApiDiscoveryId("a4e4434f-cdf8-469c-9668-286488ec5069");
        apiDiscoveryEndpointThree.setApiEndpoint("https://api.sulcrediouro.com.br/open-banking/channels/v1/phone-channels");

        ApiDiscoveryEndpoint apiDiscoveryEndpointFour = new ApiDiscoveryEndpoint();

        apiDiscoveryEndpointFour.setApiDiscoveryId("4abde9c9-dba9-4306-ae2b-f006dc2c44c0");
        apiDiscoveryEndpointFour.setApiEndpoint("https://api.sulcrediouro.com.br/open-banking/channels/v1/banking-agents");

        return List.of(apiDiscoveryEndpointOne, apiDiscoveryEndpointTwo, apiDiscoveryEndpointThree, apiDiscoveryEndpointFour);
    }

    private ApiResource createApiResourcesDiscovery() {
        ApiResource apiResource = new ApiResource();

        apiResource.setApiResourceId("336bdf44-cf95-4112-b455-7f2d9ee5404d");
        apiResource.setApiFamilyType("discovery");
        apiResource.setApiVersion(1);
        apiResource.setApiDiscoveryEndpoints(createApiDiscoveryEndpointsDiscovery());

        return apiResource;
    }

    private List<ApiDiscoveryEndpoint> createApiDiscoveryEndpointsDiscovery() {
        ApiDiscoveryEndpoint apiDiscoveryEndpointOne = new ApiDiscoveryEndpoint();

        apiDiscoveryEndpointOne.setApiDiscoveryId("c598bbb0-2c86-4c94-99de-229f539cf747");
        apiDiscoveryEndpointOne.setApiEndpoint("https://api.sulcrediouro.com.br/open-banking/discovery/v1/status");

        ApiDiscoveryEndpoint apiDiscoveryEndpointTwo = new ApiDiscoveryEndpoint();

        apiDiscoveryEndpointTwo.setApiDiscoveryId("decc7e84-15e0-4957-aeac-f02958c8ffc0");
        apiDiscoveryEndpointTwo.setApiEndpoint("https://api.sulcrediouro.com.br/open-banking/discovery/v1/outages");

        return List.of(apiDiscoveryEndpointOne, apiDiscoveryEndpointTwo);
    }

    private ApiResource createApiResourcesAdmin() {
        ApiResource apiResource = new ApiResource();

        apiResource.setApiResourceId("192a141a-93ff-4f2b-ab52-6d00a9c312fe");
        apiResource.setApiFamilyType("admin");
        apiResource.setApiVersion(1);
        apiResource.setApiDiscoveryEndpoints(createApiDiscoveryEndpointsAdmin());

        return apiResource;
    }

    private List<ApiDiscoveryEndpoint> createApiDiscoveryEndpointsAdmin() {
        ApiDiscoveryEndpoint apiDiscoveryEndpoint = new ApiDiscoveryEndpoint();

        apiDiscoveryEndpoint.setApiDiscoveryId("d1b3983c-1236-49b6-b3ba-b4edecd749b3");
        apiDiscoveryEndpoint.setApiEndpoint("https://openb.uniprime.com.br/alliance/open-banking/admin/v1");

        return Collections.singletonList(apiDiscoveryEndpoint);
    }

    private ApiResource createApiResourcesProductsServices() {
        ApiResource apiResource = new ApiResource();

        apiResource.setApiResourceId("d27d866a-d33e-4841-afd5-029eee87d1d2");
        apiResource.setApiFamilyType("products-services");
        apiResource.setApiVersion(1);
        apiResource.setApiDiscoveryEndpoints(createApiDiscoveryEndpointsProductsServices());

        return apiResource;
    }

    private List<ApiDiscoveryEndpoint> createApiDiscoveryEndpointsProductsServices() {
        ApiDiscoveryEndpoint apiDiscoveryEndpointOne = new ApiDiscoveryEndpoint();

        apiDiscoveryEndpointOne.setApiDiscoveryId("0b03f92b-3ab2-4ef3-9dac-e1c842559048");
        apiDiscoveryEndpointOne.setApiEndpoint("https://api.bradesco.com/Losango/open-banking/products-services/v1/personal-credit-cards");

        ApiDiscoveryEndpoint apiDiscoveryEndpointTwo = new ApiDiscoveryEndpoint();

        apiDiscoveryEndpointTwo.setApiDiscoveryId("a8948b48-88d7-463e-9a7e-6da75d739983");
        apiDiscoveryEndpointTwo.setApiEndpoint("https://api.bradesco.com/Losango/open-banking/products-services/v1/personal-loans");

        ApiDiscoveryEndpoint apiDiscoveryEndpointThree = new ApiDiscoveryEndpoint();

        apiDiscoveryEndpointThree.setApiDiscoveryId("5bc148a3-722f-4b3e-9ae8-64e4a0c8c336");
        apiDiscoveryEndpointThree.setApiEndpoint("https://api.bradesco.com/Losango/open-banking/products-services/v1/personal-financings");

        return List.of(apiDiscoveryEndpointOne, apiDiscoveryEndpointTwo, apiDiscoveryEndpointThree);
    }

    private ApiResource createApiResourcesOther() {
        ApiResource apiResource = new ApiResource();

        apiResource.setApiResourceId("3f36ab86-7a8e-4d2e-9f7b-077f233d4bd8");
        apiResource.setApiFamilyType("other");
        apiResource.setApiVersion(1);
        apiResource.setApiDiscoveryEndpoints(Collections.emptyList());

        return apiResource;
    }
}
