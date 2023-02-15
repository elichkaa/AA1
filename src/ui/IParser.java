package ui;

public interface IParser<T> {
    Object checkInputCorrectness(String pattern, String errorMessage, String question);
    T parse();
    void addObserver(StateObserver observer);
}
