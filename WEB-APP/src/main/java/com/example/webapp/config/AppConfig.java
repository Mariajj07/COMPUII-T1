package com.example.webapp.config;

import com.example.webapp.repositories.ArtistRepository;
import com.example.webapp.repositories.TrackRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.webapp")

public class AppConfig {
    @Bean
    public ArtistRepository createArtistRepo(){
        return new ArtistRepository();
    }
    @Bean
    public TrackRepository createTrackRepo(){
        return new TrackRepository();
    }
}
