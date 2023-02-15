package util;

public enum Communication {
    SESSION_TERMINATED_MESSAGE("Session terminated"),
    PLAYER_COUNT_QUESTION("How many players?"),
    INITIAL_GOLD_QUESTION("With how much gold should each player start?"),
    WINNING_GOLD_QUESTION("With how much gold should a player win?"),
    PLAYER_NAME_PROMPT("Enter the name of player ");

    private final String value;

    Communication(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }
}
