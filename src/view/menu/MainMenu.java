package view.menu;

import java.util.Scanner;

import util.IO;
import view.interfaces.View;

public class MainMenu implements View {


    Scanner in = IO.getScanner();

    public MainMenu() {
    }

    @Override
    public int display() {
        int input = -1;

        System.out.println("\n\t\t+---------------------------------------------+");
        System.out.println("\t\t|                  MAIN MENU                  |");
        System.out.println("\t\t+---------------------------------------------+");
        System.out.println("\t\t|                                             |");
        System.out.println("\t\t|     1- Create New Project                   |");
        System.out.println("\t\t|     2- List All Projects                    |");
        System.out.println("\t\t|     3- Calculate The Cost of a Project      |");
        System.out.println("\t\t|     4- Exit                                 |");
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
