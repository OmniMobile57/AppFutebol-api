package br.com.appfutebol.exceptions.handlers;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import br.com.appfutebol.configs.log.AuditLog;
import br.com.appfutebol.exceptions.ExceptionResponse;
import br.com.appfutebol.exceptions.OwnershipViolationException;
import br.com.appfutebol.exceptions.ResourceNotFoundException;
import java.util.Date;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @AuditLog(action = "Handle All Exception", currentClass = "CustomizeResponseEntityExceptionHandler")
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest req) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
      req.getDescription(false));
    ex.printStackTrace();
    return new ResponseEntity<>(exceptionResponse, INTERNAL_SERVER_ERROR);
  }

  @AuditLog(action = "Handle Not Found Exception", currentClass = "CustomizeResponseEntityExceptionHandler")
  @ExceptionHandler(ResourceNotFoundException.class)
  public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex,
    WebRequest req) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
      req.getDescription(false));
    ex.printStackTrace();
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
  @AuditLog(action = "Handle Ownership Violation Exception", currentClass = "CustomizeResponseEntityExceptionHandler")
  @ExceptionHandler(OwnershipViolationException.class)
  public final ResponseEntity<ExceptionResponse> handleOwnershipViolationException(Exception ex,
    WebRequest req) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
      req.getDescription(false));
    ex.printStackTrace();
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }


}