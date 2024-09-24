package service;

import java.util.List;

import model.Client;
import repository.ClientRepository;
import repository.implementation.ClientRepositoryImpl;

public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepositoryImpl();
    }

    public Client addClient(Client client) {
        return clientRepository.addClient(client);
    }

    public List<Client> findClientByName(String name) {
        return clientRepository.findClientByName(name);
    }
}