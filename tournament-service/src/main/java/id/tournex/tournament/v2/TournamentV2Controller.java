package id.tournex.tournament.v2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import id.tournex.tournament.Tournament;
import id.tournex.tournament.TournamentRepository;
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
@RequestMapping("/api/v2/tournaments")
@Tag(name = "Tournament API v2", description = "Operasi API Tournament versi 2.0")
public class TournamentV2Controller {

    private final TournamentRepository repository;

    public TournamentV2Controller(TournamentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Mengambil daftar turnamen dengan pagination")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Daftar turnamen berhasil diambil"),
        @ApiResponse(responseCode = "400", description = "Parameter tidak valid"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public PageResponse<TournamentV2Response> getAll(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String game,
            @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        Page<Tournament> page;
        if (status != null && game != null) {
            page = repository.findByStatusAndGameTitle(status, game, pageable);
        } else if (status != null) {
            page = repository.findByStatus(status, pageable);
        } else if (game != null) {
            page = repository.findByGameTitle(game, pageable);
        } else {
            page = repository.findAll(pageable);
        }
        return PageResponse.from(page.map(TournamentV2Mapper::toResponse));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Mengambil turnamen berdasarkan ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Turnamen ditemukan"),
        @ApiResponse(responseCode = "404", description = "Turnamen tidak ditemukan"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public TournamentV2Response getById(@PathVariable Long id) {
        return TournamentV2Mapper.toResponse(findById(id));
    }

    @PostMapping
    @Operation(summary = "Menambah turnamen")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Turnamen berhasil dibuat"),
        @ApiResponse(responseCode = "400", description = "Permintaan tidak valid"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public ResponseEntity<TournamentV2Response> create(@Valid @RequestBody TournamentV2Request request) {
        Tournament saved = repository.save(TournamentV2Mapper.toEntity(request));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(location)
                .body(TournamentV2Mapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mengganti seluruh data turnamen")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Turnamen berhasil diperbarui"),
        @ApiResponse(responseCode = "400", description = "Permintaan tidak valid"),
        @ApiResponse(responseCode = "404", description = "Turnamen tidak ditemukan"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public TournamentV2Response update(
            @PathVariable Long id,
            @Valid @RequestBody TournamentV2Request request) {
        Tournament existing = findById(id);
        TournamentV2Mapper.applyRequest(existing, request);
        return TournamentV2Mapper.toResponse(repository.save(existing));
    }

    @PatchMapping(value = "/{id}", consumes = "application/merge-patch+json")
    @Operation(summary = "Memperbarui sebagian data turnamen")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Turnamen berhasil diperbarui sebagian"),
        @ApiResponse(responseCode = "400", description = "Permintaan tidak valid"),
        @ApiResponse(responseCode = "404", description = "Turnamen tidak ditemukan"),
        @ApiResponse(responseCode = "415", description = "Media type tidak didukung"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public TournamentV2Response patch(
            @PathVariable Long id,
            @Valid @RequestBody TournamentV2PatchRequest request) {
        Tournament existing = findById(id);
        TournamentV2Mapper.applyPatch(existing, request);
        return TournamentV2Mapper.toResponse(repository.save(existing));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Menghapus turnamen berdasarkan ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Turnamen berhasil dihapus"),
        @ApiResponse(responseCode = "404", description = "Turnamen tidak ditemukan"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public void delete(@PathVariable Long id) {
        repository.delete(findById(id));
    }

    private Tournament findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tournament dengan ID " + id + " tidak ditemukan"));
    }
}
