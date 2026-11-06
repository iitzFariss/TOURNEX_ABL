    package id.tournex.team;
    import jakarta.persistence.*;
import java.time.*;
import jakarta.validation.constraints.*;
    @Entity
    @Table(name = "teams")
    public class Team {
      @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
            @NotBlank
private String name;
        @NotBlank
private String tag;
        @NotBlank
private String gameTitle;
    private String description;
      public Team() {}
      public Long getId() { return id; }
      public void setId(Long id) { this.id = id; }
          public String getName() { return name; }
      public void setName(String name) { this.name = name; }
      public String getTag() { return tag; }
      public void setTag(String tag) { this.tag = tag; }
      public String getGameTitle() { return gameTitle; }
      public void setGameTitle(String gameTitle) { this.gameTitle = gameTitle; }
      public String getDescription() { return description; }
      public void setDescription(String description) { this.description = description; }

    }
