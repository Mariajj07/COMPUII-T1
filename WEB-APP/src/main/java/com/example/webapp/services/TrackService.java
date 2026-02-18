package com.example.webapp.services;

import com.example.webapp.model.Artist;
import com.example.webapp.model.Track;
import com.example.webapp.repositories.ArtistRepository;
import com.example.webapp.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

 @Component
public class TrackService {

     @Autowired
    private TrackRepository trackRepository;

     @Autowired
    private ArtistRepository artistRepository;

    public void setTrackRepository(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void addTrack(String title, String genre, double duration, String album, long idA){
        Track track = new Track();
        track.setTitle(title);
        track.setGenre(genre);
        track.setDuration(duration);
        track.setAlbumTitle(album);

        Artist artist = artistRepository.findById(idA);

        addTrackToArtist(idA, track.getId());

    }

    public void addTrackToArtist(long idA, long idT){
        Artist artist =artistRepository.findById(idA);
        Track track = trackRepository.findById(idT);

        artist.getTracks().add(track);
        track.getArtists().add(artist);
    }

    public List<Track> findAll(){
        return trackRepository.findAll();
    }

    public void removeTrack(long id){
        trackRepository.delete(id);
    }

}
