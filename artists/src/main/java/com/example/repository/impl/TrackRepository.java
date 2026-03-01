package com.example.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.model.Track;
import com.example.repository.ITrackRepository;

import jakarta.annotation.PostConstruct;

@Repository
public class TrackRepository implements ITrackRepository {

    private List<Track> tracks;
    private int counter = 1;

    //use iag
    @PostConstruct
    public void init() {
        tracks = new ArrayList<>();


        Object[][] data = {
            {"Billie Jean", "Thriller", "Pop", 294},
            {"Smells Like Teen Spirit", "Nevermind", "Grunge / Rock", 301},
            {"Bohemian Rhapsody", "A Night at the Opera", "Rock", 354},
            {"Like a Rolling Stone", "Highway 61 Revisited", "Rock / Folk Rock", 371},
            {"Imagine", "Imagine", "Pop Rock", 183},
            {"Hey Jude", "Hey Jude", "Rock", 431},
            {"I Want to Hold Your Hand", "Meet the Beatles!", "Pop Rock", 145},
            {"Hotel California", "Hotel California", "Rock", 391},
            {"Stairway to Heaven", "Led Zeppelin IV", "Rock", 482},
            {"Sweet Child o’ Mine", "Appetite for Destruction", "Hard Rock", 356},
            {"Wonderwall", "(What’s the Story) Morning Glory?", "Britpop", 258},
            {"Hallelujah", "Various Positions", "Folk", 270},
            {"Rolling in the Deep", "21", "Pop / Soul", 228},
            {"Someone Like You", "21", "Pop", 285},
            {"Shape of You", "÷ (Divide)", "Pop", 235},
            {"Blinding Lights", "After Hours", "Synthpop", 200},
            {"Uptown Funk", "Uptown Special", "Funk / Pop", 270},
            {"Stayin’ Alive", "Saturday Night Fever", "Disco", 285},
            {"Dancing Queen", "Arrival", "Pop / Disco", 230},
            {"I Will Always Love You", "The Bodyguard", "Pop / Soul", 273},
            {"My Heart Will Go On", "Let’s Talk About Love", "Pop", 280},
            {"Toxic", "In the Zone", "Pop", 198},
            {"Single Ladies (Put a Ring on It)", "I Am… Sasha Fierce", "Pop / R&B", 195},
            {"Crazy in Love", "Dangerously in Love", "R&B / Pop", 235},
            {"Lose Yourself", "8 Mile", "Hip-hop", 326},
            {"Nuthin’ but a “G” Thang", "The Chronic", "Hip-hop", 238},
            {"Juicy", "Ready to Die", "Hip-hop", 300},
            {"California Love", "All Eyez on Me", "Hip-hop", 275},
            {"HUMBLE.", "DAMN.", "Hip-hop", 177},
            {"Gods Plan", "Scorpion", "Hip-hop", 198},
            {"Bad Guy", "When We All Fall Asleep, Where Do We Go?", "Pop", 194},
            {"Take On Me", "Hunting High and Low", "Synthpop", 225},
            {"Every Breath You Take", "Synchronicity", "Rock / Pop Rock", 255},
            {"Africa", "Toto IV", "Rock", 295},
            {"Livin’ on a Prayer", "Slippery When Wet", "Rock", 250},
            {"Back in Black", "Back in Black", "Hard Rock", 255},
            {"Purple Rain", "Purple Rain", "Pop / Rock", 350},
            {"Superstition", "Talking Book", "Funk / Soul", 250},
            {"What’s Going On", "What’s Going On", "Soul", 233},
            {"Respect", "I Never Loved a Man the Way I Love You", "Soul", 150},
            {"No Woman, No Cry (Live)", "Live!", "Reggae", 270},
            {"One Love / People Get Ready", "Exodus", "Reggae", 175},
            {"La Vida Es Un Carnaval", "Para Siempre", "Salsa", 275},
            {"Gasolina", "Barrio Fino", "Reggaetón", 195},
            {"Despacito", "Vida", "Reggaetón / Pop latino", 229},
            {"Hips Don’t Lie", "Oral Fixation, Vol. 2", "Pop latino", 218},
            {"Smooth", "Supernatural", "Rock latino", 295},
            {"Viva La Vida", "Viva la Vida or Death and All His Friends", "Alternative Rock", 242},
            {"Seven Nation Army", "Elephant", "Rock", 231},
            {"Get Lucky", "Random Access Memories", "Disco / Funk", 248}
        };

        for (Object[] row : data) {
            String title = (String) row[0];
            String albumTitle = (String) row[1];
            String genre = (String) row[2];
            int duration = (int) row[3];

            Track t = new Track(title, genre, duration, albumTitle);
            t.setId(counter++);
            t.setArtists(new ArrayList<>());
            tracks.add(t);
        }
    }

    @Override
    public List<Track> findAll() {
        return tracks;
    }

    @Override
    public void save(Track track) {
        if (tracks == null) init();
        track.setId(counter++);
        if (track.getArtists() == null) track.setArtists(new ArrayList<>());
        tracks.add(track);
    }

    @Override
    public boolean delete(Integer id) {
        if (tracks == null) return false;

        Track target = tracks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (target == null) return false;

        if (target.getArtists() != null) {
            for (var a : target.getArtists()) {
                if (a.getTracks() != null) {
                    a.getTracks().removeIf(tr -> tr.getId().equals(id));
                }
            }
        }

        return tracks.removeIf(t -> t.getId().equals(id));
    }

    @Override
    public Optional<Track> findById(Integer id) {
        return tracks.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

}