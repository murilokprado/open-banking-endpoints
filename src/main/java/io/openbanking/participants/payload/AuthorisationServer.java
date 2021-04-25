package io.openbanking.participants.payload;

import java.util.List;

public class AuthorisationServer {

    private String AuthorisationServerId;
    private String OrganisationId;
    private Boolean AutoRegistrationSupported;
    private List<ApiResource> ApiResources;
    private String CustomerFriendlyDescription;
    private String CustomerFriendlyLogoUri;
    private String CustomerFriendlyName;
    private String DeveloperPortalUri;
    private String TermsOfServiceUri;
    private String NotificationWebhook;
    private String NotificationWebhookStatus;
    private String OpenIDDiscoveryDocument;
    private String PayloadSigningCertLocationUri;
    private String ParentAuthorisationServerId;
}
