package me.mojaaplikacija;

/**
 * Created by Vid on 20.9.2016.
 */
public class ResponseError {
    private String message;

    public ResponseError(String message, String... args) {
        this.message = String.format(message, args);
    }

    public ResponseError(Exception e) {
        this.message = e.getMessage();
    }

    public String getMessage() {
        return this.message;
    }
}
