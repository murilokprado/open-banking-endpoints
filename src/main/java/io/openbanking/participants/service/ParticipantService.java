package io.openbanking.participants.service;

import io.openbanking.participants.payload.Participant;
import io.openbanking.participants.payload.ParticipantResponse;

import java.util.List;

public interface ParticipantService {

    List<Participant> getParticipants();

    List<ParticipantResponse> getParticipantsRelevantFields(String apiFamilyType);
}
