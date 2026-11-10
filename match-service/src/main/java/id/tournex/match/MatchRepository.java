package id.tournex.match;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {

    Page<Match> findByTournamentId(Long tournamentId, Pageable pageable);

    Page<Match> findByStatus(String status, Pageable pageable);

    Page<Match> findByTournamentIdAndStatus(Long tournamentId, String status, Pageable pageable);
}
