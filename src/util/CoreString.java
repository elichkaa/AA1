package util;

public enum CoreString {
    WHITESPACE_STRING(" "),
    SEMICOLON_STRING(";"),
    COLON_STRING(":");

    private final String value;

    CoreString(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }
}
