package io.openbanking.participants.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrgDomainRoleClaimResponse {

    private String role;
    private String status;
}
