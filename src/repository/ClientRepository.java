package repository;

import java.util.List;

import model.Client;  

public interface ClientRepository {
    
    public Boolean addClient(Client client);
    public List<Client> findClientByName(String nom);
    
}
