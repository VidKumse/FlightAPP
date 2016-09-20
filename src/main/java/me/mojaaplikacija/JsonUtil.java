package me.mojaaplikacija;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by Vid on 20.9.2016.
 */
public class JsonUtil {

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }
}
