package id.tournex.match.v2;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.OffsetDateTime;

public record MatchV2Request(
        @NotNull @Positive Long tournamentId,
        @NotNull @Positive Long teamAId,
        @NotNull @Positive Long teamBId,
        @NotNull OffsetDateTime startsAt,
        @NotBlank
        @Pattern(regexp = "SCHEDULED|ONGOING|FINISHED|CANCELLED")
        String status,
        Long winnerTeamId,
        Integer scoreTeamA,
        Integer scoreTeamB
) {
}
