package io.openbanking.participants.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrgDomainRoleClaim {

    private String organisationId;
    private String organisationAuthorityClaimId;
    private String authorityId;
    private String status;
    private String authorisationDomain;
    private String role;
    private List<Authorisation> authorisations;
    private String registrationId;
    private List<String> uniqueTechnicalIdenifier;
}
