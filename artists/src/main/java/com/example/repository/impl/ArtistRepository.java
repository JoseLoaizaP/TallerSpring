package com.example.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.model.Artist;
import com.example.model.Track;
import com.example.repository.IArtistRepository;
import com.example.repository.ITrackRepository;

public class ArtistRepository implements IArtistRepository {
    private List<Artist> artists;
    private Integer conter = 1;
    final ITrackRepository trackRepository;

    

    public ArtistRepository(ITrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void save(Artist artist) {
        artist.setId(conter++);
        artists.add(artist);
    }

    @Override
    public List<Artist> findAll() {
        return artists;
    }

    @Override
    public Optional<Artist> findByName(String name) {
        return artists.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst();
    }

    @Override
    public boolean delete(Integer id) {
        return artists.removeIf(artist -> artist.getId().equals(id));
    }

    //Usé IAG
    public void init(){
        artists = new ArrayList<>();

        String[] nationalities = {
                "USA", "Colombia", "UK", "Canada", "Spain",
                "Brazil", "Mexico", "Argentina", "France", "Germany"
        };

        List<Track> allTracks = trackRepository.findAll();

        int trackIndex = 0;

        for (int i = 1; i <= 10; i++) {

            Artist artist = new Artist(
                    "Artist " + i,
                    nationalities[i - 1]
            );

            artist.setId(conter++);
            artist.setTracks(new ArrayList<>());

            // asignar 5 canciones
            for (int j = 0; j < 5; j++) {

                Track track = allTracks.get(trackIndex);

                artist.getTracks().add(track);
                track.getArtists().add(artist);

                trackIndex++;
            }

            artists.add(artist);
        }

        System.out.println("ArtistRepository inicializado con 10 artistas");
    }

    public List<Track> getArtistsTracks(String name) {
        Optional<Artist> artistOpt = findByName(name);
        if (artistOpt.isPresent()) {
            return artistOpt.get().getTracks();
        }
        return List.of();
    }

    public void destroy(){
        artists.clear();
    }

}