package ru.classdetail.bot.max.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "max")
public class MaxProperties {
    private String url;
    private String token;
    private String id;
    private String webhookSecret;
}
