package edu.upc.dsa;

import java.util.List;

public interface MyMusic {
    //public MyMusic getInstance();
    public void addSinger(String id, String name);
    public List<Singer> listSingers();
    public void addUser(String id, String name);
    public void addPlaylist(String playlistId, String name, String userId) throws UserNotFoundException;
    public void addTrack(String id, String title, String singerId, String album, int length, String playlistId, String userId) throws UserNotFoundException, PlaylistNotFoundException;
    public List<Track> listTracks(String userId, String playlistId) throws UserNotFoundException, PlaylistNotFoundException;
    public void clear();
}
