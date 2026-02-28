package com.example.repository.impl;

import com.example.model.Artist;
import com.example.model.Track;
import com.example.repository.IArtistRepository;
import com.example.repository.ITrackRepository;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@DependsOn("trackRepository") 
public class ArtistRepository implements IArtistRepository {

    private final ITrackRepository trackRepository;

    private List<Artist> artists;
    private int counter = 1;

    public ArtistRepository(ITrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    //use iag
    @PostConstruct
    public void init() {
        artists = new ArrayList<>();

        String[] nationalities = {
                "USA","Colombia","UK","Canada","Spain",
                "Brazil","Mexico","Argentina","France","Germany"
        };

        List<Track> allTracks = trackRepository.findAll();
        int trackIndex = 0;

        for (int i = 1; i <= 10; i++) {
            Artist a = new Artist("Artist " + i, nationalities[i - 1]);
            a.setId(counter++);
            a.setTracks(new ArrayList<>());

            for (int j = 0; j < 5; j++) {
                Track t = allTracks.get(trackIndex++);
                a.getTracks().add(t);

                if (t.getArtists() == null) t.setArtists(new ArrayList<>());
                t.getArtists().add(a);
            }

            artists.add(a);
        }
    }

    @Override
    public List<Artist> findAll() {
        return artists;
    }

    @Override
    public void save(Artist artist) {
        if (artists == null) init(); // blindaje
        artist.setId(counter++);
        if (artist.getTracks() == null) artist.setTracks(new ArrayList<>());
        artists.add(artist);
    }

    @Override
    public Optional<Artist> findByName(String name) {
        return artists.stream().filter(a -> a.getName().equals(name)).findFirst();
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Artist> found = artists.stream().filter(a -> a.getId().equals(id)).findFirst();
        if (found.isEmpty()) return false;

        Artist a = found.get();

        if (a.getTracks() != null) {
            for (Track t : a.getTracks()) {
                if (t.getArtists() != null) {
                    t.getArtists().removeIf(ar -> ar.getId().equals(id));
                }
            }
        }

        return artists.removeIf(ar -> ar.getId().equals(id));
    }

    @Override
    public List<Track> getArtistsTracks(String name) {
        return findByName(name).map(Artist::getTracks).orElse(List.of());
    }
}