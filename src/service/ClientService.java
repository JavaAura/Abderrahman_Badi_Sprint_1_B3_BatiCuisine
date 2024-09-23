package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Client;
import util.DatabaseConnection;
import util.LoggerUtils;
import repository.ClientRepository;

public class ClientService implements ClientRepository {

    private static final String SQL_FIND_BY_NAME = "SELECT * from public.client WHERE name ILIKE ?";
    private static final String SQL_FIND_BY_ID = "SELECT * from public.client WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO public.client(name , address, phone_number, is_professional) VALUES (?,?,?,?)";

    @Override
    public Optional<Client> get(long id) {
        Client client = null;
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(SQL_FIND_BY_ID);) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    client = new Client(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("phone_number"),
                            rs.getBoolean("is_professional"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Unexpected error occurred while retrieving client");
            LoggerUtils.logger.warning(e.getMessage());
            LoggerUtils.logStackTrace(e);
        }

        return Optional.ofNullable(client);
    }

    @Override
    public Client addClient(Client client) {
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getAddress());
            stmt.setString(3, client.getPhoneNumber());
            stmt.setBoolean(4, client.getIsProfessional());

            int n = stmt.executeUpdate();

            if (n > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        long generatedId = rs.getLong(1);
                        client.setId(generatedId);
                        LoggerUtils.logger.info("Client added successfully with ID: " + generatedId);
                        return client;
                    }
                }
            } else {
                LoggerUtils.logger.warning("Client insertion failed, no rows affected.");
            }
        } catch (SQLException e) {
            LoggerUtils.logger.warning(e.getMessage());
            LoggerUtils.logStackTrace(e);
        }

        return null;

    }

    @Override
    public List<Client> findClientByName(String name) {
        List<Client> clients = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(SQL_FIND_BY_NAME);) {
            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String clientName = rs.getString("name");
                    String address = rs.getString("address");
                    String phone_number = rs.getString("phone_number");
                    Boolean isProfessional = rs.getBoolean("is_professional");

                    Client client = new Client(id, clientName, address, phone_number, isProfessional);
                    clients.add(client);
                }
            }

        } catch (SQLException e) {
            LoggerUtils.logger.warning(e.getMessage());
            LoggerUtils.logStackTrace(e);
        }

        return clients;
    }

}
