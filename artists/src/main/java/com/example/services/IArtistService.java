package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.model.Artist;
import com.example.model.Track;

public interface IArtistService {
    void createArtist(Artist artist);
    List<Artist> getAll();
    Optional<Artist> findByName(String name);
    boolean deleteArtist(Integer id);
    List<Track> getArtistsTracks(String name);
}