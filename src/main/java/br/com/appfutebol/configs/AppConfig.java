package br.com.appfutebol.configs;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
@EnableWebMvc
public class AppConfig {

  @Value("${front.url.prod: }")
  private String PROD_BASE_URL;
  @Value("${front.url.local: }")
  private String DEV_BASE_URL;
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(@NotNull CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedOrigins(PROD_BASE_URL, DEV_BASE_URL)
            .allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true);
      }
    };
  }
}
