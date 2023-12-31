package ru.practicum.ewn.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.ewm.client.StatsClient;

@Configuration
public class StatClientConfig {
    @Value("${stats.client.url:http://localhost:9090/}")
    private String url;

    @Bean
    StatsClient connection() {
        return new StatsClient(WebClient.create(url));
    }
}
