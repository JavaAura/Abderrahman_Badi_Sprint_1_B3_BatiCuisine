package repository;

import java.util.List;

import model.Material;

public interface MaterialRepository {

    public List<Material> getAll(long project_id);
    public Boolean addMaterial(List<Material> material, long project_id);
}
