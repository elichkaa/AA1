package util;

public enum Regex {
    PLAYER_NAME("[A-Za-z]+");

    private final String value;

    Regex(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }
}
