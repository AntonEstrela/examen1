package edu.upc.dsa;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.*;

public class MyMusicImpl implements MyMusic {
    private static MyMusic instance;

    private HashMap<String, User> users;
    private List<Singer> singers;

    static Logger logger = Logger.getLogger(MyMusicImpl.class);

    private MyMusicImpl(){
        users = new HashMap<String, User>();
        singers = new LinkedList<Singer>();
    }

    public static MyMusic getInstance() {
        if(instance == null){
            instance = new MyMusicImpl();
        }
        return instance;
    }

    public void addSinger(String id, String name) {

        singers.add(new Singer(id, name));
        logger.info("Singer added. id: " + id);
    }

    public List<Singer> listSingers() {
        logger.info("Singer list returned");
        return singers;
    }

    public void addUser(String id, String name) {
        logger.info("User added. id: " + id);
        users.put(id, new User(id, name));
    }

    public void addPlaylist(String playlistId, String name, String userId) throws UserNotFoundException{
        User u = users.get(userId);
        if(u == null){
            logger.error("User not found. UserId: " + userId);
            throw new UserNotFoundException();
        }
        u.addPlaylist(playlistId, name);
        logger.info("Playlist added. id: " + playlistId);

    }

    public void addTrack(String id, String title, String singerId, String album, int length, String playlistId, String userId) throws UserNotFoundException, PlaylistNotFoundException {
        User u = users.get(userId);
        if(u == null){
            logger.error("User not found. UserId: " + userId);
            throw new UserNotFoundException();
        }
        logger.info("Getting playlist " + playlistId + "...");
        Playlist p = u.getPlaylist(playlistId);
        p.tracks.add(new Track(id, title, singerId, album, length));
        logger.info("Track added. idTrack: " + id);
    }

    public List<Track> listTracks(String userId, String playlistId) throws UserNotFoundException, PlaylistNotFoundException {
        User u = users.get(userId);
        if(u == null){
            logger.error("User not found. UserId: " + userId);
            throw new UserNotFoundException();
        }
        logger.info("Getting playlist " + playlistId + "...");
        Playlist p = u.getPlaylist(playlistId);
        logger.info("Playlist found");
        return p.tracks;
    }

    @Override
    public void clear() {
        users.clear();
        singers.clear();
    }
}
