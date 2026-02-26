package com.example.config;


import org.springframework.context.annotation.Configuration;

import com.example.repository.IArtistRepository;
import com.example.repository.ITrackRepository;
import com.example.repository.impl.ArtistRepository;
import com.example.repository.impl.TrackRepository;
import com.example.services.IArtistService;
import com.example.services.ITrackService;
import com.example.services.impl.ArtistService;
import com.example.services.impl.TrackService;

import org.springframework.context.annotation.Bean;


// Los que vayan a hacer el xml no creo que necesiten esto (creo que se borra) y les toca modificar el singleton tambien
@Configuration
public class AppConfig {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public IArtistRepository artistRepository(){
        return new ArtistRepository(trackRepository());
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ITrackRepository trackRepository(){
        return new TrackRepository();
    }

    @Bean
    public IArtistService artistService(){
        return new ArtistService(artistRepository());
    }
    @Bean
    public ITrackService trackService(){
        return new TrackService(trackRepository());
    }
}
