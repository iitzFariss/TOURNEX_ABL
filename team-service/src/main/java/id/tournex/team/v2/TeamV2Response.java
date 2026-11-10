package id.tournex.team.v2;

import java.time.OffsetDateTime;

public record TeamV2Response(
        Long id,
        String name,
        String shortName,
        String game,
        String description,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
