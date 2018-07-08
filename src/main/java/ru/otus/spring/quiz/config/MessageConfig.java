package ru.otus.spring.quiz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class MessageConfig {

    @Value("${language}")
    private String language;

    @Bean
    public Locale locale() {
        return new Locale(language);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource result = new ReloadableResourceBundleMessageSource();
        result.setBasename("/i18n/messages");
        result.setDefaultEncoding("UTF-8");
        return result;
    }
}
