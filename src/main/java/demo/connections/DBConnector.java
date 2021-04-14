package demo.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {

    private static Properties prop = new Properties();

    public static Connection createConnection(String url, String username, String password) throws SQLException {

        return DriverManager.getConnection(url, username, password);
    }

    public static String generateURL(String host, int port, String dbName, String dbType) {
        return dbType + "://" + host + ":" + port + "/" + dbName + "?useSSL=" + dbType;
    }
}
