package controller;

import java.time.LocalDate;
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
import util.InputValidator;

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

        Double totalCost = projectView.showProjectSummary(project, selectedClient, materials, workForces);
        System.out.printf("Total Cost is after applying professional discount : %10.2f \n", totalCost);
        IO.sysPause();
        if (InputValidator.promptYesOrNo("Do you want to save the project")) {
            Quote quote = quoteService.addQuote(new Quote(totalCost, LocalDate.now()));
            if (quote != null)
                System.out.println("Quote generated.");

            project.setClient(selectedClient);
            project.setQuote(quote);

            Project addedProject = projectService.addProject(project);

            if (materialService.addMaterial(materials, addedProject.getId())) {
                System.out.println("** Materials added successfully! **");
            }

            if (workForceService.addWorkForce(workForces, addedProject.getId())) {
                System.out.println("** Work Forces added successfully! **");
            }

            System.out.println("Project saved successfully !");
            IO.sysPause();
        }
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
        Client client = null;

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
