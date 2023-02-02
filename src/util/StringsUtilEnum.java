package util;

public enum StringsUtilEnum {
    WHITESPACE_STRING(" ");

    private final String value;

    StringsUtilEnum(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }
}
