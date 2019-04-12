package edu.upc.dsa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private List<Playlist> playlists;


    public User(String id, String name){
        this.id = id;
        this.name = name;
        playlists = new ArrayList<Playlist>();
    }

    public void addPlaylist(String id, String name){
        playlists.add(new Playlist(id, name));
    }

    public Playlist getPlaylist(String id) throws PlaylistNotFoundException{
        for (Playlist p : playlists){
            if (p.getId().equals(id)){
                return p;
            }
        }
        throw new PlaylistNotFoundException();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
