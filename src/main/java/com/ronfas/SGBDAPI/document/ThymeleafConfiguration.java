package com.ronfas.SGBDAPI.document;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafConfiguration {
    @Bean
    public ClassLoaderTemplateResolver htmlTemplateResolver() {
        ClassLoaderTemplateResolver htmlTemplateResolver = new ClassLoaderTemplateResolver();
        htmlTemplateResolver.setPrefix("templates/");
        htmlTemplateResolver.setTemplateMode("HTML");
        htmlTemplateResolver.setSuffix(".html");
        htmlTemplateResolver.setCharacterEncoding("UTF-8");
        htmlTemplateResolver.setOrder(1);
        return htmlTemplateResolver;
    }
}
