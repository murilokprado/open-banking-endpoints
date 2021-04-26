package io.openbanking.participants.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorisationServer {

    private String authorisationServerId;
    private String organisationId;
    private Boolean autoRegistrationSupported;
    private List<ApiResource> apiResources;
    private String customerFriendlyDescription;
    private String customerFriendlyLogoUri;
    private String customerFriendlyName;
    private String developerPortalUri;
    private String termsOfServiceUri;
    private String notificationWebhook;
    private String notificationWebhookStatus;
    private String openIDDiscoveryDocument;
    private String payloadSigningCertLocationUri;
    private String parentAuthorisationServerId;
}
