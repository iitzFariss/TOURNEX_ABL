package id.tournex.match.v2;

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
class MatchV2ControllerTest {

    private static final String VALID_BODY = "{\"tournamentId\":1,\"teamAId\":1,\"teamBId\":2,\"startsAt\":\"2026-12-01T19:00:00Z\",\"status\":\"SCHEDULED\"}";

    @Autowired
    MockMvc mockMvc;

    @Test
    void getListReturnsPageEnvelope() throws Exception {
        mockMvc.perform(get("/api/v2/matches"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.page").value(0))
            .andExpect(jsonPath("$.size").value(20))
            .andExpect(jsonPath("$.totalElements").exists())
            .andExpect(jsonPath("$.totalPages").exists());
    }

    @Test
    void postCreatesResourceAndReturns201WithLocation() throws Exception {
        mockMvc.perform(post("/api/v2/matches")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_BODY))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", containsString("/api/v2/matches/")))
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.tournamentId").value(1))
            .andExpect(jsonPath("$.startsAt").exists())
            .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    void getByIdReturns200() throws Exception {
        long id = create();
        mockMvc.perform(get("/api/v2/matches/" + id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.status").value("SCHEDULED"));
    }

    @Test
    void getUnknownIdReturns404WithErrorBody() throws Exception {
        mockMvc.perform(get("/api/v2/matches/999999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value("NOT_FOUND"))
            .andExpect(jsonPath("$.path").value("/api/v2/matches/999999"));
    }

    @Test
    void postInvalidBodyReturns400() throws Exception {
        mockMvc.perform(post("/api/v2/matches")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tournamentId\":0,\"teamAId\":0,\"teamBId\":0,\"startsAt\":\"2026-12-01T19:00:00Z\",\"status\":\"INVALID\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));
    }

    private static final MediaType MERGE_PATCH = MediaType.parseMediaType("application/merge-patch+json");

    @Test
    void putUpdatesAllFieldsAndReturns200() throws Exception {
        long id = create();
        mockMvc.perform(put("/api/v2/matches/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tournamentId\":2,\"teamAId\":3,\"teamBId\":4,\"startsAt\":\"2027-01-01T19:00:00Z\",\"status\":\"ONGOING\",\"scoreTeamA\":1,\"scoreTeamB\":0}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.tournamentId").value(2))
            .andExpect(jsonPath("$.teamAId").value(3))
            .andExpect(jsonPath("$.status").value("ONGOING"));
    }

    @Test
    void putUnknownIdReturns404() throws Exception {
        mockMvc.perform(put("/api/v2/matches/999999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_BODY))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value("NOT_FOUND"));
    }

    @Test
    void putInvalidBodyReturns400() throws Exception {
        mockMvc.perform(put("/api/v2/matches/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tournamentId\":0,\"teamAId\":0,\"teamBId\":0,\"startsAt\":null,\"status\":\"INVALID\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));
    }

    @Test
    void patchUpdatesOnlyProvidedFieldAndReturns200() throws Exception {
        long id = create();
        mockMvc.perform(patch("/api/v2/matches/" + id)
                .contentType(MERGE_PATCH)
                .content("{\"scoreTeamA\":2}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.scoreTeamA").value(2))
            .andExpect(jsonPath("$.status").value("SCHEDULED"))
            .andExpect(jsonPath("$.tournamentId").value(1));
    }

    @Test
    void patchUnknownIdReturns404() throws Exception {
        mockMvc.perform(patch("/api/v2/matches/999999")
                .contentType(MERGE_PATCH)
                .content("{\"status\":\"FINISHED\"}"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value("NOT_FOUND"));
    }

    @Test
    void patchWithWrongContentTypeReturns415() throws Exception {
        long id = create();
        // Ketidakcocokan atribut consumes dideteksi Spring sebelum controller
        // dipanggil, sehingga body respons kosong.
        mockMvc.perform(patch("/api/v2/matches/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"status\":\"FINISHED\"}"))
            .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void postWithUnsupportedContentTypeReturns415WithErrorBody() throws Exception {
        mockMvc.perform(post("/api/v2/matches")
                .contentType(MediaType.TEXT_PLAIN)
                .content(VALID_BODY))
            .andExpect(status().isUnsupportedMediaType())
            .andExpect(jsonPath("$.code").value("UNSUPPORTED_MEDIA_TYPE"));
    }

    @Test
    void deleteRemovesResourceAndReturns204() throws Exception {
        long id = create();
        mockMvc.perform(delete("/api/v2/matches/" + id))
            .andExpect(status().isNoContent());
        mockMvc.perform(get("/api/v2/matches/" + id))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteUnknownIdReturns404() throws Exception {
        mockMvc.perform(delete("/api/v2/matches/999999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value("NOT_FOUND"));
    }

    private long create() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v2/matches")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_BODY))
            .andExpect(status().isCreated())
            .andReturn();
        Number id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        return id.longValue();
    }
}
