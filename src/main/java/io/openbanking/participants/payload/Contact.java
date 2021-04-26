package io.openbanking.participants.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {

    private String contactId;
    private String organisationId;
    private String contactType;
    private String firstName;
    private String lastName;
    private String department;
    private String emailAddress;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postcode;
    private String country;
    private String additionalInformation;
    private String pgpPublicKey;
}
