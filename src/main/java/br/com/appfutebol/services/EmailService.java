package br.com.appfutebol.services;

import br.com.appfutebol.models.Email;

public interface EmailService {

  void sendEmail(Email email);
}
