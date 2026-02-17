package com.example.webapp.services;

import com.example.webapp.model.Artist;
import com.example.webapp.model.Track;
import com.example.webapp.repositories.ArtistRepository;
import com.example.webapp.repositories.TrackRepository;

import java.util.List;

public class TrackService {
    private TrackRepository trackRepository;
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

        track.getArtists().add(artist);
        artist.getTracks().add(track);
        trackRepository.save(track);

    }

    public List<Track> findAll(){
        return trackRepository.findAll();
    }

    public void removeTrack(long id){
        trackRepository.delete(id);
    }

}
