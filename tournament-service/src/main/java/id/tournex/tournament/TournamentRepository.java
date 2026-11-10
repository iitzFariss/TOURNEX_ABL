package id.tournex.tournament;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    Page<Tournament> findByStatus(String status, Pageable pageable);

    Page<Tournament> findByGameTitle(String gameTitle, Pageable pageable);

    Page<Tournament> findByStatusAndGameTitle(String status, String gameTitle, Pageable pageable);
}
