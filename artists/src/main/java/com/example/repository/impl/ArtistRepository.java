package com.example.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import com.example.model.Artist;
import com.example.model.Track;
import com.example.repository.IArtistRepository;
import com.example.repository.ITrackRepository;

import jakarta.annotation.PostConstruct;

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


        String[] artistByTrack = {
            "Michael Jackson",
            "Nirvana",
            "Queen",
            "Bob Dylan",
            "John Lennon",
            "The Beatles",
            "The Beatles",
            "Eagles",
            "Led Zeppelin",
            "Guns N’ Roses",
            "Oasis",
            "Leonard Cohen",
            "Adele",
            "Adele",
            "Ed Sheeran",
            "The Weeknd",
            "Mark Ronson ft. Bruno Mars",
            "Bee Gees",
            "ABBA",
            "Whitney Houston",
            "Céline Dion",
            "Britney Spears",
            "Beyoncé",
            "Beyoncé ft. Jay-Z",
            "Eminem",
            "Dr. Dre ft. Snoop Dogg",
            "The Notorious B.I.G.",
            "2Pac ft. Dr. Dre",
            "Kendrick Lamar",
            "Drake",
            "Billie Eilish",
            "a-ha",
            "The Police",
            "Toto",
            "Bon Jovi",
            "AC/DC",
            "Prince",
            "Stevie Wonder",
            "Marvin Gaye",
            "Aretha Franklin",
            "Bob Marley & The Wailers",
            "Bob Marley & The Wailers",
            "Celia Cruz",
            "Daddy Yankee",
            "Luis Fonsi ft. Daddy Yankee",
            "Shakira ft. Wyclef Jean",
            "Santana ft. Rob Thomas",
            "Coldplay",
            "The White Stripes",
            "Daft Punk ft. Pharrell Williams"
        };

        List<Track> allTracks = trackRepository.findAll();

        for (int i = 0; i < allTracks.size(); i++) {
            Track t = allTracks.get(i);

            Artist a = new Artist(artistByTrack[i], "Unknown");
            a.setId(counter++);
            a.setTracks(new ArrayList<>());

            a.getTracks().add(t);
            if (t.getArtists() == null) t.setArtists(new ArrayList<>());
            t.getArtists().add(a);

            artists.add(a);
        }
    }

    @Override
    public List<Artist> findAll() {
        return artists;
    }

    @Override
    public void save(Artist artist) {
        if (artists == null) init();
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
        Optional<Artist> artistOpt = findByName(name);
        if (artistOpt.isPresent()) {
            return artistOpt.get().getTracks();
        }
        return List.of();
    }
}