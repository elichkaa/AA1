package util;

public enum Regex {
    PLAYER_NAME("[A-Za-z]+"),
    SHOW_COMMAND_ARGS("(barn|market|board)"),
    SELL_SPECIFIC_COMMAND_ARGS("\\b(tomato|mushroom|salad|carrot|all)\\b"),
    VEGETABLE_NAME_ARGS("\\b(tomato|mushroom|salad|carrot)\\b"),
    SELL_ALL_COMMAND_ARGS("all"),
    BUY_LAND_COMMAND("land"),
    BUY_VEGETABLE_COMMAND("vegetable"),
    QUIT("quit"),
    COORDINATES("^0*-?[0-9]\\d*$"),
    WHOLE_NUMBER("^0*[0-9]\\d*$"),
    WHOLE_POSITIVE_NUMBER("^0*[1-9]\\d*$");

    private final String value;

    Regex(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
