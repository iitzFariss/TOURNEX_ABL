package id.tournex.tournament;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc
class TournamentControllerTest {
  @Autowired MockMvc mockMvc;
  @Test void getAllReturnsOk() throws Exception {
    mockMvc.perform(get("/api/v1/tournaments"))
      .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }
  @Test void postCreatesResourceAndReturns201() throws Exception {
    mockMvc.perform(post("/api/v1/tournaments").contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"TOURNEX Open\",\"gameTitle\":\"VALORANT\",\"description\":\"Community tournament\",\"status\":\"REGISTRATION\",\"startDate\":\"2026-12-01\",\"endDate\":\"2026-12-05\"}"))
      .andExpect(status().isCreated()).andExpect(jsonPath("$.id").exists());
  }
  @Test void getUnknownIdReturns404() throws Exception {
    mockMvc.perform(get("/api/v1/tournaments/999999"))
      .andExpect(status().isNotFound());
  }
}
