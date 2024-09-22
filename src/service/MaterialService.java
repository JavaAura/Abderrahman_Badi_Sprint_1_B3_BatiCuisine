package service;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Material;
import util.DatabaseConnection;
import util.LoggerUtils;
import repository.MaterialRepository;

public class MaterialService implements MaterialRepository {

    private static final String SQL_INSERT = "INSERT INTO  public.material (name , component_type , vat_rate, transport_cost, quality_coefficient, quantity, unit_cost) VALUES(? , ? , ? , ? , ? , ?, ?)";

    @Override
    public Boolean addMaterial(Material material) {
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(SQL_INSERT)) {
            stmt.setString(1, material.getName());
            stmt.setString(2, "MATERIAL");
            stmt.setDouble(3, material.getVatRate());
            stmt.setDouble(4, material.getTransportCost());
            stmt.setDouble(5, material.getQualityCoefficient());
            stmt.setDouble(6, material.getQuantity());
            stmt.setDouble(7, material.getUnitCost());

            int n = stmt.executeUpdate();
            if (n == 1) {
                LoggerUtils.logger.info("Component of type \"Material\" added successfully: " + material.getName());
                return true;
            }
        } catch (SQLException e) {
            LoggerUtils.logger.warning(e.getMessage());
            LoggerUtils.logStackTrace(e);
        }

        return false;

    }

}
