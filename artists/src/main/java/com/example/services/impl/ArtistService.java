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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createArtist'");
    }

    @Override
    public List<Artist> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Optional<Artist> findByName(Integer name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByName'");
    }

    @Override
    public boolean deleteArtist(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteArtist'");
    }


}
