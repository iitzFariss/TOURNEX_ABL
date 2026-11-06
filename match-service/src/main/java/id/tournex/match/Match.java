    package id.tournex.match;
    import jakarta.persistence.*;
import java.time.*;
import jakarta.validation.constraints.*;
    @Entity
    @Table(name = "matches")
    public class Match {
      @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
      public Match() {}
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

    }
