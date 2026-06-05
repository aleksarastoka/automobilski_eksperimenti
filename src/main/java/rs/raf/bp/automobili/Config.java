package rs.raf.bp.automobili;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Config {

    private static Properties properties;
    private static Connection connection;

    public static void loadProperties(String cfgFile) {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(cfgFile)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPropertyValue(String property, String defaultValue) {
        return properties.getProperty(property, defaultValue);
    }

    public static void connect(String host, String port, String db, String user, String password) {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void disconnect() {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Config() {
    }
}
