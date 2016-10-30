package app.mallusolutions.myxmlparser.com;

/**
 * Created by Phantom on 30-10-2016.
 */

public final class songs {
    public final Integer id;
    public final String title;
    public final String artist;
    public final String URL;

    public songs(Integer id,String title, String artist, String URL){
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.URL = URL;
    }
}
