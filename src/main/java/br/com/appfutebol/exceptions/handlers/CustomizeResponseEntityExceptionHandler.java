package br.com.appfutebol.exceptions.handlers;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import br.com.appfutebol.configs.log.AuditLog;
import br.com.appfutebol.exceptions.AbstractException;
import br.com.appfutebol.exceptions.ExceptionResponse;
import br.com.appfutebol.exceptions.NotEnoughPlayersException;
import br.com.appfutebol.exceptions.OwnershipViolationException;
import br.com.appfutebol.exceptions.ResourceNotFoundException;
import java.util.Date;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@RestControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler{

  @AuditLog(action = "Handle All Exception", currentClass = "CustomizeResponseEntityExceptionHandler")
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest req) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),
      req.getDescription(false));
    ex.printStackTrace();
    return new ResponseEntity<>(exceptionResponse, INTERNAL_SERVER_ERROR);
  }

//  @AuditLog(action = "Handle Not Found Exception", currentClass = "CustomizeResponseEntityExceptionHandler")
//  @ExceptionHandler(ResourceNotFoundException.class)
//  public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex,
//    WebRequest req) {
//    ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),
//      req.getDescription(false));
//    ex.printStackTrace();
//    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
//  }
//  @AuditLog(action = "Handle Ownership Violation Exception", currentClass = "CustomizeResponseEntityExceptionHandler")
//  @ExceptionHandler(OwnershipViolationException.class)
//  public final ResponseEntity<ExceptionResponse> handleOwnershipViolationException(Exception ex,
//    WebRequest req) {
//    ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),
//      req.getDescription(false));
//    ex.printStackTrace();
//    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//  }
  @AuditLog(action = "Handle Abstract Exception", currentClass = "CustomizeResponseEntityExceptionHandler")
  @ExceptionHandler(AbstractException.class)
  public final ResponseEntity<ExceptionResponse> handleNotEnoughPlayersException(AbstractException ex,
    WebRequest req) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),
      req.getDescription(false));
    ex.printStackTrace();
    return new ResponseEntity<>(exceptionResponse, ex.getStatus());
  }
  @AuditLog(action = "Handle Method Argument Not Valid Exception", currentClass = "CustomizeResponseEntityExceptionHandler")
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
    WebRequest req) {
    FieldError fieldError = ex.getBindingResult().getFieldError();
    String message = fieldError.getDefaultMessage();

    ExceptionResponse exceptionResponse = new ExceptionResponse(message,
      req.getDescription(false));
    ex.printStackTrace();
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }


}