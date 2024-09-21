package repository;

import java.sql.SQLException;
import java.util.Optional;

import enums.ProjectStatus;

import java.util.List;

import model.Project;

public interface ProjectRepository {

    public Optional<Project> get(long id) throws SQLException;

    public List<Project> getAll(ProjectStatus projectStatus) throws SQLException;

    public Boolean addProject(Project project) throws SQLException;

    public Boolean updateStatus(Project project) throws SQLException;

}
