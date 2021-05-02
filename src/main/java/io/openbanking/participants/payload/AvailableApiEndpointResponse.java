package io.openbanking.participants.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AvailableApiEndpointResponse {

    private String organisationId;
    private String customerFriendlyName;
    private String apiFamilyType;
    private List<String> availableEndpoints;
}
