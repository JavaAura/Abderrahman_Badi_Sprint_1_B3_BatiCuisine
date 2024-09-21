package service;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Material;
import util.DatabaseConnection;
import repository.MaterialRepository;

public class MaterialService implements MaterialRepository {

    private static final String SQL_INSERT = "INSERT INTO  public.material (name , component_type , vat_rate, transport_cost, quality_coefficient, quantity, unit_cost) VALUES(? , ? , ? , ? , ? , ?, ?)";

    private static Connection con = DatabaseConnection.getConnection();

    @Override
    public Boolean addMaterial(Material material) {
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, material.getName());
            stmt.setString(2, "MATERIAL");
            stmt.setDouble(3, material.getVatRate());
            stmt.setDouble(4, material.getTransportCost());
            stmt.setDouble(5, material.getQualityCoefficient());
            stmt.setDouble(6, material.getQuantity());
            stmt.setDouble(7, material.getUnitCost());

            int n = stmt.executeUpdate();
            return n == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
    }

}
