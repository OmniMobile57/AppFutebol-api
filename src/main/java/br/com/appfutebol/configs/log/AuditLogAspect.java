package br.com.appfutebol.configs.log;

import static br.com.appfutebol.configs.log.AuditResult.ERROR;
import static br.com.appfutebol.configs.log.AuditResult.SUCCESS;

import br.com.appfutebol.models.Email;
import br.com.appfutebol.services.EmailService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"dev","prod"})
@RequiredArgsConstructor
@Log4j2
@Aspect
@Component
public class AuditLogAspect {

  private final EmailService emailService;
  @Value("${spring.mail.username}")
  private String emailTo;

  @AfterReturning("@annotation(auditLog)")
  public void logAudit(JoinPoint joinPoint, AuditLog auditLog) {
    String methodName = joinPoint.getSignature().getName();
    String currentClass = auditLog.currentClass();
    String action = auditLog.action();
    LocalDateTime dateTime = LocalDateTime.now();
    AuditLogEntry logEntry = new AuditLogEntry(currentClass, dateTime, SUCCESS, methodName, action);

    log.info(logEntry.logMessage());
  }

  @AfterThrowing(value = "@annotation(auditLog)", throwing = "ex")
  public void logAuditError(JoinPoint joinPoint, AuditLog auditLog, Throwable ex) {
    String methodName = joinPoint.getSignature().getName();
    String currentClass = auditLog.currentClass();
    String action = auditLog.action();
    LocalDateTime dateTime = LocalDateTime.now();
    AuditLogEntry logEntry = new AuditLogEntry(currentClass, dateTime, ERROR, methodName, action);
    String message = logEntry.logMessage(ex);
    log.error(message);

    emailService.sendEmail(buildEmail(message));
  }

  private Email buildEmail(String text) {

    return new Email(emailTo, text, "Um erro ocorreu na aplicação App Futebol!");
  }
}
    