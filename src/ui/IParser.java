package ui;

import java.util.regex.Pattern;

public interface IParser<T> {
    Object getCorrectInputIfAvailable(Pattern pattern, String errorMessage, String question, boolean needsParsing);

    T parse();

    void addObserver(StateObserver observer);
}
