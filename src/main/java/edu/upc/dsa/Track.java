package edu.upc.dsa;

public class Track {

    private String id;
    private String title;
    private String singerId;
    private String album;
    private int length;

    public Track(String id, String title, String singerId, String album, int length) {
        this.id = id;
        this.title = title;
        this.singerId = singerId;
        this.album = album;
        this.length = length;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingerId() {
        return singerId;
    }

    public int getLength() {
        return length;
    }

    public String getAlbum() {
        return album;
    }

    @Override
    public String toString() {
        return "Track [id="+id+", title=" + title + ", singer=" + singerId + ", album=" + album + ", length=" + length +"]";
    }

}