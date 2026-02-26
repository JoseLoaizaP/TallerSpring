package com.example.services;

import java.util.List;


import com.example.model.Track;

public interface ITrackService {
    public void createTrack(Track track);
    public List<Track> getAll();
    public boolean deleteTrack(Integer id);
}
