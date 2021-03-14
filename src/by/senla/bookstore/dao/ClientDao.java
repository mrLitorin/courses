package by.senla.bookstore.dao;

import by.senla.bookstore.model.Client;

import java.util.Objects;

public class ClientDao extends AbstractDao<Client> {
    private static final ClientDao instance = getInstance();

    private ClientDao() {
    }

    public static ClientDao getInstance() {
        return Objects.requireNonNullElse(instance, new ClientDao());
    }
}
