package io.openbanking.participants;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import io.openbanking.participants.payload.Participants;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@GraphQLApi
public class ParticipantsResolver {

    private ParticipantsClient participantsClient;

    public ParticipantsResolver(ParticipantsClient participantsClient) {
        this.participantsClient = participantsClient;
    }

    @GraphQLQuery
    public List<Participants> participants() {
        return participantsClient.getParticipants();
    }
}
