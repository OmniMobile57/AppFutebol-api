package br.com.appfutebol.services.imp;

import br.com.appfutebol.configs.log.AuditLog;
import br.com.appfutebol.models.Email;
import br.com.appfutebol.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class EmailServiceIMP implements EmailService {

  private final JavaMailSender javaMailSender;
  @Value("${spring.mail.username}")
  private String emailFrom;


  @AuditLog(action = "Send email", currentClass = "EmailServiceIMP")
  @Override
  public void sendEmail(Email email){
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email.emailTo());
    message.setSubject(email.subject());
    message.setText(email.text());
    message.setFrom(emailFrom);
    javaMailSender.send(message);
  }


}
