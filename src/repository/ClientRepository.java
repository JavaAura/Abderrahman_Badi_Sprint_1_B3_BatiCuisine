package repository;

import java.util.List;

import model.Client;  

public interface ClientRepository {
    
    public Client addClient(Client client);
    public List<Client> findClientByName(String nom);
    
}
