package repository;

import java.util.List;

import model.Material;

public interface MaterialRepository {
    Boolean addMaterial(List<Material> material, long project_id);
}
