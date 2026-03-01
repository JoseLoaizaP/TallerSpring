package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.model.Artist;
import com.example.model.Track;

public interface IArtistService {
    public void createArtist(Artist artist);
    public List<Artist> getAll();
    public Optional<Artist> findByName(String name);
    public boolean deleteArtist(Integer id);
    public List<Track> getArtistsTracks(String name);
}
