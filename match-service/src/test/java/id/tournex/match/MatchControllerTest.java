package id.tournex.match;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc
class MatchControllerTest {
  @Autowired MockMvc mockMvc;
  @Test void getAllReturnsOk() throws Exception {
    mockMvc.perform(get("/api/v1/matches"))
      .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }
  @Test void postCreatesResourceAndReturns201() throws Exception {
    mockMvc.perform(post("/api/v1/matches").contentType(MediaType.APPLICATION_JSON).content("{\"tournamentId\":1,\"teamAId\":1,\"teamBId\":2,\"scheduledAt\":\"2026-12-01T19:00:00\",\"status\":\"SCHEDULED\"}"))
      .andExpect(status().isCreated()).andExpect(jsonPath("$.id").exists());
  }
  @Test void getUnknownIdReturns404() throws Exception {
    mockMvc.perform(get("/api/v1/matches/999999"))
      .andExpect(status().isNotFound());
  }
}
