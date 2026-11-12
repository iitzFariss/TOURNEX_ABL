package id.tournex.match.v2;

import id.tournex.match.Match;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

final class MatchV2Mapper {

    private MatchV2Mapper() {
    }

    static MatchV2Response toResponse(Match entity) {
        return new MatchV2Response(
                entity.getId(),
                entity.getTournamentId(),
                entity.getTeamAId(),
                entity.getTeamBId(),
                toOffsetDateTime(entity.getScheduledAt()),
                entity.getStatus(),
                entity.getWinnerTeamId(),
                entity.getScoreTeamA(),
                entity.getScoreTeamB(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    static Match toEntity(MatchV2Request request) {
        Match entity = new Match();
        entity.setTournamentId(request.tournamentId());
        entity.setTeamAId(request.teamAId());
        entity.setTeamBId(request.teamBId());
        entity.setScheduledAt(toLocalDateTime(request.startsAt()));
        entity.setStatus(request.status());
        entity.setWinnerTeamId(request.winnerTeamId());
        entity.setScoreTeamA(request.scoreTeamA());
        entity.setScoreTeamB(request.scoreTeamB());
        return entity;
    }

    static void applyRequest(Match entity, MatchV2Request request) {
        entity.setTournamentId(request.tournamentId());
        entity.setTeamAId(request.teamAId());
        entity.setTeamBId(request.teamBId());
        entity.setScheduledAt(toLocalDateTime(request.startsAt()));
        entity.setStatus(request.status());
        entity.setWinnerTeamId(request.winnerTeamId());
        entity.setScoreTeamA(request.scoreTeamA());
        entity.setScoreTeamB(request.scoreTeamB());
    }

    static void applyPatch(Match entity, MatchV2PatchRequest patch) {
        if (patch.tournamentId() != null) {
            entity.setTournamentId(patch.tournamentId());
        }
        if (patch.teamAId() != null) {
            entity.setTeamAId(patch.teamAId());
        }
        if (patch.teamBId() != null) {
            entity.setTeamBId(patch.teamBId());
        }
        if (patch.startsAt() != null) {
            entity.setScheduledAt(toLocalDateTime(patch.startsAt()));
        }
        if (patch.status() != null) {
            entity.setStatus(patch.status());
        }
        if (patch.winnerTeamId() != null) {
            entity.setWinnerTeamId(patch.winnerTeamId());
        }
        if (patch.scoreTeamA() != null) {
            entity.setScoreTeamA(patch.scoreTeamA());
        }
        if (patch.scoreTeamB() != null) {
            entity.setScoreTeamB(patch.scoreTeamB());
        }
    }

    private static OffsetDateTime toOffsetDateTime(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.atOffset(ZoneOffset.UTC);
    }

    private static LocalDateTime toLocalDateTime(OffsetDateTime dateTime) {
        return dateTime == null
                ? null
                : dateTime.withOffsetSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }
}
