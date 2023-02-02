package models;

public class CustomException extends IllegalArgumentException {
    public CustomException(final String message){
        super(message);
    }
}
