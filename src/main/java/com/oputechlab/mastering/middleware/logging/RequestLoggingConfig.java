package com.oputechlab.mastering.middleware.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingConfig {

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);  // Untuk mencatat request body
        filter.setIncludeHeaders(true);
        filter.setMaxPayloadLength(10000);  // Batasi panjang body yang dicatat
        return filter;
    }
}
