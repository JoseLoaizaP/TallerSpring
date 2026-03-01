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

            String artistName = artistByTrack[i];
            String nationality = nationalityOf(artistName);

            Artist a = new Artist(artistName, nationality);
            a.setId(counter++);
            a.setTracks(new ArrayList<>());


            a.getTracks().add(t);
            if (t.getArtists() == null) t.setArtists(new ArrayList<>());
            t.getArtists().add(a);

            artists.add(a);
        }
    }

    private String nationalityOf(String artistName) {
        return switch (artistName) {
            case "Michael Jackson" -> "USA";
            case "Nirvana" -> "USA";
            case "Queen" -> "UK";
            case "Bob Dylan" -> "USA";
            case "John Lennon" -> "UK";
            case "The Beatles" -> "UK";
            case "Eagles" -> "USA";
            case "Led Zeppelin" -> "UK";
            case "Guns N’ Roses" -> "USA";
            case "Oasis" -> "UK";
            case "Leonard Cohen" -> "Canada";
            case "Adele" -> "UK";
            case "Ed Sheeran" -> "UK";
            case "The Weeknd" -> "Canada";
            case "Mark Ronson ft. Bruno Mars" -> "UK/USA";
            case "Bee Gees" -> "UK/Australia";
            case "ABBA" -> "Sweden";
            case "Whitney Houston" -> "USA";
            case "Céline Dion" -> "Canada";
            case "Britney Spears" -> "USA";
            case "Beyoncé" -> "USA";
            case "Beyoncé ft. Jay-Z" -> "USA";
            case "Eminem" -> "USA";
            case "Dr. Dre ft. Snoop Dogg" -> "USA";
            case "The Notorious B.I.G." -> "USA";
            case "2Pac ft. Dr. Dre" -> "USA";
            case "Kendrick Lamar" -> "USA";
            case "Drake" -> "Canada";
            case "Billie Eilish" -> "USA";
            case "a-ha" -> "Norway";
            case "The Police" -> "UK";
            case "Toto" -> "USA";
            case "Bon Jovi" -> "USA";
            case "AC/DC" -> "Australia";
            case "Prince" -> "USA";
            case "Stevie Wonder" -> "USA";
            case "Marvin Gaye" -> "USA";
            case "Aretha Franklin" -> "USA";
            case "Bob Marley & The Wailers" -> "Jamaica";
            case "Celia Cruz" -> "Cuba";
            case "Daddy Yankee" -> "Puerto Rico";
            case "Luis Fonsi ft. Daddy Yankee" -> "Puerto Rico";
            case "Shakira ft. Wyclef Jean" -> "Colombia/USA";
            case "Santana ft. Rob Thomas" -> "USA";
            case "Coldplay" -> "UK";
            case "The White Stripes" -> "USA";
            case "Daft Punk ft. Pharrell Williams" -> "France/USA";
            default -> "Unknown";
        };
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
        return findByName(name).map(Artist::getTracks).orElse(List.of());
    }
}