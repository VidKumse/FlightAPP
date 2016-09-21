package me.mojaaplikacija;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import static java.lang.Boolean.FALSE;
import static spark.route.HttpMethod.post;

/**
 * Created by Vid on 16.9.2016.
 */
public class PostDAOImpl implements PostDAO {

    me.mojaaplikacija.Data db;
    private boolean db_exists;
    private String [] data;
    private DataInfo readData;
    private FieldInfo [] fieldInfoArray = null;
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.fieldInfoArray = fi;
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
            if(readData == null) {
                return null;
            }
            valuesArray = readData.getValues();
            //tale del potrebuje še neko posplošitev
            post = new Post(id, valuesArray[0], valuesArray[1]);


        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return post;
    }

    // TOLE JE ORIGINAL FUNKCIJA, KI JI PODAMO OBJEKT
    /*@Override
    public void createPost(Post post) {
        try {
            String [] data = post.toStringArray();
            db.add(data);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }*/

    //TOLE JE DRUGA FUNKCIJA, KI JI PODAMO STRINGE
    @Override
    public Post createPost(String title, String content) {
        try {
            String [] data = {title, content};
            db.add(data);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        Post post = new Post(title, content);
        return post;
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> list_of_posts = new ArrayList<>();
        Post post;
        int recordCount=db.getRecordCount();;
        for(int i=1; i<=recordCount; i++) {
                post=getPost(i);
                if(post != null) {
                    list_of_posts.add(post);
                }
        }
        return list_of_posts;
    }

    // TOLE JE ORIGINAL FUNKCIJA, KI JI PODAMO OBJEKT
    /*@Override
    public void update(Post post) {
        int id = post.getId();
        String title = post.getTitle();
        String content = post.getContent();
        String [] fieldValues = {title, content};

        DataInfo datainfo = new DataInfo(id, fieldInfoArray, fieldValues);
        try {
            db.modify(datainfo);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }*/

    //TOLE JE DRUGA FUNKCIJA, KI JI PODAMO STRINGE
    @Override
    public Post update(int id, String title, String content) {
        String [] fieldValues = {title, content};

        DataInfo datainfo = new DataInfo(id, fieldInfoArray, fieldValues);
        try {
            db.modify(datainfo);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        Post post = new Post(id, title, content);
        return post;
    }

}
