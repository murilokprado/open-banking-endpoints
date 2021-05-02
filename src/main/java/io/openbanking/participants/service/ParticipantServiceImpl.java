package io.openbanking.participants.service;

import feign.FeignException;
import io.openbanking.participants.ParticipantClient;
import io.openbanking.participants.ParticipantFactory;
import io.openbanking.participants.payload.ApiResource;
import io.openbanking.participants.payload.AvailableApiEndpointResponse;
import io.openbanking.participants.payload.Participant;
import io.openbanking.participants.payload.ParticipantResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private ParticipantClient participantClient;
    private ParticipantFactory participantFactory;

    public ParticipantServiceImpl(ParticipantClient participantClient, ParticipantFactory participantFactory) {
        this.participantClient = participantClient;
        this.participantFactory = participantFactory;
    }

    @Override
    public List<String> getApiFamilyTypes() {
        List<Participant> participants = getParticipants();

        return participants.stream()
                .map(p -> p.getAuthorisationServers().stream()
                        .flatMap(a -> a.getApiResources().stream())
                        .map(ApiResource::getApiFamilyType)
                        .distinct())
                .flatMap(Stream::distinct)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<AvailableApiEndpointResponse> getAvailableApiEndpointByApiFamilyType(String apiFamilyType) {
        List<Participant> participants = getParticipants();

        if (apiFamilyType != null) {
            participants = getParticipantsByApiFamilyTypes(apiFamilyType, participants);
        }

        return participantFactory.createAvailableEndpoint(participants, apiFamilyType);
    }

    @Override
    public List<Participant> getParticipants() {
        try {
            return participantClient.getParticipants();
        } catch (FeignException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ParticipantResponse> getParticipantsRelevantFields(String apiFamilyType) {
        List<Participant> participants = getParticipants();

        if (apiFamilyType != null) {
            participants = getParticipantsByApiFamilyTypes(apiFamilyType, participants);
        }

        return participantFactory.create(participants, apiFamilyType);
    }

    private List<Participant> getParticipantsByApiFamilyTypes(String apiFamilyType, List<Participant> participants) {
        return participants.stream()
                .filter(p -> p.getAuthorisationServers().stream()
                        .flatMap(a -> a.getApiResources().stream())
                        .anyMatch(api -> api.getApiFamilyType().equals(apiFamilyType)))
                .collect(Collectors.toList());
    }
}
