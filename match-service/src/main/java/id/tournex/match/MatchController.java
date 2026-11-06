package id.tournex.match;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController
@RequestMapping("/api/v1/matches")
@Tag(name = "Match API", description = "Operasi API Match versi 1.0")
public class MatchController {
  private final MatchRepository repository;
  public MatchController(MatchRepository repository) { this.repository = repository; }
  @GetMapping
  @Operation(summary = "Mengambil seluruh data match")
  public List<Match> getAll() { return repository.findAll(); }
  @GetMapping("/{id}")
  @Operation(summary = "Mengambil match berdasarkan ID")
  public Match getById(@PathVariable Long id) {
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match dengan ID " + id + " tidak ditemukan"));
  }
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Membuat match baru")
  public Match create(@Valid @RequestBody Match request) {
    request.setId(null);
    return repository.save(request);
  }
}
