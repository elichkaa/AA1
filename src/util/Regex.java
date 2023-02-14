package util;

public enum Regex {
    PLAYER_NAME("[A-Za-z]+"),
    COUNT_AND_GOLD("^0*[1-9]\\d*$");

    private final String value;

    Regex(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }
}
