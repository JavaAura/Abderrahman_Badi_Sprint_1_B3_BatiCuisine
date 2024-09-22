package controller;

import java.util.ArrayList;
import java.util.List;

import model.Material;
import model.Project;
import model.Quote;
import model.WorkForce;
import service.MaterialService;
import service.ProjectService;
import service.QuoteService;
import service.WorkForceService;
import view.ProjectView;
import view.interfaces.View;
import view.menu.ProjectMenu;

public class ProjectController {
    private View projectMenu = new ProjectMenu();
    private ProjectView projectView = new ProjectView();
    private ProjectService projectService = new ProjectService();
    private QuoteService quoteService = new QuoteService();
    private MaterialService materialService = new MaterialService();
    private WorkForceService workForceService = new WorkForceService();
    private boolean isRunning = true;

    public void startProjectMenu() {
        while (isRunning) {
            System.out.println("\033[H\033[2J");
            int choice = projectMenu.display();
            handleChoice(choice);
        }
    }

    public void addProjectUI() {
        int choice = 0;
        Project project = new Project();
        Quote quote = new Quote();
        List<Material> materials = new ArrayList<Material>();
        List<WorkForce> workForces = new ArrayList<WorkForce>();

        do {
            choice = projectView.promptClient();
            if (choice == 3)
                return;
        } while (choice != 1 || choice != 2);
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                projectService.getAll(null);
                projectMenu.back();
                break;
            case 2:
                System.out.println("List of finished projects");
                projectMenu.back();
                break;
            case 3:
                System.out.println("List no validated projects");
                projectMenu.back();
                break;
            case 4:
                isRunning = false;
                break;
            default:
                break;
        }
    }

}
