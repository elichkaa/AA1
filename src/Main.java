import models.Session;
import util.ErrorMessage;

public final class Main {
    private Main() {
        throw new IllegalStateException(ErrorMessage.UTILITY_CLASS_FALSE_INSTANTIATION.toString());
    }

    public static void main(final String[] args) {
        if (args != null && args.length != 0){
            System.out.println(ErrorMessage.COMMANDLINE_ARGUMENTS_NOT_ALLOWED);
            return;
        }
        Session session = new Session();
        session.init();
    }
}
