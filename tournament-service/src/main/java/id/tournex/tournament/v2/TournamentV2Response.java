package id.tournex.tournament.v2;

import java.time.OffsetDateTime;

public record TournamentV2Response(
        Long id,
        String name,
        String game,
        String description,
        String status,
        OffsetDateTime startsAt,
        OffsetDateTime endsAt,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
