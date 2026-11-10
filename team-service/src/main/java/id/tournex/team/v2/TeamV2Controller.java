package id.tournex.team.v2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import id.tournex.team.Team;
import id.tournex.team.TeamRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v2/teams")
@Tag(name = "Team API v2", description = "Operasi API Team versi 2.0")
public class TeamV2Controller {

    private final TeamRepository repository;

    public TeamV2Controller(TeamRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Mengambil daftar tim dengan pagination")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Daftar tim berhasil diambil"),
        @ApiResponse(responseCode = "400", description = "Parameter tidak valid"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public PageResponse<TeamV2Response> getAll(
            @RequestParam(required = false) String game,
            @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        Page<Team> page = game == null
                ? repository.findAll(pageable)
                : repository.findByGameTitle(game, pageable);
        return PageResponse.from(page.map(TeamV2Mapper::toResponse));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Mengambil tim berdasarkan ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tim ditemukan"),
        @ApiResponse(responseCode = "404", description = "Tim tidak ditemukan"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public TeamV2Response getById(@PathVariable Long id) {
        return TeamV2Mapper.toResponse(findById(id));
    }

    @PostMapping
    @Operation(summary = "Menambah tim")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Tim berhasil dibuat"),
        @ApiResponse(responseCode = "400", description = "Permintaan tidak valid"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public ResponseEntity<TeamV2Response> create(@Valid @RequestBody TeamV2Request request) {
        Team saved = repository.save(TeamV2Mapper.toEntity(request));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(location)
                .body(TeamV2Mapper.toResponse(saved));
    }

    private Team findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Team dengan ID " + id + " tidak ditemukan"));
    }
}
