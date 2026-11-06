package id.tournex.tournament;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController
@RequestMapping("/api/v1/tournaments")
@Tag(name = "Tournament API", description = "Operasi API Tournament versi 1.0")
public class TournamentController {
  private final TournamentRepository repository;
  public TournamentController(TournamentRepository repository) { this.repository = repository; }
  @GetMapping
  @Operation(summary = "Mengambil seluruh data tournament")
  public List<Tournament> getAll() { return repository.findAll(); }
  @GetMapping("/{id}")
  @Operation(summary = "Mengambil tournament berdasarkan ID")
  public Tournament getById(@PathVariable Long id) {
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament dengan ID " + id + " tidak ditemukan"));
  }
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Membuat tournament baru")
  public Tournament create(@Valid @RequestBody Tournament request) {
    request.setId(null);
    return repository.save(request);
  }
}
