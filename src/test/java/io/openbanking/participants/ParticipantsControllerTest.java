package io.openbanking.participants;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.openbanking.app.OpenbankingApplication;
import io.openbanking.participants.payload.Participant;
import io.openbanking.participants.service.ParticipantService;
import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OpenbankingApplication.class)
public class ParticipantsControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Mock
    private ParticipantService participantService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldReturnParticipants() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/participants")).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONArray JsonParticipants = new JSONArray(content);

        assertThat(JsonParticipants.length()).isEqualTo(63);
    }
}
