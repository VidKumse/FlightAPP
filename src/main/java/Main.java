//package me.mojaaplikacija;

import static spark.Spark.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import me.mojaaplikacija.*;


import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
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
        FieldInfo f1 = new FieldInfo("Flight number", 6);
        FieldInfo f2 = new FieldInfo("Origin airport", 4);
        FieldInfo f3 = new FieldInfo("Destination airport", 4);
        FieldInfo f4 = new FieldInfo("Carrier", 15);
        FieldInfo f5 = new FieldInfo("Price", 6);
        FieldInfo f6 = new FieldInfo("Day", 3);
        FieldInfo f7 = new FieldInfo("Time", 5);
        FieldInfo f8 = new FieldInfo("Duration",  8);
        FieldInfo f9 = new FieldInfo("Available seats", 4);
        FieldInfo [] fi = new FieldInfo[]{f1, f2, f3, f4, f5, f6, f7 ,f8, f9};



        //Post post = new Post(1, "Cetrti", "Cetrti");

        //zakvaj

        //Ta del imam narejen drugače kot v tutorialu, a je to ok??
        PostDAO postDAO = new PostDAOImpl(dbname, fi);
        PostController postController = new PostController(postDAO);
        System.out.println(postController);

        /*HashMap<String, String> p = new HashMap<String, String>();
        p.put("Day", "f");
        postDAO.searchPost(p);*/
        //Tako je v tutorialu!!
        //new PostController(new PostDAOImpl(dbname, fi));

        /*postDAO.createPost(
                "SA001",  //flight number
                "SFO",  //origin airport
                "DEN",  //destination airport
                "SpeedyAir",  //carrier
                "400",   //price
                "Sun",   //day
                "13:40",   //time
                "20m",   //duration
                "50"   //available seats
                );*/
        /*for (Post post_count : postDAO.getAllPosts()) {
            System.out.println("Title: "+post_count.getTitle()+" Content: "+post_count.getContent());
        }*/
    }
}





