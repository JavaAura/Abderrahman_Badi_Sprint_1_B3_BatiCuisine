package view;

import java.util.List;

import model.Client;
import util.IO;
import util.InputValidator;

public class ClientView {

    public String searchClientUI() {
        return InputValidator.promptAndParseString("\033[H\033[2J \n Enter customer name : ");
    }

    public Client listClients(List<Client> clients) {
        Client selectedClient = null;

        if (clients.size() == 0) {
            System.out.println("\033[H\033[2J");
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------------+");
            System.out.println(
                    "|                                               No clients found                                              |");
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------------+\n\n\n");

            IO.sysPause();
            return null;
        }

        do {
            System.out.println("\033[H\033[2J");
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------------+");
            System.out.println(
                    "|  Id  |        Full Name       |            Address           |       Phone Number       |  Is Professional  |");
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------------+");
            for (Client client : clients) {
                System.out.printf("| %-3d | %-22s | %-28s | %-24s |  %-15s  |\n",
                        client.getId(),
                        client.getName(),
                        client.getAddress(),
                        client.getPhoneNumber(),
                        client.getIsProfessional() ? "YES" : "NO");
                System.out.println(
                        "+-------------------------------------------------------------------------------------------------------------+");
            }

            System.out.print(
                    "0 - Return to User Menu \nPlease pick a user by entering their ID \nPick your choice : ");
            try {
                int selectedId = IO.getScanner().nextInt();
                if (selectedId == 0)
                    return selectedClient;
                selectedClient = clients.stream()
                        .filter(client -> client.getId() == selectedId)
                        .findFirst()
                        .orElse(null);

                if (selectedClient == null) {
                    System.out.println("Invalid ID. Please try again.");
                    IO.getScanner().next();
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                IO.getScanner().next();
            }

        } while (selectedClient == null);

        return selectedClient;

    }
}
