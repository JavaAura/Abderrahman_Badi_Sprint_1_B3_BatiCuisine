package repository;

import java.sql.SQLException;

import model.Material;

public interface MaterialRepository {
    Boolean addMaterial(Material material) throws SQLException;
}
