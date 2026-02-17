package com.example.webapp.services;

import com.example.webapp.model.Artist;
import com.example.webapp.model.Track;
import com.example.webapp.repositories.ArtistRepository;
import com.example.webapp.repositories.TrackRepository;

import java.util.ArrayList;
import java.util.List;

public class ArtistService {
    private ArtistRepository artistRepository;
    private TrackRepository trackRepository;

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
        List<Artist> artists = artistRepository.findAll();
        List<Track> tracks = trackRepository.findAll();

        for (Artist artist : artists) {
            List<Track> artistTracks = new ArrayList<>();
            for (Track t : tracks) {
                if (t.getArtistId() == artist.getId()) {
                    artistTracks.add(t);
                }
            }
            artist.setTracks(artistTracks);
        }


        return artists;
    }

    public Artist findByName(String name){
        return artistRepository.findByName(name);
    }

}
