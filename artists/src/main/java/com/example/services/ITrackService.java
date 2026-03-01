package com.example.services;

import java.util.List;
import java.util.Optional;


import com.example.model.Track;

public interface ITrackService {
    public void createTrack(Track track);
    public List<Track> getAll();
    public boolean deleteTrack(Integer id);
    public Optional<Track> findById(Integer id);
}
