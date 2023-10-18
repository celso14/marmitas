package com.api.marmitas.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.maps.GeoApiContext;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class AppConfig {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    GeoApiContext geoApiContext() {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY_GEOLOCATION");
        return new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }
}
