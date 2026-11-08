package id.tournex.team;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController
@RequestMapping("/api/v1/teams")
@Tag(name = "Team API", description = "Operasi API Team versi 1.0")
public class TeamController {
  private final TeamRepository repository;
  private final Validator validator;
  public TeamController(TeamRepository repository, Validator validator) {
    this.repository = repository;
    this.validator = validator;
  }
  @GetMapping
  @Operation(summary = "Mengambil seluruh data team")
  public List<Team> getAll() { return repository.findAll(); }
  @GetMapping("/{id}")
  @Operation(summary = "Mengambil team berdasarkan ID")
  public Team getById(@PathVariable Long id) { return findById(id); }
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Membuat team baru")
  public Team create(@Valid @RequestBody Team request) {
    request.setId(null);
    return repository.save(request);
  }
  @PutMapping("/{id}")
  @Operation(summary = "Mengganti seluruh data team")
  public Team update(@PathVariable Long id, @Valid @RequestBody Team request) {
    Team existing = findById(id);
    existing.setName(request.getName());
    existing.setTag(request.getTag());
    existing.setGameTitle(request.getGameTitle());
    existing.setDescription(request.getDescription());
    return repository.save(existing);
  }
  @PatchMapping("/{id}")
  @Operation(summary = "Memperbarui sebagian data team")
  public Team patch(@PathVariable Long id, @RequestBody Team request) {
    Team existing = findById(id);
    if (request.getName() != null) existing.setName(request.getName());
    if (request.getTag() != null) existing.setTag(request.getTag());
    if (request.getGameTitle() != null) existing.setGameTitle(request.getGameTitle());
    if (request.getDescription() != null) existing.setDescription(request.getDescription());
    validate(existing);
    return repository.save(existing);
  }
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Menghapus team berdasarkan ID")
  public void delete(@PathVariable Long id) { repository.delete(findById(id)); }
  private Team findById(Long id) {
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team dengan ID " + id + " tidak ditemukan"));
  }
  private void validate(Team entity) {
    validator.validate(entity).stream().findFirst().ifPresent(violation -> {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, violation.getMessage());
    });
  }
}
