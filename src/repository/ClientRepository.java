package repository;

import java.util.List;
import java.util.Optional;

import model.Client;  

public interface ClientRepository {
    
    public Optional<Client> get(long id);
    public Client addClient(Client client);
    public List<Client> findClientByName(String nom);
    
}
