package org.example;

import client.Client;
import client.ClientRepo;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.database.base.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static connection.Database.*;

public class Launcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);

    public static void main(String[] args) {
        Flyway flyway = Flyway
                .configure()
                .dataSource(DB_URL, DB_USER, DB_PASSWORD)
                .load();

        flyway.migrate();
        ClientRepo clientrepo = new ClientRepo();
        LOGGER.info(String.valueOf(clientrepo.create("Lina")));
        LOGGER.info(clientrepo.getById(5));
        clientrepo.setName(1, "Evgeniy");
        clientrepo.deleteById(7);
        for (Client client : clientrepo.listAll()) {
            LOGGER.info(client.toString());
        }

    }
}
