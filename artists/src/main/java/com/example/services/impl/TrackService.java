package com.example.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Track;
import com.example.repository.ITrackRepository;
import com.example.services.ITrackService;

@Service
public class TrackService implements ITrackService {

    private final ITrackRepository trackRepository;

    public TrackService(ITrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void createTrack(Track track) {
        trackRepository.save(track);
    }

    @Override
    public List<Track> getAll() {
        return trackRepository.findAll();
    }

    @Override
    public boolean deleteTrack(Integer id) {
        return trackRepository.delete(id);
    }
}