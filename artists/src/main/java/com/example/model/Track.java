package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Track {
    
    private Integer id;
    private String title;
    private String genre;
    private Integer duration;
    private String albumTitle;
    private List<Artist> artists = new ArrayList<>();

    //Este no me convencio enotnces cree el de abajo pero pueden utilizar el que quieran
    public Track(String title, String genre, Integer duration, String albumTitle){
        this.title=title;
        this.genre=genre;
        this.duration=duration;
        this.albumTitle=albumTitle;
        this.id=-1;
    }

    

    public Track(Integer id, String title, String genre, Integer duration, String albumTitle, List<Artist> artists) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.albumTitle = albumTitle;
        this.artists = artists;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public String getAlbumTitle() {
        return albumTitle;
    }
    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
