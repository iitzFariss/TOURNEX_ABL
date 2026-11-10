package id.tournex.team.v2;

import java.time.OffsetDateTime;
import java.util.List;

public record ErrorResponse(
        String code,
        String message,
        List<FieldError> details,
        OffsetDateTime timestamp,
        String path
) {
    public record FieldError(String field, String message) {
    }
}
