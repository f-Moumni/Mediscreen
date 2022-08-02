package com.mediscreen.assessment.config;

import com.mediscreen.assessment.exception.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class AssessmentConfig {

    @Bean
    public Docket assessmentApi() {

        return new Docket(DocumentationType.SWAGGER_2).select()
                                                      .apis(RequestHandlerSelectors.basePackage("com.mediscreen.assessment.controller"))
                                                      .build();
    }

    @Bean
    public CustomErrorDecoder errorDecoder() {

        return new CustomErrorDecoder();
    }
}
