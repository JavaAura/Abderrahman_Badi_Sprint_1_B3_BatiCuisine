package repository;

import java.sql.SQLException;
import java.util.Optional;

import model.Client;  

public interface ClientRepository {
    
    public Boolean addClient(Client client) throws SQLException;
    public Optional<Client> findClientByName(String nom) throws SQLException;
    
}
