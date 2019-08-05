package com.saiproject.jsonp1;

import com.saiproject.jsonp1.Model.iTunesStuff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonItunesParser {

    //data is the URL for ex : https://itunes.apple.com/search?term=jack+johnson

    public static iTunesStuff getItunesStuff(String data) throws  JSONException{


        iTunesStuff iTunesStuff = new iTunesStuff();
        JSONObject iTunesStuffJSONObject = new JSONObject(data);


        JSONArray resultsJsonArray = iTunesStuffJSONObject.getJSONArray("results"); //Tagname of the JSON array must be same as that when opened in  JSON Editor

        JSONObject artistObject = resultsJsonArray.getJSONObject(0);


        // getString method returns string from JSON object using tagName and JSON Object.

        iTunesStuff.setType(getString("wrapperType",artistObject)); //Extracts the type from the JSON Object
        iTunesStuff.setKind(getString("kind",artistObject)); //Extracts the kind from the JSON Object
        iTunesStuff.setArtistName(getString("artistName",artistObject)); //Extracts the name from the JSON Object
        iTunesStuff.setCollectionName(getString("collectionName",artistObject)); //Extracts collection type from the JSON Object
        iTunesStuff.setArtistViewURL(getString("artworkUrl100",artistObject)); //Extracts image type from the JSON Object
        iTunesStuff.setTrackName(getString("trackName",artistObject)); //Extracts the track from the JSON Object
        iTunesStuff.setTrackName(getString("trackName",artistObject)); //Extracts the track from the JSON Object


        return iTunesStuff;
    }



    private static JSONObject getJsonObject(String tagName,JSONObject jsonObject) throws JSONException{ //Get JSON object from the array of JSON objects

        return jsonObject.getJSONObject(tagName);
    }


    private static String  getString(String tagName, JSONObject jsonObject) throws JSONException{

        return  jsonObject.getString(tagName);
    }

    private static Float  getFloat(String tagName, JSONObject jsonObject) throws JSONException{

        return (float) jsonObject.getDouble(tagName);
    }


    private static int getInt(String tagName, JSONObject jsonObject) throws JSONException{

        return jsonObject.getInt(tagName);
    }

    private static boolean getBoolean(String tagName, JSONObject jsonObject) throws JSONException{

        return jsonObject.getBoolean(tagName);
    }







}
