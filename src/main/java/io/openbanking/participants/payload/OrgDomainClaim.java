package io.openbanking.participants.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrgDomainClaim {

    private String organisationAuthorityDomainClaimId;
    private String authorisationDomainName;
    private String authorityId;
    private String authorityName;
    private String registrationId;
    private String status;
}
