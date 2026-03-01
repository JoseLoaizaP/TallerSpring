package com.example.repository;

import java.util.List;
import java.util.Optional;

import com.example.model.Artist;
import com.example.model.Track;

public interface IArtistRepository {
    public void save(Artist artist);
    public List<Artist> findAll();
    public Optional<Artist> findByName(String name);
    public boolean delete(Integer id);
    public List<Track> getArtistsTracks(String name);
} 