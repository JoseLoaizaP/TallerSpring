package com.example.repository.impl;

import java.util.List;

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
    
    
}
