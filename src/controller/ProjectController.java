package controller;

import view.interfaces.View;
import view.menu.ProjectMenu;

public class ProjectController {
    private View projectMenu = new ProjectMenu();
    private boolean isRunning = true;

    public void startProjectMenu() {
        while (isRunning) {
            System.out.println("\033[H\033[2J");
            int choice = projectMenu.display();
            handleChoice(choice);
        }
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println("List of ongoing projects");
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
