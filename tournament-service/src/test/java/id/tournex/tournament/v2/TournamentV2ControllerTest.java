package id.tournex.tournament.v2;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TournamentV2ControllerTest {

    private static final String VALID_BODY = "{\"name\":\"TOURNEX V2\",\"game\":\"VALORANT\",\"description\":\"v2\",\"status\":\"DRAFT\",\"startsAt\":\"2026-12-01T09:00:00Z\",\"endsAt\":\"2026-12-05T18:00:00Z\"}";

    @Autowired
    MockMvc mockMvc;

    @Test
    void getListReturnsPageEnvelope() throws Exception {
        mockMvc.perform(get("/api/v2/tournaments"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.page").value(0))
            .andExpect(jsonPath("$.size").value(20))
            .andExpect(jsonPath("$.totalElements").exists())
            .andExpect(jsonPath("$.totalPages").exists());
    }

    @Test
    void postCreatesResourceAndReturns201WithLocation() throws Exception {
        mockMvc.perform(post("/api/v2/tournaments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_BODY))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", containsString("/api/v2/tournaments/")))
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.game").value("VALORANT"))
            .andExpect(jsonPath("$.startsAt").exists())
            .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    void getByIdReturns200() throws Exception {
        long id = create();
        mockMvc.perform(get("/api/v2/tournaments/" + id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.name").value("TOURNEX V2"));
    }

    @Test
    void getUnknownIdReturns404WithErrorBody() throws Exception {
        mockMvc.perform(get("/api/v2/tournaments/999999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value("NOT_FOUND"))
            .andExpect(jsonPath("$.path").value("/api/v2/tournaments/999999"));
    }

    @Test
    void postInvalidBodyReturns400() throws Exception {
        mockMvc.perform(post("/api/v2/tournaments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"game\":\"\",\"status\":\"INVALID\",\"startsAt\":\"2026-12-01T09:00:00Z\",\"endsAt\":\"2026-12-05T18:00:00Z\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));
    }

    private long create() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v2/tournaments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_BODY))
            .andExpect(status().isCreated())
            .andReturn();
        Number id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        return id.longValue();
    }
}
