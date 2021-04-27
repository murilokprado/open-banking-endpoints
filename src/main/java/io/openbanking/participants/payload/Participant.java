package io.openbanking.participants.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Participant {

    private String organisationId;
    private String status;
    private String organisationName;
    private String createdOn;
    private String legalEntityName;
    private String countryOfRegistration;
    private String companyRegister;
    private String registrationNumber;
    private String registrationId;
    private String registeredName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postcode;
    private String country;
    private String parentOrganisationReference;
    private List<Contact> contacts;
    private List<AuthorisationServer> authorisationServers;
    private List<OrgDomainClaim> orgDomainClaims;
    private List<OrgDomainRoleClaim> orgDomainRoleClaims;
}
