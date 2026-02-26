package com.example.repository;

import java.util.List;

import com.example.model.Track;

public interface ITrackRepository {
    public void save(Track track);
    public List<Track> findAll();
    public boolean delete(Integer id);
}
