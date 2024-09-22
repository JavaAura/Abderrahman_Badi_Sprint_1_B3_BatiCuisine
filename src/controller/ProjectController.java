package controller;

import java.util.ArrayList;
import java.util.List;

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
import view.ComponentView;
import view.ProjectView;
import view.interfaces.View;
import view.menu.ProjectMenu;

import util.IO;

public class ProjectController {

    private View projectMenu = new ProjectMenu();
    private ProjectView projectView = new ProjectView();
    private ClientView clientView = new ClientView();
    private ComponentView componentView = new ComponentView();

    private ProjectService projectService = new ProjectService();
    private QuoteService quoteService = new QuoteService();
    private MaterialService materialService = new MaterialService();
    private WorkForceService workForceService = new WorkForceService();
    private ClientService clientService = new ClientService();

    private boolean isRunning = true;

    public void startProjectMenu() {
        isRunning = true;
        while (isRunning) {
            IO.clear();
            int choice = projectMenu.display();
            handleChoice(choice);
        }
    }

    public Client startClientMenu() {
        Client client = null;
        isRunning = true;
        do {
            IO.clear();
            int choice = projectView.clientMenu();
            client = handleClientMenuChoice(choice);
        } while (isRunning && client == null);

        return client;
    }

    public void addProjectUI() {
    
        Client selectedClient = startClientMenu();
        Project project = projectView.addProjectUI();
        List<Material> materials = componentView.addMaterialUI();
        List<WorkForce> workForces = componentView.addWorkForceUI();

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
                client = clientView.listClients(clients);
                break;
            case 2:
                client = clientService.addClient(clientView.addClientUI());
                if (client != null) {
                    System.out.println("Client added successfuly!");
                }
                IO.sysPause();
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
