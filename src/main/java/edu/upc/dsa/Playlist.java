package edu.upc.dsa;

import java.util.LinkedList;
import java.util.List;

public class Playlist {
    private String id;
    private String name;
    public List<Track> tracks;

    public Playlist(String id, String name){
        this.id = id;
        this.name = name;
        tracks = new LinkedList<Track>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
