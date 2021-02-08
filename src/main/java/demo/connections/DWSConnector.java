package demo.connections;

import demo.config.ConfigMapping;
import org.aeonbits.owner.ConfigCache;

import java.sql.Connection;
import java.sql.SQLException;


public final class DWSConnector extends DBConnector {

    private static DWSConnector instance;
    private static Connection connection;

    private DWSConnector() {
    }

    private Boolean isConnected(Connection connection) throws SQLException {
        return !(connection == null || connection.isClosed());
    }

    public Connection createConnection() throws SQLException {
        if (!isConnected(connection)) {
            ConfigMapping cfg = ConfigCache.getOrCreate(ConfigMapping.class, System.getProperties());
            connection = createConnection(generateURL(cfg.postgresqlHost(), cfg.psqlPort(),
                    cfg.psqlDBName(), "jdbc:postgresql"),
                    cfg.psqlUsername(), cfg.psqlPassword());
        }
        return connection;
    }

    public static DWSConnector getInstance() {
        if (instance == null) instance = new DWSConnector();
        return instance;
    }

    public void closeConnection() throws SQLException {
        if (isConnected(connection)) {
            connection.close();
        }
    }
}
