package me.mojaaplikacija;

import java.io.IOException;
import java.util.List;

import static java.lang.Boolean.FALSE;

/**
 * Created by Vid on 16.9.2016.
 */
public class PostDAOImpl implements PostDAO {

    me.mojaaplikacija.Data db;
    private boolean db_exists;
    private String [] data;
    private  DataInfo readData;
    private FieldInfo [] fieldInfoArray;
    private String [] valuesArray;

    public PostDAOImpl(String name, FieldInfo [] fi){

        try {
            db = new me.mojaaplikacija.Data(name);
        } catch (IOException e) {
            e.printStackTrace();
            db_exists = FALSE;
        }

        if (db_exists == FALSE) {
            try {
                db = new Data(name, fi);
                this.fieldInfoArray = fi;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deletePost(int id) {
        DataInfo data_to_delete = null;
        try {
            data_to_delete = db.getRecord(id);
            db.delete(data_to_delete);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    //retrive list of students from the database

    @Override
    public Post getPost(int id) {
        Post post = null;
        try {
            readData = db.getRecord(id);
            valuesArray = readData.getValues();

            //tale del potrebuje še neko posplošitev
            post = new Post(valuesArray[0], valuesArray[1]);


        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void createPost(Post post) {
        try {
            String [] data = post.toStringArray();
            db.add(data);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

}
