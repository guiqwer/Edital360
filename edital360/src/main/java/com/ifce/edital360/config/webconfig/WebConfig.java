package com.ifce.edital360.config.webconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mapeia URLs começando com /files/ para o diretório real no VPS
        registry
                .addResourceHandler("/publicos/**")
                .addResourceLocations("file:/home/app/edital360-arquivos/documentos/");

        registry
                .addResourceHandler("/sensiveis/**")
                .addResourceLocations("file:/home/app/edital360-arquivos/documentos/");
    }
}
