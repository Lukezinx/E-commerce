package lucas.ecommerce.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandartError> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {

        String errorMessage = ex.getBindingResult().getFieldErrors().getFirst().getDefaultMessage();

        StandartError error = new StandartError(
                LocalDate.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validate Error",
                errorMessage,
                request.getRequestURI()

        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandartError> handleDatabaseException(org.springframework.dao.DataIntegrityViolationException ex, HttpServletRequest request) {
        StandartError error = new StandartError(
                LocalDate.now(),
                HttpStatus.CONFLICT.value(),
                "Data Breach",
                "You cannot delete this record because it is linked to other items in the system.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandartError> handleMessageNotReadable(org.springframework.http.converter.HttpMessageNotReadableException ex, HttpServletRequest request) {
        StandartError error = new StandartError(
                LocalDate.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Invalid or Malformed JSON",
                "The request you sent has an invalid format. Please check the structure of your JSON.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandartError> handleGenericException(Exception ex, HttpServletRequest request) {
        StandartError error = new StandartError(
                LocalDate.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "An unexpected error has occurred in our system. Please try again later.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    
}
