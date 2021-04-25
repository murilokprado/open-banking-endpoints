package io.openbanking.participants;

import io.openbanking.participants.payload.Participants;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("participants")
public class ParticipantsController {

    private ParticipantsClient participantsClient;

    public ParticipantsController(ParticipantsClient participantsClient) {
        this.participantsClient = participantsClient;
    }

    @ApiOperation("Verifica os participantes do openbanking")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna participantes do openbanking"),
            @ApiResponse(code = 400, message = "Retorna que não existem participantes"),
            @ApiResponse(code = 500, message = "Ocorreu algum erro no servidor"),
    })
    @GetMapping
    public List<Object> getAll() {
        return participantsClient.getParticipantsObject();
    }

    @GetMapping("o")
    public List<Participants> getParticipants() {
        return participantsClient.getParticipants();
    }
}
