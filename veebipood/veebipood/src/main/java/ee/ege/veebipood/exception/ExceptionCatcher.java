package ee.ege.veebipood.exception;

import lombok.extern.log4j.Log4j2;
import org.hibernate.TransientPropertyValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

// @ExceptionHandler
@Log4j2
@ControllerAdvice
public class ExceptionCatcher {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorMessage> CustomException(ValidationException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setName(e.getMessage()); // throw new Exception("Email cannot be empty");
        errorMessage.setDate(new Date());
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorMessage> CustomException(TransientPropertyValueException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setName("Kategooria lisamata"); // throw new Exception("Email cannot be empty");
        errorMessage.setDate(new Date());
        return ResponseEntity.badRequest().body(errorMessage);
    }

// Lõpus live eelne ka kõik ülejäänud exceptionid peita
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorMessage> CustomException(RuntimeException e) {
//        log.error(e);
//        ErrorMessage errorMessage = new ErrorMessage();
//        errorMessage.setName("Unexpected error"); // throw new Exception("Email cannot be empty");
//        errorMessage.setDate(new Date());
//        return ResponseEntity.badRequest().body(errorMessage);
//    }
}
