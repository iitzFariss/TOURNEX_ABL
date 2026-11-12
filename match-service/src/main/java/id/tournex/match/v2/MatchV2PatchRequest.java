package id.tournex.match.v2;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.OffsetDateTime;

public record MatchV2PatchRequest(
        @Positive Long tournamentId,
        @Positive Long teamAId,
        @Positive Long teamBId,
        OffsetDateTime startsAt,
        @Pattern(regexp = "SCHEDULED|ONGOING|FINISHED|CANCELLED") String status,
        Long winnerTeamId,
        Integer scoreTeamA,
        Integer scoreTeamB
) {
}
