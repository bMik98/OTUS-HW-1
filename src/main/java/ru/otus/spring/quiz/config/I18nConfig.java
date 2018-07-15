package ru.otus.spring.quiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class I18nConfig {

    private final AppProperties appProperties;

    @Autowired
    public I18nConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public Locale locale() {
        return new Locale(appProperties.getGeneral().getApplicationLanguage());
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource result = new ReloadableResourceBundleMessageSource();
        result.setBasename("/i18n/messages");
        result.setDefaultEncoding("UTF-8");
        return result;
    }
}
