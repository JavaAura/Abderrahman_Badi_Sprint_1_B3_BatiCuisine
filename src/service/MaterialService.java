package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import enums.ComponentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Material;
import util.DatabaseConnection;
import util.LoggerUtils;
import repository.MaterialRepository;

public class MaterialService implements MaterialRepository {

    private static final String SQL_LIST = "SELECT * FROM public.material WHERE project_id = ?";
    private static final String SQL_INSERT = "INSERT INTO  public.material (component_name, component_type , project_id, transport_cost, quality_coefficient, quantity, unit_cost) VALUES(?, ?::component_type, ?, ?, ?, ?, ?)";

    @Override
    public List<Material> getAll(long project_id) {
        List<Material> materials = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(SQL_LIST)) {
            stmt.setLong(1, project_id);

            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String name = rs.getString("component_name");
                    ComponentType componentType = ComponentType.valueOf(rs.getString("component_type"));
                    Double unitCost = rs.getDouble("unit_cost");
                    Double quantity = rs.getDouble("quantity");
                    Double transportCost = rs.getDouble("transport_cost");
                    Double qualityCoefficient = rs.getDouble("quality_coefficient");

                    Material material = new Material(id, name, componentType, unitCost, quantity, transportCost,
                            qualityCoefficient);

                    materials.add(material);

                }
            }
        } catch (SQLException e) {
            System.out.println("Unexpected error occured while retrieving materials");
            LoggerUtils.logger.warning(e.getMessage());
            LoggerUtils.logStackTrace(e);
        }
        return materials;
    }

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
