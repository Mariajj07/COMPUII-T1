package com.example.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Artist {
    private long id;
    private String name;
    private String nationality;
    private List<Track> tracks;

    public Artist(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
        this.tracks = new ArrayList<>();
    }

    public Artist(){ this.tracks = new ArrayList<>(); }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
