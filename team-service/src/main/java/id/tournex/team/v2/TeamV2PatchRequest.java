package id.tournex.team.v2;

public record TeamV2PatchRequest(
        String name,
        String shortName,
        String game,
        String description
) {
}
