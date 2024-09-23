package controller;

import util.IO;
import view.interfaces.Menu;
import view.menu.MainMenu;

public class MenuController {
    private Menu mainMenu = new MainMenu();
    private ProjectController projectController = new ProjectController();
    private QuoteController quoteController = new QuoteController();
    private boolean isRunning = true;

    public void startMainMenu() {
        while (isRunning) {
            IO.clear();
            int choice = mainMenu.display();
            handleChoice(choice);
        }
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                projectController.addProjectUI();
                break;
            case 2:
                projectController.startProjectMenu();
                break;
            case 3:
                quoteController.startQuoteMenu();
                break;
            case 4:
                isRunning = false;
                System.out.println("Exiting...");
                break;
            default:
                break;
        }
    }

}
