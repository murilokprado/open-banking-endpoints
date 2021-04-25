package io.openbanking.participants.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {

    private String ContactId;
    private String OrganisationId;
    private String ContactType;
    private String FirstName;
    private String LastName;
    private String Department;
    private String EmailAddress;
    private String PhoneNumber;
    private String AddressLine1;
    private String AddressLine2;
    private String City;
    private String Postcode;
    private String Country;
    private String AdditionalInformation;
    private String PgpPublicKey;
}
