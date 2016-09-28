package me.mojaaplikacija;

import java.util.ArrayList;
import java.util.HashMap;

public class Post {
    private HashMap<String, String> params;
    private int id;
    private String value;

    public Post(HashMap<String,  String> p) {
        this.params = p;
    }

    public Post(int id, HashMap<String, String> p) {
        //podvajanje konstruktorjev!!!
        this.params = p;
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String get(String param) {
        for ( String key : params.keySet() ) {
            System.out.println( key );
            value = params.get(key);
        }
        return value;
    }

    public void set(String param, String value) {
        params.put(param, value);
    }

    /*public boolean isValid() {
        return title != null && !title.isEmpty();
    }*/

    public String [] toStringArray() {
        String[] data = new String[params.size()];
        int i=0;
        for ( String key : params.keySet() ) {
            i++;
            data[i] = params.get(value);
        }

        return data;
    }

    public String toString() {
        String text = "";
        for ( String key : params.keySet() ) {
            text = text + params.get(value) + " ";
        }
        return text;
    }
}