package edu.upc.dsa;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MyMusicTest {
    private MyMusic mm;

    @Before
    public void SetUp() throws Exception {
        mm = MyMusicImpl.getInstance();

        mm.addUser("u1", "Antonio");
        mm.addPlaylist("p1", "lista1", "u1");
    }

    @Test
    public void testAddTrack() throws Exception {
        mm.addTrack("t1", "Inframan", "s1", "album1", 200, "p1", "u1");
        Assert.assertEquals(1, mm.listTracks("u1", "p1").size());

        mm.addTrack("t2", "Bomba", "s2", "album1", 210, "p1", "u1");
        Assert.assertEquals(2, mm.listTracks("u1", "p1").size());
    }

    @Test (expected = UserNotFoundException.class)
    public void testUserNotFoundException() throws Exception{
        mm.listTracks("qwerty", "qwerty");
    }

    @Test (expected = PlaylistNotFoundException.class)
    public void testPlaylistNotFoundException() throws Exception{
        mm.listTracks("u1", "qwerty");
    }

    @After
    public void tearDown(){
        mm.clear();
    }

}
