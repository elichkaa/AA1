package util;

public enum ErrorMessage {
    UTILITY_CLASS_FALSE_INSTANTIATION("You cannot instantiate a utility class."),
    COMMANDLINE_ARGUMENTS_NOT_ALLOWED("No command line arguments allowed. Restart the program without them."),
    PIXEL_ART_FILE_DOES_NOT_EXIST("The file with the pixel art appears to be deleted. Restore the file."),
    PLAYER_COUNT_INVALID("Player count is invalid. Please enter a positive whole number."),
    INITIAL_GOLD_QUANTITY_INVALID("The initial gold quantity is invalid. Please enter a positive whole number."),
    WINNING_GOLD_QUANTITY_INVALID("The winning gold quantity is invalid. Please enter a positive whole number."),
    INVALID_ARGUMENT_NAME("An argument for the command %s with the name %s doesn't exist."),
    NO_ARGUMENTS_PROVIDED("Please provide arguments for the command %s."),
    TOO_MANY_ARGUMENTS_PROVIDED("Please provide the correct amount of arguments for the command %s. They should be %d."),
    PLAYER_NAME_INVALID("Name of the player is invalid. Please only include letters.");

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
