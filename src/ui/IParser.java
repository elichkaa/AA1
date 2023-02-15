package ui;

import models.Session;
import util.StateObserver;

import java.util.Scanner;

public interface IParser<T> {
    Object checkInputCorrectness(String pattern, String errorMessage, String question);
    T parse();
    void addObserver(StateObserver observer);
}
