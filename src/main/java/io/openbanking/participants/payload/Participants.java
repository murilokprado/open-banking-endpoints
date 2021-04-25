package io.openbanking.participants.payload;

import java.util.List;

public class Participants {

    private String OrganisationId;
    private String Status;
    private String OrganisationName;
    private String CreatedOn;
    private String LegalEntityName;
    private String CountryOfRegistration;
    private String CompanyRegister;
    private String RegistrationNumber;
    private String RegistrationId;
    private String RegisteredName;
    private String AddressLine1;
    private String AddressLine2;
    private String City;
    private String Postcode;
    private String Country;
    private String ParentOrganisationReference;
    private List<Contact> Contacts;
    private List<AuthorisationServer> AuthorisationServers;
    private List<OrgDomainClaim> OrgDomainClaims;
    private List<OrgDomainRoleClaim> OrgDomainRoleClaims;
}
