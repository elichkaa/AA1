package util;

public enum ErrorMessage {
    UTILITY_CLASS_FALSE_INSTANTIATION("You cannot instantiate a utility class."),
    COMMANDLINE_ARGUMENTS_NOT_ALLOWED("No command line arguments allowed. Restart the program without them."),
    PIXEL_ART_FILE_DOES_NOT_EXIST("The file with the pixel art appears to be deleted. Restore the file.");

    private final String value;
    private final static String PREFIX = "Error: ";

    ErrorMessage(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return PREFIX + this.value;
    }
}
