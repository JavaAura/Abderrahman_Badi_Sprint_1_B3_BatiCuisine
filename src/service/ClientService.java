package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import model.Client;
import util.DatabaseConnection;
import repository.ClientRepository;

public class ClientService implements ClientRepository {

    private static final String SQL_FIND_BY_NAME = "SELECT * from public.client WHERE name LIKE ?";
    private static final String SQL_INSERT = "INSERT INTO public.client(name , address, phone_number, is_proessional) VALUES (?,?,?,?)";

    private static Connection con = DatabaseConnection.getConnection();

    @Override
    public Boolean addClient(Client client) throws SQLException {
        if (con == null) {
            throw new SQLException("Database connection is not initialized.");
        }

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getAddress());
            stmt.setString(3, client.getPhoneNumber());
            stmt.setBoolean(4, client.getIsProfessional());

            int n = stmt.executeUpdate();

            return n == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return false;

    }

    @Override
    public Optional<Client> findClientByName(String nom) throws SQLException {
        Client client = new Client();
        if (con == null) {
            throw new SQLException("Database connection is not initialized.");
        }

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(SQL_FIND_BY_NAME);
            stmt.setString(1, "%" + nom + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));
                client.setAddress(rs.getString("address"));
                client.setPhoneNumber(rs.getString("phone_number"));
                client.setIsProfessional(rs.getBoolean("is_professional"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return Optional.ofNullable(client);
    }

}
