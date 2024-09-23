package service;

import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Material;
import util.DatabaseConnection;
import util.LoggerUtils;
import repository.MaterialRepository;

public class MaterialService implements MaterialRepository {

    private static final String SQL_INSERT = "INSERT INTO  public.material (name , component_type , project_id, transport_cost, quality_coefficient, quantity, unit_cost) VALUES(? , ? , ? , ? , ? , ?, ?)";

    @Override
    public Boolean addMaterial(List<Material> materials, long project_id) {
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(SQL_INSERT);) {

            for (Material material : materials) {
                stmt.setString(1, material.getName());
                stmt.setString(2, "MATERIAL");
                stmt.setLong(3, project_id);
                stmt.setDouble(4, material.getTransportCost());
                stmt.setDouble(5, material.getQualityCoefficient());
                stmt.setDouble(6, material.getQuantity());
                stmt.setDouble(7, material.getUnitCost());

                int n = stmt.executeUpdate();
                if (n == 1) {
                    LoggerUtils.logger.info("Component of type \"Material\" added successfully: " + material.getName());
                } else {
                    LoggerUtils.logger.warning("Failed to add component: " + material.getName());
                }
            }

            return true;
            
        } catch (SQLException e) {
            System.out.println("Unexpected error occured while inserting materials");
            LoggerUtils.logger.warning(e.getMessage());
            LoggerUtils.logStackTrace(e);
        }

        return false;

    }

}
