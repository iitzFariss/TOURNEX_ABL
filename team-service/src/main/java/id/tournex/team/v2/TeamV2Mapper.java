package id.tournex.team.v2;

import id.tournex.team.Team;

final class TeamV2Mapper {

    private TeamV2Mapper() {
    }

    static TeamV2Response toResponse(Team entity) {
        return new TeamV2Response(
                entity.getId(),
                entity.getName(),
                entity.getTag(),
                entity.getGameTitle(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    static Team toEntity(TeamV2Request request) {
        Team entity = new Team();
        entity.setName(request.name());
        entity.setTag(request.shortName());
        entity.setGameTitle(request.game());
        entity.setDescription(request.description());
        return entity;
    }

    static void applyRequest(Team entity, TeamV2Request request) {
        entity.setName(request.name());
        entity.setTag(request.shortName());
        entity.setGameTitle(request.game());
        entity.setDescription(request.description());
    }

    static void applyPatch(Team entity, TeamV2PatchRequest patch) {
        if (patch.name() != null) {
            entity.setName(patch.name());
        }
        if (patch.shortName() != null) {
            entity.setTag(patch.shortName());
        }
        if (patch.game() != null) {
            entity.setGameTitle(patch.game());
        }
        if (patch.description() != null) {
            entity.setDescription(patch.description());
        }
    }
}
