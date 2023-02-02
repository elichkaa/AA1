import models.Session;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        if (args != null && args.length != 0){
            System.out.println("Arguments not allowed");
        }
        Session session = new Session();
        session.init();
    }
}
