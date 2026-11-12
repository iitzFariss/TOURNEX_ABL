package id.tournex.tournament.v2;

import jakarta.validation.constraints.Pattern;

import java.time.OffsetDateTime;

public record TournamentV2PatchRequest(
        String name,
        String game,
        String description,
        @Pattern(regexp = "DRAFT|PUBLISHED|ONGOING|COMPLETED|CANCELLED") String status,
        OffsetDateTime startsAt,
        OffsetDateTime endsAt
) {
}
