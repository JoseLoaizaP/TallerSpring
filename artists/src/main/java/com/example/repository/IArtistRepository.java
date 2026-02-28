package com.example.repository;

import java.util.List;
import java.util.Optional;

import com.example.model.Artist;
import com.example.model.Track;

public interface IArtistRepository {
    void save(Artist artist);
    List<Artist> findAll();
    Optional<Artist> findByName(String name);
    boolean delete(Integer id);
    List<Track> getArtistsTracks(String name);
}