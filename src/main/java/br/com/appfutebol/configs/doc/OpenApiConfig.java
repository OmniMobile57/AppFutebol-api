package br.com.appfutebol.configs.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("App Futebol")
        .version("v0.0.1")
        .description("API que visa sortear times de futebol de forma equilibrada!")
        .termsOfService("termsOfService")
        .contact(contact()));
  }

  private Contact contact() {
    Contact contact = new Contact();
    contact.setEmail("omnimobile57@gmail.com");
    contact.setName("OmniMobile");
    contact.setUrl("url");
    return contact;
  }
}