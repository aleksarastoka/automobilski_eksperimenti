package rs.raf.bp.automobili;

public class Launcher {

    private static Launcher launcher;

    public static Launcher getLauncher() {
        if (launcher == null) {
            launcher = new Launcher();
        }
        return launcher;
    }

    private Launcher() {
    }

    public void launch(String... args) {
        setUp(args);
        App.launch(App.class, args);
        clean();
    }

    private void setUp(String... args) {
        String cfgFile = "database.cfg";
        if (args != null && args.length > 0) {
            cfgFile = args[0];
        }

        Config.loadProperties(cfgFile);
        String host = Config.getPropertyValue("host", "localhost");
        String port = Config.getPropertyValue("port", "3306");
        String db = Config.getPropertyValue("db", "automobilski_eksperimenti");
        String user = Config.getPropertyValue("user", "root");
        String password = Config.getPropertyValue("password", "root");

        Config.connect(host, port, db, user, password);
    }

    private void clean() {
        Config.disconnect();
    }
}
