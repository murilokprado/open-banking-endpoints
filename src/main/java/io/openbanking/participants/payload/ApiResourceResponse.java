package io.openbanking.participants.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiResourceResponse {

    private String apiFamilyType;
    private Integer apiVersion;
    private List<ApiDiscoveryEndpointResponse> apiDiscoveryEndpoints;
}
