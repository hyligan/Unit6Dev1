package connection;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Database {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/task";
    public static final String DB_USER = "myuser";
    public static final String DB_PASSWORD= "mysecretpassword";

    private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);
    private static Database instance;
    private static Connection CONNECTION;

    private Database() {
        BasicConfigurator.configure();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Don`t have a db driver!", e);
        }
        try {
            CONNECTION = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            LOGGER.info("Connection is successful");
        } catch (SQLException e) {
            LOGGER.error("Connection problem..", e);
        }
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public static Connection getConnection() {
        return CONNECTION;
    }

}
