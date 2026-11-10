package id.tournex.tournament.v2;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.OffsetDateTime;

public record TournamentV2Request(
        @NotBlank String name,
        @NotBlank String game,
        String description,
        @NotBlank
        @Pattern(regexp = "DRAFT|PUBLISHED|ONGOING|COMPLETED|CANCELLED")
        String status,
        @NotNull OffsetDateTime startsAt,
        @NotNull OffsetDateTime endsAt
) {
}
