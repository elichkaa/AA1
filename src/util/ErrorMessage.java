package util;

public enum ErrorMessage {
    UTILITY_CLASS_FALSE_INSTANTIATION("You cannot instantiate a utility class."),
    COMMANDLINE_ARGUMENTS_NOT_ALLOWED("No command line arguments allowed. Restart the program without them."),
    PIXEL_ART_FILE_DOES_NOT_EXIST("The file with the pixel art appears to be deleted. Restore the file."),
    PLAYER_COUNT_INVALID("Player count is invalid. Please enter a positive whole number."),
    INITIAL_GOLD_QUANTITY_INVALID("The initial gold quantity is invalid. Please enter a positive whole number."),
    WINNING_GOLD_QUANTITY_INVALID("The winning gold quantity is invalid. Please enter a positive whole number."),
    INVALID_ARGUMENT_NAME("An argument for the command %s with the name %s doesn't exist."),
    INVALID_SALE_ARGUMENTS("An argument for the command %s with the name %s doesn't exist or the argument all was entered alongside other arguments."),
    NO_ARGUMENTS_PROVIDED("Please provide arguments for the command %s."),
    TOO_MANY_ARGUMENTS_PROVIDED("Too many arguments for the command %s provided. They should be maximum %d."),
    NOT_ENOUGH_ARGUMENTS_PROVIDED("Not enough arguments for the command %s provided. They should be minimum %d."),
    SECOND_COORDINATE_MISSING("The %s command is missing it's second coordinate."),
    INVALID_VEGETABLE_NAME("Invalid vegetable name for %s command. A vegetable with the name %s doesn't exist."),
    INVALID_COORDINATES("Please enter correct integers for the coordinates."),
    INVALID_VEGETABLE_QUANTITY("Please enter a positive or zero amount of vegetables."),
    NO_ADDITIONAL_PARAMETERS_REQUIRED("The command %s and the argument %s do not require additional parameters."),
    PLAYER_NAME_INVALID("Name of the player is invalid. Please only include letters.");

    private final String value;
    private final static String PREFIX = "Error: ";

    ErrorMessage(String value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return PREFIX + this.value;
    }
}
