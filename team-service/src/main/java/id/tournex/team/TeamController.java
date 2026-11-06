package id.tournex.team;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController
@RequestMapping("/api/v1/teams")
@Tag(name = "Team API", description = "Operasi API Team versi 1.0")
public class TeamController {
  private final TeamRepository repository;
  public TeamController(TeamRepository repository) { this.repository = repository; }
  @GetMapping
  @Operation(summary = "Mengambil seluruh data team")
  public List<Team> getAll() { return repository.findAll(); }
  @GetMapping("/{id}")
  @Operation(summary = "Mengambil team berdasarkan ID")
  public Team getById(@PathVariable Long id) {
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team dengan ID " + id + " tidak ditemukan"));
  }
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Membuat team baru")
  public Team create(@Valid @RequestBody Team request) {
    request.setId(null);
    return repository.save(request);
  }
}
