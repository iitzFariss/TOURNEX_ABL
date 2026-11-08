package id.tournex.tournament;
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
class TournamentControllerTest {
  private static final String VALID_BODY = "{\"name\":\"TOURNEX Open\",\"gameTitle\":\"VALORANT\",\"description\":\"Community tournament\",\"status\":\"REGISTRATION\",\"startDate\":\"2026-12-01\",\"endDate\":\"2026-12-05\"}";
  @Autowired MockMvc mockMvc;
  @Test void getAllReturnsOk() throws Exception {
    mockMvc.perform(get("/api/v1/tournaments"))
      .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }
  @Test void postCreatesResourceAndReturns201() throws Exception {
    mockMvc.perform(post("/api/v1/tournaments").contentType(MediaType.APPLICATION_JSON).content(VALID_BODY))
      .andExpect(status().isCreated()).andExpect(jsonPath("$.id").exists());
  }
  @Test void getUnknownIdReturns404() throws Exception {
    mockMvc.perform(get("/api/v1/tournaments/999999"))
      .andExpect(status().isNotFound());
  }
  @Test void putUpdatesWholeResourceAndReturns200() throws Exception {
    long id = createTournament();
    mockMvc.perform(put("/api/v1/tournaments/" + id).contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"TOURNEX Finals\",\"gameTitle\":\"CS2\",\"description\":\"Update penuh\",\"status\":\"ONGOING\",\"startDate\":\"2026-12-02\",\"endDate\":\"2026-12-06\"}"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(id))
      .andExpect(jsonPath("$.name").value("TOURNEX Finals"))
      .andExpect(jsonPath("$.gameTitle").value("CS2"))
      .andExpect(jsonPath("$.status").value("ONGOING"));
  }
  @Test void patchUpdatesOnlyProvidedFieldsAndReturns200() throws Exception {
    long id = createTournament();
    mockMvc.perform(patch("/api/v1/tournaments/" + id).contentType(MediaType.APPLICATION_JSON)
        .content("{\"status\":\"ONGOING\"}"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name").value("TOURNEX Open"))
      .andExpect(jsonPath("$.status").value("ONGOING"));
  }
  @Test void patchInvalidValueReturns400() throws Exception {
    long id = createTournament();
    mockMvc.perform(patch("/api/v1/tournaments/" + id).contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"\"}"))
      .andExpect(status().isBadRequest());
  }
  @Test void deleteRemovesResourceAndReturns204() throws Exception {
    long id = createTournament();
    mockMvc.perform(delete("/api/v1/tournaments/" + id))
      .andExpect(status().isNoContent());
    mockMvc.perform(get("/api/v1/tournaments/" + id))
      .andExpect(status().isNotFound());
  }
  @Test void putUnknownIdReturns404() throws Exception {
    mockMvc.perform(put("/api/v1/tournaments/999999").contentType(MediaType.APPLICATION_JSON).content(VALID_BODY))
      .andExpect(status().isNotFound());
  }
  @Test void patchUnknownIdReturns404() throws Exception {
    mockMvc.perform(patch("/api/v1/tournaments/999999").contentType(MediaType.APPLICATION_JSON).content("{\"status\":\"ONGOING\"}"))
      .andExpect(status().isNotFound());
  }
  @Test void deleteUnknownIdReturns404() throws Exception {
    mockMvc.perform(delete("/api/v1/tournaments/999999"))
      .andExpect(status().isNotFound());
  }
  private long createTournament() throws Exception {
    MvcResult result = mockMvc.perform(post("/api/v1/tournaments").contentType(MediaType.APPLICATION_JSON).content(VALID_BODY))
      .andExpect(status().isCreated()).andReturn();
    Number id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
    return id.longValue();
  }
}
