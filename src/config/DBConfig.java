package config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream("resources/config.properties")) {
            properties.load(input);
        } catch (Exception e) {
            System.out.println("Could not load config.properties. Falling back to defaults.");
            properties.setProperty("db.url", "jdbc:mysql://localhost:3306/smart_banking");
            properties.setProperty("db.user", "root");
            properties.setProperty("db.password", "root");
        }
    }

    public static String get(String key) { return properties.getProperty(key); }
}
