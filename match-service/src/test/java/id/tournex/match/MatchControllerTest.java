package id.tournex.match;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc
class MatchControllerTest {
  private static final String VALID_BODY = "{\"tournamentId\":1,\"teamAId\":1,\"teamBId\":2,\"scheduledAt\":\"2026-12-01T19:00:00\",\"status\":\"SCHEDULED\"}";
  @Autowired MockMvc mockMvc;
  @Test void getAllReturnsOk() throws Exception {
    mockMvc.perform(get("/api/v1/matches"))
      .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }
  @Test void postCreatesResourceAndReturns201() throws Exception {
    mockMvc.perform(post("/api/v1/matches").contentType(MediaType.APPLICATION_JSON).content(VALID_BODY))
      .andExpect(status().isCreated()).andExpect(jsonPath("$.id").exists());
  }
  @Test void getUnknownIdReturns404() throws Exception {
    mockMvc.perform(get("/api/v1/matches/999999"))
      .andExpect(status().isNotFound());
  }
  @Test void putUpdatesWholeResourceAndReturns200() throws Exception {
    long id = createMatch();
    mockMvc.perform(put("/api/v1/matches/" + id).contentType(MediaType.APPLICATION_JSON)
        .content("{\"tournamentId\":2,\"teamAId\":3,\"teamBId\":4,\"scheduledAt\":\"2026-12-02T20:00:00\",\"status\":\"ONGOING\",\"winnerTeamId\":3,\"scoreTeamA\":2,\"scoreTeamB\":1}"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(id))
      .andExpect(jsonPath("$.tournamentId").value(2))
      .andExpect(jsonPath("$.teamAId").value(3))
      .andExpect(jsonPath("$.status").value("ONGOING"))
      .andExpect(jsonPath("$.scoreTeamA").value(2));
  }
  @Test void patchUpdatesOnlyProvidedFieldsAndReturns200() throws Exception {
    long id = createMatch();
    mockMvc.perform(patch("/api/v1/matches/" + id).contentType(MediaType.APPLICATION_JSON)
        .content("{\"status\":\"FINISHED\",\"scoreTeamA\":3}"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.teamAId").value(1))
      .andExpect(jsonPath("$.status").value("FINISHED"))
      .andExpect(jsonPath("$.scoreTeamA").value(3));
  }
  @Test void patchInvalidValueReturns400() throws Exception {
    long id = createMatch();
    mockMvc.perform(patch("/api/v1/matches/" + id).contentType(MediaType.APPLICATION_JSON)
        .content("{\"status\":\"\"}"))
      .andExpect(status().isBadRequest());
  }
  @Test void deleteRemovesResourceAndReturns204() throws Exception {
    long id = createMatch();
    mockMvc.perform(delete("/api/v1/matches/" + id))
      .andExpect(status().isNoContent());
    mockMvc.perform(get("/api/v1/matches/" + id))
      .andExpect(status().isNotFound());
  }
  @Test void putUnknownIdReturns404() throws Exception {
    mockMvc.perform(put("/api/v1/matches/999999").contentType(MediaType.APPLICATION_JSON).content(VALID_BODY))
      .andExpect(status().isNotFound());
  }
  @Test void patchUnknownIdReturns404() throws Exception {
    mockMvc.perform(patch("/api/v1/matches/999999").contentType(MediaType.APPLICATION_JSON).content("{\"status\":\"FINISHED\"}"))
      .andExpect(status().isNotFound());
  }
  @Test void deleteUnknownIdReturns404() throws Exception {
    mockMvc.perform(delete("/api/v1/matches/999999"))
      .andExpect(status().isNotFound());
  }
  private long createMatch() throws Exception {
    MvcResult result = mockMvc.perform(post("/api/v1/matches").contentType(MediaType.APPLICATION_JSON).content(VALID_BODY))
      .andExpect(status().isCreated()).andReturn();
    Number id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
    return id.longValue();
  }
}
