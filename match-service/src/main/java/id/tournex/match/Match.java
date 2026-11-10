package id.tournex.match;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private Long tournamentId;

    @NotNull
    @Positive
    private Long teamAId;

    @NotNull
    @Positive
    private Long teamBId;

    @NotNull
    private LocalDateTime scheduledAt;

    @NotBlank
    private String status;

    private Long winnerTeamId;

    private Integer scoreTeamA;

    private Integer scoreTeamB;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(nullable = false)
    private OffsetDateTime updatedAt;

    public Match() {
    }

    @PrePersist
    void onCreate() {
        OffsetDateTime now = OffsetDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTournamentId() { return tournamentId; }
    public void setTournamentId(Long tournamentId) { this.tournamentId = tournamentId; }

    public Long getTeamAId() { return teamAId; }
    public void setTeamAId(Long teamAId) { this.teamAId = teamAId; }

    public Long getTeamBId() { return teamBId; }
    public void setTeamBId(Long teamBId) { this.teamBId = teamBId; }

    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getWinnerTeamId() { return winnerTeamId; }
    public void setWinnerTeamId(Long winnerTeamId) { this.winnerTeamId = winnerTeamId; }

    public Integer getScoreTeamA() { return scoreTeamA; }
    public void setScoreTeamA(Integer scoreTeamA) { this.scoreTeamA = scoreTeamA; }

    public Integer getScoreTeamB() { return scoreTeamB; }
    public void setScoreTeamB(Integer scoreTeamB) { this.scoreTeamB = scoreTeamB; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}
