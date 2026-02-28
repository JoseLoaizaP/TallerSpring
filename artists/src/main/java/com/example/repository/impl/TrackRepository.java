package com.example.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.model.Track;
import com.example.repository.ITrackRepository;

import jakarta.annotation.PostConstruct;

@Repository
public class TrackRepository implements ITrackRepository {

    private List<Track> tracks;
    private int counter = 1;

    //use iag
    @PostConstruct
    public void init() {
        tracks = new ArrayList<>();

        String[] genres = {"Pop", "Rock", "Jazz", "Reggaeton", "Hip-Hop"};

        for (int i = 1; i <= 50; i++) {
            Track t = new Track(
                    "Song " + i,
                    genres[i % genres.length],
                    120 + i,                 // duration
                    "Album " + ((i - 1) / 10 + 1)
            );

            t.setId(counter++);
            t.setArtists(new ArrayList<>()); // clave para many-to-many
            tracks.add(t);
        }
    }

    @Override
    public List<Track> findAll() {
        return tracks;
    }

    @Override
    public void save(Track track) {
        if (tracks == null) init(); // blindaje por si acaso (mejor que NPE)
        track.setId(counter++);
        if (track.getArtists() == null) track.setArtists(new ArrayList<>());
        tracks.add(track);
    }

    @Override
    public boolean delete(Integer id) {
        return tracks.removeIf(t -> t.getId().equals(id));
    }
}