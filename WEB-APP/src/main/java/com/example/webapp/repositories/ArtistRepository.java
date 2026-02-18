package com.example.webapp.repositories;

import com.example.webapp.model.Artist;
import com.example.webapp.model.Track;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ArtistRepository {
    private List<Artist> artists;
    private long currentId;
    private Gson gson;
    private final String FILE =
            "C:/Users/mjbwr/OneDrive/Escritorio/COMPUII-T1/WEB-APP/src/main/webapp/data/artists.json";

    public ArtistRepository(){
        gson = new Gson();
        loadFromFile();
    }

    private void writeToFile(){
        try (Writer writer = new FileWriter(FILE)) {
            System.out.println("Saving in: " + new File(FILE).getAbsolutePath());
            gson.toJson(artists, writer);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadFromFile(){
        try (Reader reader = new FileReader(FILE)) {
            Artist[] arr = gson.fromJson(reader, Artist[].class);
            if(arr != null){
                artists = new ArrayList<>(Arrays.asList(arr));
                currentId = 0;
                for(Artist a: artists){
                    if(a.getId()>currentId){
                        currentId=a.getId();
                    }
                }
            }
        } catch (IOException e){
            artists = new ArrayList<>();
        }
    }

    public void save(Artist artist){
        currentId++;
        artist.setId(currentId);
        artists.add(artist);
        writeToFile();

    }

    public Artist findById(long id){
        return artists.stream().filter(artist -> artist.getId()==id).findFirst().orElse(null);
    }

    public void delete(long id){
        Artist a = artists.stream().filter(artist -> artist.getId()==id).findFirst().orElse(null);
        artists.remove(a);
        writeToFile();
    }

    public List<Artist> findAll(){
        return artists;
    }

    public Artist findByName(String name){
        return artists.stream().filter(artist -> artist.getName().equals(name)).findFirst().orElse(null);
    }

}
