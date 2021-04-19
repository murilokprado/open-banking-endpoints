package io.openbanking.participants;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "openbanking-bacen", url = "${openbanking-bacen.url:}")
public interface ParticipantsClient {

    @RequestMapping(method = RequestMethod.GET, value = "participants")
    List<Object> getParticipants();
}
