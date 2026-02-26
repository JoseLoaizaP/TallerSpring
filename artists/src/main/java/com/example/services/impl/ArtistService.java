package com.example.services.impl;

import java.util.List;
import java.util.Optional;

import com.example.model.Artist;
import com.example.repository.IArtistRepository;
import com.example.services.IArtistService;

public class ArtistService implements IArtistService{

    private final IArtistRepository artistRepository;


    public ArtistService(IArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public void createArtist(Artist artist) {
        artistRepository.save(artist);
    }

    @Override
    public List<Artist> getAll() {
        return artistRepository.findAll();
    }
    

    @Override
    public Optional<Artist> findByName(String name) {
        return artistRepository.findByName(name);
    }

    @Override
    public boolean deleteArtist(Integer id) {
        return artistRepository.delete(id);
    }


}
