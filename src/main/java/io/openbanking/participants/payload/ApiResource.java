package io.openbanking.participants.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiResource {

    private String apiResourceId;
    private String apiFamilyType;
    private Integer apiVersion;
    private List<ApiDiscoveryEndpoint> apiDiscoveryEndpoints;
}
