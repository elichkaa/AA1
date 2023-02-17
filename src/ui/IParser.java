package ui;

public interface IParser<T> {
    Object getCorrectInputIfAvailable(String pattern, String errorMessage, String question, boolean needsParsing);

    T parse();
    void addObserver(StateObserver observer);
}
