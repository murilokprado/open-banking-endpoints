package io.openbanking.participants.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorisationServerResponse {

    private String developerPortalUri;
    private List<ApiResourceResponse> apiResourceResponses;
}
