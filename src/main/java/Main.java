//package me.mojaaplikacija;

import static spark.Spark.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import me.mojaaplikacija.*;


import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

public class Main {

    private static final int HTTP_BAD_REQUEST = 400;

    interface Validable {
        boolean isValid();
    }



    public static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e){
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }

    public static void main( String[] args) {
        String dbname = "C:\\Users\\vid\\test.txt";
        me.mojaaplikacija.FieldInfo [] fi;
        fi = new FieldInfo[2];
        fi[0] = new FieldInfo("title", 10);
        fi[1] = new FieldInfo("content", 10);



        //Post post = new Post(1, "Cetrti", "Cetrti");

        //zakvaj

        //Ta del imam narejen drugače kot v tutorialu, a je to ok??
        PostDAO postDAO = new PostDAOImpl(dbname, fi);
        PostController postController = new PostController(postDAO);
        System.out.println(postController);

        //Tako je v tutorialu!!
        //new PostController(new PostDAOImpl(dbname, fi));

        //postDAO.update(post);
        /*for (Post post_count : postDAO.getAllPosts()) {
            System.out.println("Title: "+post_count.getTitle()+" Content: "+post_count.getContent());
        }*/
    }
}





