import models.Session;
import util.ErrorPrinter;

public final class Main {
    private static final String UTILITY_CLASS_FALSE_INSTANTIATION = "Error: You cannot instantiate a utility class.";
    private static final String COMMANDLINE_ARGUMENTS_NOT_ALLOWED = "No command line arguments allowed. Restart the program without them.";

    private Main() {
        throw new IllegalStateException(UTILITY_CLASS_FALSE_INSTANTIATION);
    }

    public static void main(final String[] args) {
        if (args != null && args.length != 0) {
            ErrorPrinter.print(COMMANDLINE_ARGUMENTS_NOT_ALLOWED);
            return;
        }
        Session session = new Session();
        session.init();
    }
}
