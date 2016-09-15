package me.mojaaplikacija;

import java.io.IOException;

import static java.lang.Boolean.FALSE;

/**
 * Created by Vid on 15.9.2016.
 */
public class DBHandler {

    me.mojaaplikacija.Data db;
    private boolean db_exists;
    private String [] data;
    private  DataInfo readData;

    public DBHandler(String name) {

        try {
            db = new me.mojaaplikacija.Data(name);
        } catch (IOException e) {
            e.printStackTrace();
            db_exists = FALSE;
        }

        if (db_exists == FALSE) {
            try {
                FieldInfo [] fi = Post.Init();
                db = new Data(name, fi);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void SaveToDB(Post post) {
        try {
            String [] title = post.toStringArray();
            data = title;
            db.add(data);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public String ReadFromDB (int id) {
        try {
            readData = db.getRecord(id);

        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return readData.toString();
    }
}
