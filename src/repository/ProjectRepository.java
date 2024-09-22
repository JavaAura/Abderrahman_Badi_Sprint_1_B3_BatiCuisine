package repository;

import java.util.List;
import java.util.Optional;

import enums.ProjectStatus;
import model.Project;

public interface ProjectRepository {

    public Optional<Project> get(long id);

    public List<Project> getAll(ProjectStatus projectStatus);

    public Project addProject(Project project);

    public Boolean updateStatus(Project project);

}
