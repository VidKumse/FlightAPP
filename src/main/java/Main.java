//package me.mojaaplikacija;

import static spark.Spark.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import me.mojaaplikacija.DBHandler;
import me.mojaaplikacija.FieldInfo;
import me.mojaaplikacija.Post;


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

        DBHandler DB = new DBHandler(dbname);
        Post post = new Post("n3", "n4");
        DB.SaveToDB(post);
        String read = DB.ReadFromDB(4);
        System.out.println(read);
    }
}





