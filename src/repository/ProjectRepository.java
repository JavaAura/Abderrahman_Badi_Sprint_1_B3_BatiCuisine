package repository;

import java.sql.SQLException;

import model.Project;

public interface ProjectRepository {

    public Boolean addProject(Project project) throws SQLException;

}
