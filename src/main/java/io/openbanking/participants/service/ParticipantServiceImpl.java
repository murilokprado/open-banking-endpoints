package io.openbanking.participants.service;

import io.openbanking.participants.ParticipantClient;
import io.openbanking.participants.ParticipantFactory;
import io.openbanking.participants.payload.Participant;
import io.openbanking.participants.payload.ParticipantResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private ParticipantClient participantClient;
    private ParticipantFactory participantFactory;

    public ParticipantServiceImpl(ParticipantClient participantClient, ParticipantFactory participantFactory) {
        this.participantClient = participantClient;
        this.participantFactory = participantFactory;
    }

    @Override
    public List<Participant> getParticipants() {
        return participantClient.getParticipants();
    }

    @Override
    public List<ParticipantResponse> getParticipantsRelevantFields(String apiFamilyType) {
        List<Participant> participants = this.getParticipants();

        if (apiFamilyType != null) {
            participants = participants.stream()
                    .filter(p -> p.getAuthorisationServers().stream()
                            .flatMap(a -> a.getApiResources().stream())
                            .anyMatch(api -> api.getApiFamilyType().equals(apiFamilyType)))
                    .collect(Collectors.toList());
        }

        return participantFactory.create(participants, apiFamilyType);
    }
}
