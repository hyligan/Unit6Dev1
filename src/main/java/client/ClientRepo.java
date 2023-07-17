package client;


import connection.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepo {

    private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);

    public Long create(String name) {
        Long id = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO client (name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            id = generatedKeys.getLong(1);
        } catch (SQLException e) {
            LOGGER.error("Error = ", e);
        }
        return id;
    }

    public String getById(long id) {
        String name = "";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT name FROM client WHERE client_id = ?");
            preparedStatement.setInt(1, (int) id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            name = resultSet.getString("name");
        } catch (SQLException e) {
            LOGGER.error("Error = ", e);
        }
        return name;
    }

    public void setName(long id, String name) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("UPDATE client SET name = ? WHERE client_id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, (int) id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error = ", e);
        }
    }

    public void deleteById(long id) {
        try {
            PreparedStatement deleteUser = getConnection().prepareStatement("DELETE FROM client WHERE client_id = ?");
            deleteUser.setInt(1, (int) id);
            deleteUser.execute();
        } catch (SQLException e) {
            LOGGER.error("Error = ", e);
        }
    }

    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM client ORDER BY client_id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(new Client((long) resultSet.getInt("client_id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            LOGGER.error("Error = ", e);
        }
        return clients;
    }

    private static Connection getConnection() {
        return Database.getInstance().getConnection();
    }
}
