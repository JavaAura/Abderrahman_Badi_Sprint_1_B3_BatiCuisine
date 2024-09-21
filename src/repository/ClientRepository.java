package repository;

import java.util.Optional;

import model.Client;  

public interface ClientRepository {
    
    public Boolean addClient(Client client);
    public Optional<Client> findClientByName(String nom);
    
}
