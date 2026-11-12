package id.tournex.match.v2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import id.tournex.match.Match;
import id.tournex.match.MatchRepository;
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
@RequestMapping("/api/v2/matches")
@Tag(name = "Match API v2", description = "Operasi API Match versi 2.0")
public class MatchV2Controller {

    private final MatchRepository repository;

    public MatchV2Controller(MatchRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Mengambil daftar pertandingan dengan pagination")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Daftar pertandingan berhasil diambil"),
        @ApiResponse(responseCode = "400", description = "Parameter tidak valid"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public PageResponse<MatchV2Response> getAll(
            @RequestParam(required = false) Long tournamentId,
            @RequestParam(required = false) String status,
            @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        Page<Match> page;
        if (tournamentId != null && status != null) {
            page = repository.findByTournamentIdAndStatus(tournamentId, status, pageable);
        } else if (tournamentId != null) {
            page = repository.findByTournamentId(tournamentId, pageable);
        } else if (status != null) {
            page = repository.findByStatus(status, pageable);
        } else {
            page = repository.findAll(pageable);
        }
        return PageResponse.from(page.map(MatchV2Mapper::toResponse));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Mengambil pertandingan berdasarkan ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pertandingan ditemukan"),
        @ApiResponse(responseCode = "404", description = "Pertandingan tidak ditemukan"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public MatchV2Response getById(@PathVariable Long id) {
        return MatchV2Mapper.toResponse(findById(id));
    }

    @PostMapping
    @Operation(summary = "Menambah pertandingan")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Pertandingan berhasil dibuat"),
        @ApiResponse(responseCode = "400", description = "Permintaan tidak valid"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public ResponseEntity<MatchV2Response> create(@Valid @RequestBody MatchV2Request request) {
        Match saved = repository.save(MatchV2Mapper.toEntity(request));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(location)
                .body(MatchV2Mapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mengganti seluruh data pertandingan")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pertandingan berhasil diperbarui"),
        @ApiResponse(responseCode = "400", description = "Permintaan tidak valid"),
        @ApiResponse(responseCode = "404", description = "Pertandingan tidak ditemukan"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public MatchV2Response update(
            @PathVariable Long id,
            @Valid @RequestBody MatchV2Request request) {
        Match existing = findById(id);
        MatchV2Mapper.applyRequest(existing, request);
        return MatchV2Mapper.toResponse(repository.save(existing));
    }

    @PatchMapping(value = "/{id}", consumes = "application/merge-patch+json")
    @Operation(summary = "Memperbarui sebagian data pertandingan")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pertandingan berhasil diperbarui sebagian"),
        @ApiResponse(responseCode = "400", description = "Permintaan tidak valid"),
        @ApiResponse(responseCode = "404", description = "Pertandingan tidak ditemukan"),
        @ApiResponse(responseCode = "415", description = "Media type tidak didukung"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public MatchV2Response patch(
            @PathVariable Long id,
            @Valid @RequestBody MatchV2PatchRequest request) {
        Match existing = findById(id);
        MatchV2Mapper.applyPatch(existing, request);
        return MatchV2Mapper.toResponse(repository.save(existing));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Menghapus pertandingan berdasarkan ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Pertandingan berhasil dihapus"),
        @ApiResponse(responseCode = "404", description = "Pertandingan tidak ditemukan"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public void delete(@PathVariable Long id) {
        repository.delete(findById(id));
    }

    private Match findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Match dengan ID " + id + " tidak ditemukan"));
    }
}
