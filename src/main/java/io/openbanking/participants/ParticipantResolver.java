package io.openbanking.participants;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import io.openbanking.participants.payload.Participant;
import io.openbanking.participants.payload.ParticipantResponse;
import io.openbanking.participants.service.ParticipantService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@GraphQLApi
public class ParticipantResolver {

    private ParticipantService participantService;

    public ParticipantResolver(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GraphQLQuery
    public List<String> apiFamilyTypes() {
        return participantService.getApiFamilyTypes();
    }

    @GraphQLQuery
    public List<Participant> participants() {
        return participantService.getParticipants();
    }

    @GraphQLQuery
    public List<ParticipantResponse> participantsRelevantFields(String apiFamilyType) {
        return participantService.getParticipantsRelevantFields(apiFamilyType);
    }
}
