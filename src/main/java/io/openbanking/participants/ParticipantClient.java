package io.openbanking.participants;

import io.openbanking.participants.payload.Participant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "openbanking-bacen", url = "${openbanking-bacen.url:}")
public interface ParticipantClient {

    @RequestMapping(method = RequestMethod.GET, value = "participants")
    List<Participant> getParticipants();
}
