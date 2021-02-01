package by.senla.task3.ex4.dao;

import by.senla.task3.ex4.model.Client;

import java.util.List;

public class ClientDao extends AbstractDao<Client> {
    private static final ClientDao clientDao = new ClientDao();
    private final List<Client> clients = this.getList();

    private ClientDao() {
    }

    public static ClientDao getInstance() {
        return clientDao;
    }

    public List<Client> getClients() {
        return clients;
    }

    @Override
    public Client update(Client client) {
        Client temp = null;
        if (clients.contains(client)) {
            temp = clients.get(clients.indexOf(client));
            temp.setId(client.getId());
            temp.setAge(client.getAge());
            temp.setFullName(client.getFullName());
        }
        return temp;
    }
}
