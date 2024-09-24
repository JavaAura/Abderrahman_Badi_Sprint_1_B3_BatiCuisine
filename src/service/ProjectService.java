package service;

import java.util.List;

import enums.ProjectStatus;
import model.Project;

import repository.ProjectRepository;
import repository.implementation.ProjectRepositoryImpl;


public class ProjectService implements ProjectRepository {

    private final ProjectRepository projectRepository;

    public ProjectService() {
        this.projectRepository = new ProjectRepositoryImpl();
    }

    @Override
    public List<Project> getAll(ProjectStatus status) {
        return projectRepository.getAll(status);
    }

    @Override
    public Project addProject(Project project) {
        return projectRepository.addProject(project);
    }

    @Override
    public Boolean updateStatus(Project project, ProjectStatus status) {
        return projectRepository.updateStatus(project, status);
    }
}
