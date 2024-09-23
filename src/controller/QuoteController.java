package controller;

import java.util.ArrayList;
import java.util.List;

import enums.ProjectStatus;
import model.Component;
import model.Material;
import model.Project;
import model.WorkForce;
import service.MaterialService;
import service.ProjectService;
import service.WorkForceService;
import view.ProjectView;

public class QuoteController {

    private ProjectView projectView = new ProjectView();
    private ProjectService projectService = new ProjectService();
    private MaterialService materialService = new MaterialService();
    private WorkForceService workForceService = new WorkForceService();

    public void startQuoteMenu() {
        List<Component> components = new ArrayList<>();

        List<Project> projects = projectService.getAll(ProjectStatus.ONGOING);
        Project selectedProject = projectView.listProjects(projects);

        List<Material> materials = materialService.getAll(selectedProject.getId());
        List<WorkForce> workForces = workForceService.getAll(selectedProject.getId());

        components.addAll(materials);
        components.addAll(workForces);

        selectedProject.setComponents(components);

        projectView.showProjectSummary(selectedProject, null, components)
    }
}
