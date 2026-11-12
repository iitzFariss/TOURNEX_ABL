package id.tournex.tournament.v2;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;

@RestControllerAdvice(basePackages = "id.tournex.tournament.v2")
public class ApiExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatus(
            ResponseStatusException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.resolve(exception.getStatusCode().value());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(new ErrorResponse(
                codeFor(status),
                exception.getReason(),
                List.of(),
                OffsetDateTime.now(),
                request.getRequestURI()
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<ErrorResponse.FieldError> details = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorResponse.FieldError(error.getField(), error.getDefaultMessage()))
                .toList();
        return ResponseEntity.badRequest().body(new ErrorResponse(
                "VALIDATION_ERROR",
                "Permintaan tidak valid",
                details,
                OffsetDateTime.now(),
                request.getRequestURI()
        ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleUnreadable(
            HttpMessageNotReadableException exception, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponse(
                "VALIDATION_ERROR",
                "Body permintaan tidak dapat dibaca",
                List.of(),
                OffsetDateTime.now(),
                request.getRequestURI()
        ));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedMediaType(
            HttpMediaTypeNotSupportedException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(new ErrorResponse(
                "UNSUPPORTED_MEDIA_TYPE",
                "Media type tidak didukung. Gunakan application/json untuk POST dan PUT, "
                        + "serta application/merge-patch+json untuk PATCH",
                List.of(),
                OffsetDateTime.now(),
                request.getRequestURI()
        ));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException exception, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponse(
                "VALIDATION_ERROR",
                "Parameter '" + exception.getName() + "' tidak valid",
                List.of(new ErrorResponse.FieldError(
                        exception.getName(), "Nilai parameter tidak sesuai tipe data")),
                OffsetDateTime.now(),
                request.getRequestURI()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(
            Exception exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(
                "INTERNAL_ERROR",
                "Terjadi kesalahan pada server",
                List.of(),
                OffsetDateTime.now(),
                request.getRequestURI()
        ));
    }

    private String codeFor(HttpStatus status) {
        return switch (status) {
            case BAD_REQUEST -> "VALIDATION_ERROR";
            case NOT_FOUND -> "NOT_FOUND";
            case CONFLICT -> "CONFLICT";
            case UNSUPPORTED_MEDIA_TYPE -> "UNSUPPORTED_MEDIA_TYPE";
            default -> "INTERNAL_ERROR";
        };
    }
}
