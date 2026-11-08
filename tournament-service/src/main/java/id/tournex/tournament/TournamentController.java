package id.tournex.tournament;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tournaments")
@Tag(name = "Tournament API", description = "Operasi API Tournament versi 1.0")
public class TournamentController {

    private final TournamentRepository repository;
    private final Validator validator;

    public TournamentController(TournamentRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @GetMapping
    @Operation(summary = "Mengambil seluruh data tournament")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Daftar tournament berhasil diambil"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public List<Tournament> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Mengambil tournament berdasarkan ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tournament ditemukan"),
        @ApiResponse(responseCode = "404", description = "Tournament tidak ditemukan"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public Tournament getById(@PathVariable Long id) {
        return findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Membuat tournament baru")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Tournament berhasil dibuat"),
        @ApiResponse(responseCode = "400", description = "Permintaan tidak valid"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public Tournament create(@Valid @RequestBody Tournament request) {
        request.setId(null);
        return repository.save(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mengganti seluruh data tournament")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tournament berhasil diperbarui"),
        @ApiResponse(responseCode = "400", description = "Permintaan tidak valid"),
        @ApiResponse(responseCode = "404", description = "Tournament tidak ditemukan"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public Tournament update(@PathVariable Long id, @Valid @RequestBody Tournament request) {
        Tournament existing = findById(id);
        existing.setName(request.getName());
        existing.setGameTitle(request.getGameTitle());
        existing.setDescription(request.getDescription());
        existing.setStatus(request.getStatus());
        existing.setStartDate(request.getStartDate());
        existing.setEndDate(request.getEndDate());
        return repository.save(existing);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Memperbarui sebagian data tournament")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tournament berhasil diperbarui sebagian"),
        @ApiResponse(responseCode = "400", description = "Permintaan tidak valid"),
        @ApiResponse(responseCode = "404", description = "Tournament tidak ditemukan"),
        @ApiResponse(responseCode = "500", description = "Kesalahan pada server")
    })
    public Tournament patch(@PathVariable Long id, @RequestBody Tournament request) {
        Tournament existing = findById(id);
        if (request.getName() != null) existing.setName(request.getName());
        if (request.getGameTitle() != null) existing.setGameTitle(request.getGameTitle());
        if (request.getDescription() != null) existing.setDescription(request.getDescription());
        if (request.getStatus() != null) existing.setStatus(request.getStatus());
        if (request.getStartDate() != null) existing.setStartDate(request.getStartDate());
        if (request.getEndDate() != null) existing.setEndDate(request.getEndDate());
        validate(existing);
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Menghapus tournament berdasarkan ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Tournament berhasil dihapus"),
        @ApiResponse(responseCode = "404", description = "Tournament tidak ditemukan"),
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

    private void validate(Tournament entity) {
        validator.validate(entity).stream().findFirst().ifPresent(violation -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, violation.getMessage());
        });
    }
}
