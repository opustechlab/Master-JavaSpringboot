package com.oputechlab.mastering.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "piwapi")
@Data
public class PiwapiConfiguration {
    private String apiKey;
    private String url;
    private String accountId;
}
