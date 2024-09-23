package view;

import java.util.List;

import model.Client;
import util.IO;
import util.InputValidator;

public class ClientView {

    public String searchClientUI() {
        return InputValidator.promptAndParseString("\033[H\033[2J \n Enter customer name : ");
    }

    public Client addClientUI() {
        int choice;
        IO.clear();
        String name = InputValidator.promptAndParseString("Enter the client's full name : ");
        String address = InputValidator.promptAndParseString("Enter the client's address : ");
        String phoneNumber = InputValidator.promptAndParsePhoneNumber("Enter the client's phone number : ");
        Boolean isProfessional;

        do {
            System.out.println("Client type : ");
            System.out.println("\t 1 - Professional \t 2 - Commercial");
            choice = InputValidator.promptAndParseInt("Choice :", 1, 2);
        } while (choice != 1 && choice != 2);

        isProfessional = (choice == 1) ? true : false;

        return new Client(name, address, phoneNumber, isProfessional);
    }

    public Client listClients(List<Client> clients) {
        Client selectedClient = null;

        if (clients.size() == 0) {
            IO.clear();
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------------+");
            System.out.println(
                    "|                                                                                                             |");
            System.out.println(
                    "|                                               No clients found                                              |");
            System.out.println(
                    "|                                                                                                             |");
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------------+\n\n");

            IO.sysPause();
            return null;
        }

        do {
            IO.clear();
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------------+");
            System.out.println(
                    "|  Id  |        Full Name       |            Address           |       Phone Number       |  Is Professional  |");
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------------+");
            for (Client client : clients) {
                System.out.printf("|  %-3d | %-22s | %-28s | %-24s |  %-15s  |\n",
                        client.getId(),
                        client.getName(),
                        client.getAddress(),
                        client.getPhoneNumber(),
                        client.getIsProfessional() ? "YES" : "NO");
                System.out.println(
                        "+-------------------------------------------------------------------------------------------------------------+");
            }

            System.out.print(
                    "0 - Return to User Menu \nPlease pick a client by entering their ID \nClient ID : ");
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
                IO.getScanner().next();
            }

        } while (selectedClient == null);

        return selectedClient;

    }
}
