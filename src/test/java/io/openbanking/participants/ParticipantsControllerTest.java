package io.openbanking.participants;

import io.openbanking.participants.service.ParticipantService;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(ParticipantsController.class)
public class ParticipantsControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParticipantService participantService;

    @Test
    public void shouldReturnParticipants() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/participants")).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONArray participants = new JSONArray(content);

        assertThat(participants.length()).isEqualTo(66);
    }
}
