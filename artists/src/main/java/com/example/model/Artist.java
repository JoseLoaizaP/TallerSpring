package com.example.model;

import java.util.List;
import java.util.ArrayList;

public class Artist {

    private Integer id;
    private String name;
    private String nationality;
    private List<Track> tracks = new ArrayList<>();

    //Lo mismo que en track
    public Artist(String name, String nationality){
        this.name=name;
        this.nationality=nationality;
        this.id=-1;
    }


    public Artist(Integer id, String name, String nationality, List<Track> tracks) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.tracks = tracks;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


    public List<Track> getTracks() {
        return tracks;
    }


    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
    
}
