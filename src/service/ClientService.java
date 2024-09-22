package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Client;
import util.DatabaseConnection;
import util.LoggerUtils;
import repository.ClientRepository;

public class ClientService implements ClientRepository {

    private static final String SQL_FIND_BY_NAME = "SELECT * from public.client WHERE name LIKE ?";
    private static final String SQL_INSERT = "INSERT INTO public.client(name , address, phone_number, is_proessional) VALUES (?,?,?,?)";

    @Override
    public Boolean addClient(Client client) {
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(SQL_INSERT);) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getAddress());
            stmt.setString(3, client.getPhoneNumber());
            stmt.setBoolean(4, client.getIsProfessional());

            int n = stmt.executeUpdate();

            if (n == 1) {
                LoggerUtils.logger.info("Client added successfully: " + client.getName());
                return true;
            }

        } catch (SQLException e) {
            LoggerUtils.logger.warning(e.getMessage());
            LoggerUtils.logStackTrace(e);
        }
        return false;

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
