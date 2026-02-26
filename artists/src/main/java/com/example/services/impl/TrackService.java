package com.example.services.impl;

import java.util.List;
import com.example.model.Track;

import com.example.repository.ITrackRepository;
import com.example.services.ITrackService;

public class TrackService implements ITrackService{

    private final ITrackRepository trackRepository;
    

    public TrackService(ITrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void createTrack(Track track) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTrack'");
    }

    @Override
    public List<Track> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public boolean deleteTrack(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTrack'");
    }

}
