package id.tournex.tournament.v2;

import id.tournex.tournament.Tournament;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

final class TournamentV2Mapper {

    private TournamentV2Mapper() {
    }

    static TournamentV2Response toResponse(Tournament entity) {
        return new TournamentV2Response(
                entity.getId(),
                entity.getName(),
                entity.getGameTitle(),
                entity.getDescription(),
                entity.getStatus(),
                toOffsetDateTime(entity.getStartDate()),
                toOffsetDateTime(entity.getEndDate()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    static Tournament toEntity(TournamentV2Request request) {
        Tournament entity = new Tournament();
        entity.setName(request.name());
        entity.setGameTitle(request.game());
        entity.setDescription(request.description());
        entity.setStatus(request.status());
        entity.setStartDate(toLocalDate(request.startsAt()));
        entity.setEndDate(toLocalDate(request.endsAt()));
        return entity;
    }

    static void applyRequest(Tournament entity, TournamentV2Request request) {
        entity.setName(request.name());
        entity.setGameTitle(request.game());
        entity.setDescription(request.description());
        entity.setStatus(request.status());
        entity.setStartDate(toLocalDate(request.startsAt()));
        entity.setEndDate(toLocalDate(request.endsAt()));
    }

    static void applyPatch(Tournament entity, TournamentV2PatchRequest patch) {
        if (patch.name() != null) {
            entity.setName(patch.name());
        }
        if (patch.game() != null) {
            entity.setGameTitle(patch.game());
        }
        if (patch.description() != null) {
            entity.setDescription(patch.description());
        }
        if (patch.status() != null) {
            entity.setStatus(patch.status());
        }
        if (patch.startsAt() != null) {
            entity.setStartDate(toLocalDate(patch.startsAt()));
        }
        if (patch.endsAt() != null) {
            entity.setEndDate(toLocalDate(patch.endsAt()));
        }
    }

    private static OffsetDateTime toOffsetDateTime(LocalDate date) {
        return date == null ? null : date.atStartOfDay().atOffset(ZoneOffset.UTC);
    }

    private static LocalDate toLocalDate(OffsetDateTime dateTime) {
        return dateTime == null ? null : dateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDate();
    }
}
