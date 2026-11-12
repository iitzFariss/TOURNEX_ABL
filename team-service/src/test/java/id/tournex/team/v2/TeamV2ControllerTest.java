package id.tournex.team.v2;

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
class TeamV2ControllerTest {

    private static final String VALID_BODY = "{\"name\":\"Kapal Lompat\",\"shortName\":\"KPL\",\"game\":\"VALORANT\",\"description\":\"Peserta\"}";

    @Autowired
    MockMvc mockMvc;

    @Test
    void getListReturnsPageEnvelope() throws Exception {
        mockMvc.perform(get("/api/v2/teams"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.page").value(0))
            .andExpect(jsonPath("$.size").value(20))
            .andExpect(jsonPath("$.totalElements").exists())
            .andExpect(jsonPath("$.totalPages").exists());
    }

    @Test
    void postCreatesResourceAndReturns201WithLocation() throws Exception {
        mockMvc.perform(post("/api/v2/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_BODY))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", containsString("/api/v2/teams/")))
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.shortName").value("KPL"))
            .andExpect(jsonPath("$.game").value("VALORANT"))
            .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    void getByIdReturns200() throws Exception {
        long id = create();
        mockMvc.perform(get("/api/v2/teams/" + id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.name").value("Kapal Lompat"));
    }

    @Test
    void getUnknownIdReturns404WithErrorBody() throws Exception {
        mockMvc.perform(get("/api/v2/teams/999999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value("NOT_FOUND"))
            .andExpect(jsonPath("$.path").value("/api/v2/teams/999999"));
    }

    @Test
    void postInvalidBodyReturns400() throws Exception {
        mockMvc.perform(post("/api/v2/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"shortName\":\"\",\"game\":\"\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));
    }

    private static final MediaType MERGE_PATCH = MediaType.parseMediaType("application/merge-patch+json");

    @Test
    void putUpdatesAllFieldsAndReturns200() throws Exception {
        long id = create();
        mockMvc.perform(put("/api/v2/teams/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Tim Baru\",\"shortName\":\"TBR\",\"game\":\"DOTA2\",\"description\":\"put\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.name").value("Tim Baru"))
            .andExpect(jsonPath("$.shortName").value("TBR"))
            .andExpect(jsonPath("$.game").value("DOTA2"));
    }

    @Test
    void putUnknownIdReturns404() throws Exception {
        mockMvc.perform(put("/api/v2/teams/999999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_BODY))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value("NOT_FOUND"));
    }

    @Test
    void putInvalidBodyReturns400() throws Exception {
        mockMvc.perform(put("/api/v2/teams/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"shortName\":\"\",\"game\":\"\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));
    }

    @Test
    void patchUpdatesOnlyProvidedFieldAndReturns200() throws Exception {
        long id = create();
        mockMvc.perform(patch("/api/v2/teams/" + id)
                .contentType(MERGE_PATCH)
                .content("{\"description\":\"diubah lewat patch\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.description").value("diubah lewat patch"))
            .andExpect(jsonPath("$.name").value("Kapal Lompat"))
            .andExpect(jsonPath("$.shortName").value("KPL"));
    }

    @Test
    void patchUnknownIdReturns404() throws Exception {
        mockMvc.perform(patch("/api/v2/teams/999999")
                .contentType(MERGE_PATCH)
                .content("{\"name\":\"X\"}"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value("NOT_FOUND"));
    }

    @Test
    void patchWithWrongContentTypeReturns415() throws Exception {
        long id = create();
        // Ketidakcocokan atribut consumes dideteksi Spring sebelum controller
        // dipanggil, sehingga body respons kosong.
        mockMvc.perform(patch("/api/v2/teams/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"X\"}"))
            .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void postWithUnsupportedContentTypeReturns415WithErrorBody() throws Exception {
        mockMvc.perform(post("/api/v2/teams")
                .contentType(MediaType.TEXT_PLAIN)
                .content(VALID_BODY))
            .andExpect(status().isUnsupportedMediaType())
            .andExpect(jsonPath("$.code").value("UNSUPPORTED_MEDIA_TYPE"));
    }

    @Test
    void deleteRemovesResourceAndReturns204() throws Exception {
        long id = create();
        mockMvc.perform(delete("/api/v2/teams/" + id))
            .andExpect(status().isNoContent());
        mockMvc.perform(get("/api/v2/teams/" + id))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteUnknownIdReturns404() throws Exception {
        mockMvc.perform(delete("/api/v2/teams/999999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value("NOT_FOUND"));
    }

    private long create() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v2/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_BODY))
            .andExpect(status().isCreated())
            .andReturn();
        Number id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        return id.longValue();
    }
}
