package com.saiproject.jsonp1.Model;

/**
 *
 * Class containing information about one item from the artist from the URL
 *
 *  Contains type,kind,artistName,collectionName,trackName,artistViewURL
 *
 */
public class iTunesStuff {


    //Needs to be private to set getters and setters
    private String type;                                          //song,podcasts,movie,book etc
    private String kind;                                            //rock,pop,rap
    private String artistName;
    private String collectionName;
    private String trackName;
    private String artistViewURL;                                           // URL of the artist album image


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistViewURL() {
        return artistViewURL;
    }

    public void setArtistViewURL(String artistViewURL) {
        this.artistViewURL = artistViewURL;
    }
}
