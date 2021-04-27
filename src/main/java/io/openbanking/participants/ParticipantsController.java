package io.openbanking.participants;

import io.openbanking.participants.payload.Participant;
import io.openbanking.participants.payload.ParticipantResponse;
import io.openbanking.participants.service.ParticipantService;
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

    private ParticipantService participantService;

    public ParticipantsController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @ApiOperation("Verifica os participantes do openbanking")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna participantes do openbanking"),
            @ApiResponse(code = 400, message = "Retorna que não existem participantes"),
            @ApiResponse(code = 500, message = "Ocorreu algum erro no servidor"),
    })
    @GetMapping
    public List<Participant> getParticipants() {
        return participantService.getParticipants();
    }

    @ApiOperation("Verifica as informações relevantes dos participantes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna participantes do openbanking"),
            @ApiResponse(code = 400, message = "Retorna que não existem participantes"),
            @ApiResponse(code = 500, message = "Ocorreu algum erro no servidor"),
    })
    @GetMapping("/relevant_fields")
    public List<ParticipantResponse> participantsRelevantFields(String apiFamilyType) {
        return participantService.getParticipantsRelevantFields(apiFamilyType);
    }
}
