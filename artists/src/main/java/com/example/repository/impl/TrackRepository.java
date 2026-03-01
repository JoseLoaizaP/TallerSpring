package com.example.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.model.Track;

import com.example.repository.ITrackRepository;

public class TrackRepository implements ITrackRepository {
    private List<Track> tracks;
    private Integer count = 1;



    @Override
    public void save(Track track) {
        track.setId(count++);
        tracks.add(track);
    }

    @Override
    public List<Track> findAll() {
        return tracks;        
    }

    @Override
    public boolean delete(Integer id) {
        return tracks.removeIf(track -> track.getId().equals(id));
    }
    
    //Usé IAG
    public void init(){

        tracks = new ArrayList<>();

        String[] genres = {"Pop", "Rock", "Jazz", "Reggaeton", "Hip-Hop"};

        for (int i = 1; i <= 50; i++) {

            Track track = new Track(
                    "Song " + i,
                    genres[i % genres.length],
                    180 + i,
                    "Album " + ((i - 1) / 10 + 1)
            );

            track.setId(count++); 
            track.setArtists(new ArrayList<>());

            tracks.add(track);
        }

        System.out.println("TrackRepository inicializado con 50 canciones");
    }

    public void destroy(){
        tracks.clear();
    }

    @Override
    public Optional<Track> findById(Integer id) {
        return tracks.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

}
