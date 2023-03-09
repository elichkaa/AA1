package util;

public final class ErrorPrinter {
    private final static String ERROR_PREFIX = "Error: ";

    private ErrorPrinter() {
    }

    public static void print(String message, Object... optionalParameters) {
        if (message.length() != 0) {
            System.err.printf(ERROR_PREFIX + message + System.lineSeparator(), optionalParameters);
        } else {
            System.err.printf(ERROR_PREFIX + message + System.lineSeparator());
        }
    }
}
