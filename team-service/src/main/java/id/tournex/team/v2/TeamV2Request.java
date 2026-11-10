package id.tournex.team.v2;

import jakarta.validation.constraints.NotBlank;

public record TeamV2Request(
        @NotBlank String name,
        @NotBlank String shortName,
        @NotBlank String game,
        String description
) {
}
