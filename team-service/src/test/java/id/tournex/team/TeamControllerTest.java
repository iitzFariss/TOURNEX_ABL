package id.tournex.team;
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
class TeamControllerTest {
  private static final String VALID_BODY = "{\"name\":\"Kapal Lompat\",\"tag\":\"KPL\",\"gameTitle\":\"VALORANT\",\"description\":\"Peserta\"}";
  @Autowired MockMvc mockMvc;
  @Test void getAllReturnsOk() throws Exception {
    mockMvc.perform(get("/api/v1/teams"))
      .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }
  @Test void postCreatesResourceAndReturns201() throws Exception {
    mockMvc.perform(post("/api/v1/teams").contentType(MediaType.APPLICATION_JSON).content(VALID_BODY))
      .andExpect(status().isCreated()).andExpect(jsonPath("$.id").exists());
  }
  @Test void getUnknownIdReturns404() throws Exception {
    mockMvc.perform(get("/api/v1/teams/999999"))
      .andExpect(status().isNotFound());
  }
  @Test void putUpdatesWholeResourceAndReturns200() throws Exception {
    long id = createTeam();
    mockMvc.perform(put("/api/v1/teams/" + id).contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Kapal Baru\",\"tag\":\"KBR\",\"gameTitle\":\"CS2\",\"description\":\"Update penuh\"}"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(id))
      .andExpect(jsonPath("$.name").value("Kapal Baru"))
      .andExpect(jsonPath("$.tag").value("KBR"))
      .andExpect(jsonPath("$.gameTitle").value("CS2"));
  }
  @Test void patchUpdatesOnlyProvidedFieldsAndReturns200() throws Exception {
    long id = createTeam();
    mockMvc.perform(patch("/api/v1/teams/" + id).contentType(MediaType.APPLICATION_JSON)
        .content("{\"description\":\"Update sebagian\"}"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name").value("Kapal Lompat"))
      .andExpect(jsonPath("$.tag").value("KPL"))
      .andExpect(jsonPath("$.description").value("Update sebagian"));
  }
  @Test void patchInvalidValueReturns400() throws Exception {
    long id = createTeam();
    mockMvc.perform(patch("/api/v1/teams/" + id).contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"\"}"))
      .andExpect(status().isBadRequest());
  }
  @Test void deleteRemovesResourceAndReturns204() throws Exception {
    long id = createTeam();
    mockMvc.perform(delete("/api/v1/teams/" + id))
      .andExpect(status().isNoContent());
    mockMvc.perform(get("/api/v1/teams/" + id))
      .andExpect(status().isNotFound());
  }
  @Test void putUnknownIdReturns404() throws Exception {
    mockMvc.perform(put("/api/v1/teams/999999").contentType(MediaType.APPLICATION_JSON).content(VALID_BODY))
      .andExpect(status().isNotFound());
  }
  @Test void patchUnknownIdReturns404() throws Exception {
    mockMvc.perform(patch("/api/v1/teams/999999").contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"X\"}"))
      .andExpect(status().isNotFound());
  }
  @Test void deleteUnknownIdReturns404() throws Exception {
    mockMvc.perform(delete("/api/v1/teams/999999"))
      .andExpect(status().isNotFound());
  }
  private long createTeam() throws Exception {
    MvcResult result = mockMvc.perform(post("/api/v1/teams").contentType(MediaType.APPLICATION_JSON).content(VALID_BODY))
      .andExpect(status().isCreated()).andReturn();
    Number id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
    return id.longValue();
  }
}
