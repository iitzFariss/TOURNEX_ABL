package id.tournex.match;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController
@RequestMapping("/api/v1/matches")
@Tag(name = "Match API", description = "Operasi API Match versi 1.0")
public class MatchController {
  private final MatchRepository repository;
  private final Validator validator;
  public MatchController(MatchRepository repository, Validator validator) {
    this.repository = repository;
    this.validator = validator;
  }
  @GetMapping
  @Operation(summary = "Mengambil seluruh data match")
  public List<Match> getAll() { return repository.findAll(); }
  @GetMapping("/{id}")
  @Operation(summary = "Mengambil match berdasarkan ID")
  public Match getById(@PathVariable Long id) { return findById(id); }
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Membuat match baru")
  public Match create(@Valid @RequestBody Match request) {
    request.setId(null);
    return repository.save(request);
  }
  @PutMapping("/{id}")
  @Operation(summary = "Mengganti seluruh data match")
  public Match update(@PathVariable Long id, @Valid @RequestBody Match request) {
    Match existing = findById(id);
    existing.setTournamentId(request.getTournamentId());
    existing.setTeamAId(request.getTeamAId());
    existing.setTeamBId(request.getTeamBId());
    existing.setScheduledAt(request.getScheduledAt());
    existing.setStatus(request.getStatus());
    existing.setWinnerTeamId(request.getWinnerTeamId());
    existing.setScoreTeamA(request.getScoreTeamA());
    existing.setScoreTeamB(request.getScoreTeamB());
    return repository.save(existing);
  }
  @PatchMapping("/{id}")
  @Operation(summary = "Memperbarui sebagian data match")
  public Match patch(@PathVariable Long id, @RequestBody Match request) {
    Match existing = findById(id);
    if (request.getTournamentId() != null) existing.setTournamentId(request.getTournamentId());
    if (request.getTeamAId() != null) existing.setTeamAId(request.getTeamAId());
    if (request.getTeamBId() != null) existing.setTeamBId(request.getTeamBId());
    if (request.getScheduledAt() != null) existing.setScheduledAt(request.getScheduledAt());
    if (request.getStatus() != null) existing.setStatus(request.getStatus());
    if (request.getWinnerTeamId() != null) existing.setWinnerTeamId(request.getWinnerTeamId());
    if (request.getScoreTeamA() != null) existing.setScoreTeamA(request.getScoreTeamA());
    if (request.getScoreTeamB() != null) existing.setScoreTeamB(request.getScoreTeamB());
    validate(existing);
    return repository.save(existing);
  }
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Menghapus match berdasarkan ID")
  public void delete(@PathVariable Long id) { repository.delete(findById(id)); }
  private Match findById(Long id) {
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match dengan ID " + id + " tidak ditemukan"));
  }
  private void validate(Match entity) {
    validator.validate(entity).stream().findFirst().ifPresent(violation -> {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, violation.getMessage());
    });
  }
}
