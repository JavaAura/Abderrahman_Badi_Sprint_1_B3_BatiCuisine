package view.menu;

import java.util.Scanner;

import util.IO;
import view.interfaces.Menu;

public class ProjectMenu implements Menu {


    Scanner in = IO.getScanner();

    public ProjectMenu() {
    }

    @Override
    public int display() {
        int input = -1;

        System.out.println("\n\t\t+---------------------------------------------+");
        System.out.println("\t\t|                 PROJECT MENU                |");
        System.out.println("\t\t+---------------------------------------------+");
        System.out.println("\t\t|                                             |");
        System.out.println("\t\t|     1- List of Ongoing Projects             |");
        System.out.println("\t\t|     2- List of Finished Projects            |");
        System.out.println("\t\t|     3- List of Projects Awaiting Validation |");
        System.out.println("\t\t|     4- Back                                 |");
        System.out.println("\t\t|                                             |");
        System.out.println("\t\t+---------------------------------------------+");
        System.out.print("Pick your choice : ");

        try {
            input = in.nextInt();
            if (input < 1 || input > 4) {
                System.out.println("Please pick a choice between 1 and 4...");
                in.next();
            }
        } catch (Exception e) {
            System.out.println("Please pick a valid number...");
            in.next();
        }
        return input;
    }


    @Override
    public void back() {
        System.out.println("Press Enter to go back...");
        in.next();
    }
}
