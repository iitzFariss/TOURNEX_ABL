    package id.tournex.tournament;
    import jakarta.persistence.*;
import java.time.*;
import jakarta.validation.constraints.*;
    @Entity
    @Table(name = "tournaments")
    public class Tournament {
      @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
            @NotBlank
private String name;
        @NotBlank
private String gameTitle;
    private String description;
        @NotBlank
private String status;
        @NotNull
private LocalDate startDate;
        @NotNull
private LocalDate endDate;
      public Tournament() {}
      public Long getId() { return id; }
      public void setId(Long id) { this.id = id; }
          public String getName() { return name; }
      public void setName(String name) { this.name = name; }
      public String getGameTitle() { return gameTitle; }
      public void setGameTitle(String gameTitle) { this.gameTitle = gameTitle; }
      public String getDescription() { return description; }
      public void setDescription(String description) { this.description = description; }
      public String getStatus() { return status; }
      public void setStatus(String status) { this.status = status; }
      public LocalDate getStartDate() { return startDate; }
      public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
      public LocalDate getEndDate() { return endDate; }
      public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    }
