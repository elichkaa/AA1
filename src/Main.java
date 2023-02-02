import models.Session;

public final class Main {
    private Main() {
        throw new IllegalStateException("Utility class cannot be instantiated.");
    }

    public static void main(final String[] args) {
        if (args != null && args.length != 0){
            System.out.println("Arguments not allowed");
        }
        Session session = new Session();
        session.init();
    }
}
