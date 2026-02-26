package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.model.Artist;

public interface IArtistService {
    public void createArtist(Artist artist);
    public List<Artist> getAll();
    public Optional<Artist> findByName(Integer name);
    public boolean deleteArtist(Integer id);
}
