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

    private static OffsetDateTime toOffsetDateTime(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.atOffset(ZoneOffset.UTC);
    }

    private static LocalDateTime toLocalDateTime(OffsetDateTime dateTime) {
        return dateTime == null
                ? null
                : dateTime.withOffsetSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }
}
