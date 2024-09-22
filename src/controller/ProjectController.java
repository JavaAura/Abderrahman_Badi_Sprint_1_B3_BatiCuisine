package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Client;
import model.Material;
import model.Project;
import model.Quote;
import model.WorkForce;
import service.ClientService;
import service.MaterialService;
import service.ProjectService;
import service.QuoteService;
import service.WorkForceService;
import view.ClientView;
import view.ProjectView;
import view.interfaces.View;
import view.menu.ProjectMenu;

public class ProjectController {

    private View projectMenu = new ProjectMenu();
    private ProjectView projectView = new ProjectView();
    private ClientView clientView = new ClientView();

    private ProjectService projectService = new ProjectService();
    private QuoteService quoteService = new QuoteService();
    private MaterialService materialService = new MaterialService();
    private WorkForceService workForceService = new WorkForceService();
    private ClientService clientService = new ClientService();

    private boolean isRunning = true;

    public void startProjectMenu() {
        isRunning = true;
        while (isRunning) {
            System.out.println("\033[H\033[2J");
            int choice = projectMenu.display();
            handleChoice(choice);
        }
    }

    public void startClientMenu() {
        isRunning = true;
        while (isRunning) {
            System.out.println("\033[H\033[2J");
            int choice = projectView.promptClient();
            handleClientMenuChoice(choice);
        }
    }

    public void addProjectUI() {
        Quote quote = new Quote();
        Project project = new Project();
        List<Material> materials = new ArrayList<Material>();
        List<WorkForce> workForces = new ArrayList<WorkForce>();

        startClientMenu();
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

    public Client handleClientMenuChoice(int choice) {
        Client client = new Client();

        switch (choice) {
            case 1:
                String input = clientView.searchClientUI();
                List<Client> clients = clientService.findClientByName(input);

                Client selectedClient = clientView.listClients(clients);

                if (selectedClient == null) {

                }
                client = selectedClient;
                break;
            case 2:

                break;
            case 3:
                isRunning = false;
                break;
            default:
                break;
        }

        return client;
    }
}
