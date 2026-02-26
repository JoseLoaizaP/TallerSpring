package com.example.repository.impl;

import java.util.List;
import java.util.Optional;

import com.example.model.Artist;
import com.example.repository.IArtistRepository;

public class ArtistRepository implements IArtistRepository {
    private List<Artist> artists;
    private Integer conter = 1;

    @Override
    public void save(Artist artist) {
        artist.setId(conter++);
        artists.add(artist);
    }

    @Override
    public List<Artist> findAll() {
        return artists;
    }

    @Override
    public Optional<Artist> findByName(String name) {
        return artists.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst();
    }

    @Override
    public boolean delete(Integer id) {
        return artists.removeIf(artist -> artist.getId().equals(id));
    }

}
