package id.tournex.match.v2;

import java.time.OffsetDateTime;

public record MatchV2Response(
        Long id,
        Long tournamentId,
        Long teamAId,
        Long teamBId,
        OffsetDateTime startsAt,
        String status,
        Long winnerTeamId,
        Integer scoreTeamA,
        Integer scoreTeamB,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
