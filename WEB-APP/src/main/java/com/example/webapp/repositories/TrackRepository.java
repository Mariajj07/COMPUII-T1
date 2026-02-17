package com.example.webapp.repositories;

import com.example.webapp.model.Artist;
import com.example.webapp.model.Track;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrackRepository {
    private List<Track> tracks;
    private long currentId;
    private Gson gson;
    private final String FILE =
            "C:/Users/mjbwr/OneDrive/Escritorio/COMPUII-T1/WEB-APP/src/main/webapp/data/tracks.json";

    public TrackRepository(){
        gson = new Gson();
        loadFromFile();
    }

    private void writeToFile(){
        try (Writer writer = new FileWriter(FILE)) {
            gson.toJson(tracks, writer);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadFromFile(){
        try (Reader reader = new FileReader(FILE)) {
            Track[] arr = gson.fromJson(reader, Track[].class);
            if(arr != null){
                tracks = new ArrayList<>(Arrays.asList(arr));
                currentId = 0;
                for(Track t: tracks){
                    if(t.getId()>currentId){
                        currentId=t.getId();
                    }
                }
            }
        } catch (IOException e){
            tracks = new ArrayList<>();
        }
    }

    public List<Track> findAll(){
        return tracks;
    }

    public void save(Track track){
        currentId++;
        track.setId(currentId);
        tracks.add(track);
        writeToFile();
    }

    public void delete(long id){
        Track t = tracks.stream().filter(track -> track.getId()==id).findFirst().orElse(null);
        tracks.remove(t);
        writeToFile();

    }

    public Track findById(long id){
        return tracks.stream().filter(track -> track.getId()==id).findFirst().orElse(null);
    }
}
