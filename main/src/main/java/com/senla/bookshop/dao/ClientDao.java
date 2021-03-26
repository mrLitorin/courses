package com.senla.bookshop.dao;

import com.senla.bookshop.model.Client;
import exception.DaoException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientDao extends AbstractDao<Client> {
    private static final Logger LOGGER = Logger.getLogger(ClientDao.class.getSimpleName());
    private static final ClientDao instance = getInstance();
    private final List<Client> clients = new ArrayList<>();

    private ClientDao() {
    }

    public static ClientDao getInstance() {
        return Objects.requireNonNullElse(instance, new ClientDao());
    }

    @Override
    public List<Client> getAll() {
        return new ArrayList<>(clients);
    }

    @Override
    public void save(Client client) {
        clients.add(client);
    }

    @Override
    public Client getById(Long id) {
        for (Client entity : getAll()) {
            if (id.equals(entity.getId())) {
                return entity;
            }
        }
        LOGGER.error("Client #" + id + " not found.");
        throw new DaoException("Client #" + id + " not found.");
    }

    @Override
    public void delete(Client client) {
        try {
            clients.remove(client);
        } catch (Exception e) {
            throw new DaoException("This client is not available.", e);
        }
    }
}
