package com.example.webapp.services;

import com.example.webapp.model.Artist;
import com.example.webapp.model.Track;
import com.example.webapp.repositories.ArtistRepository;
import com.example.webapp.repositories.TrackRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArtistService {
    private ArtistRepository artistRepository;
    private TrackRepository trackRepository;


    public void init() {

        List<Artist> artists = artistRepository.findAll();
        List<Track> tracks = trackRepository.findAll();
        Random r = new Random();
        for (Artist a : artists) {
            for (int i = 0; i < 10; i++) {
                Track t = tracks.get(r.nextInt(tracks.size()));
                if (!a.getTracks().contains(t)) {
                    a.getTracks().add(t);
                    t.getArtists().add(a);
                }

            }
        }
    }



    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void setTrackRepository(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public void addArtist(String name, String nationality){
        Artist artist = new Artist();
        artist.setName(name);
        artist.setNationality(nationality);
        artistRepository.save(artist);
    }

    public void deleteArtist(long id){
        artistRepository.delete(id);
    }


    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    public Artist findByName(String name){
        return artistRepository.findByName(name);
    }

}
