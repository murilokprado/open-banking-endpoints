package io.openbanking.participants.payload;

import java.util.List;

public class OrgDomainRoleClaim {

    private String OrganisationId;
    private String OrganisationAuthorityClaimId;
    private String AuthorityId;
    private String Status;
    private String AuthorisationDomain;
    private String Role;
    private List<Authorisation> Authorisations;
    private String RegistrationId;
    private List<String> UniqueTechnicalIdenifier;
}
