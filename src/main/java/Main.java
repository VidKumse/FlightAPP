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

        //zakvaj
        Post post = new Post("Nas", "moja ob");
        PostDAO postDAO = new PostDAOImpl(dbname, fi);
        postDAO.createPost(post);
        System.out.println("blabla "+postDAO.getPost(1).toString());
    }
}





