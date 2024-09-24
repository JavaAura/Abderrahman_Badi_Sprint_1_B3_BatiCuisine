package service;

import java.util.List;

import model.Material;

import repository.MaterialRepository;
import repository.implementation.MaterialRepositoryImpl;


public class MaterialService implements MaterialRepository {

    private final MaterialRepository materialRepository;

    public MaterialService() {
        this.materialRepository = new MaterialRepositoryImpl();
    }

    public List<Material> getAll(long project_id) {
        return materialRepository.getAll(project_id);
    }

    public Boolean addMaterial(List<Material> materials, long project_id) {
        return materialRepository.addMaterial(materials, project_id);
    }
}
