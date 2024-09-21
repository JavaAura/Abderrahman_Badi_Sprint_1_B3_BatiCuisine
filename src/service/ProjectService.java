package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import enums.ProjectStatus;
import model.Project;
import repository.ProjectRepository;

public class ProjectService implements ProjectRepository {

    @Override
    public Boolean addProject(Project project) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addProject'");
    }

    @Override
    public Optional<Project> get(long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List<Project> getAll(ProjectStatus projectStatus) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Boolean updateStatus(Project project) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStatus'");
    }
}
