

import static spark.Spark.get;
import static spark.Spark.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import me.mojaaplikacija.FieldInfo;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

public class Main {

    private static final int HTTP_BAD_REQUEST = 400;

    interface Validable {
        boolean isValid();
    }

    @Data
    static class Template{
        public String title;
        public List categories = new LinkedList<>();
        public String content;
    }



    static class NewPostPayload extends Template {

        public boolean isValid() {
            return title != null && !title.isEmpty() && !categories.isEmpty();
        }
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

    private FieldInfo f1 = new FieldInfo("flight number", 13);
    private FieldInfo f2 = new FieldInfo("Origin airport", 14);
    private FieldInfo f3 = new FieldInfo("Destination airport", 19);
    private FieldInfo[] fieldinfo = {f1, f2, f3};

    public static void main( String[] args) {
        FieldInfo [] fi = new FieldInfo[1];
        fi[0] = new FieldInfo("test", 10);

        try {
            me.mojaaplikacija.Data db = new me.mojaaplikacija.Data("C:\\Users\\vid\\test.txt", fi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





