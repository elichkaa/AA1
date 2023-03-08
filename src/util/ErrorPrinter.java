package util;

public final class ErrorPrinter {
    private ErrorPrinter() {
    }

    public static void print(String message, Object... optionalParameters) {
        if (message.length() != 0) {
            System.err.printf(message + System.lineSeparator(), optionalParameters);
        } else {
            System.err.printf(message + System.lineSeparator());
        }
    }
}
