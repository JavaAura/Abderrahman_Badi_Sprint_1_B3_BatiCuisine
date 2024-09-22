package view;

import java.util.List;

import model.Client;
import model.Project;
import model.Material;
import model.WorkForce;

import util.IO;
import util.InputValidator;

public class ProjectView {
    public Project addProjectUI() {

        IO.clear();
        String name = InputValidator.promptAndParseString("Project Name : ");
        Double surface = InputValidator.promptAndParseDouble("Project Surface (m²) : ");
        Double profitMargin = InputValidator.promptAndParseDouble("Profit Margin : ");
        Double vatRate = InputValidator
                .promptAndParseNullableDouble("VAT Rate ( 0 if not applicale ) : ");

        return new Project(name, surface, vatRate, profitMargin);
    }

    public int clientMenu() {
        IO.clear(); // Clear screen
        System.out.println(
                "Do you want to search for an existing customer or add a new one ?");
        System.out.println("\t\t+---------------------------------------------+");
        System.out.println("\t\t|                                             |");
        System.out.println("\t\t|     1- Search for an existing client        |");
        System.out.println("\t\t|     2- Add a new client                     |");
        System.out.println("\t\t|     3- Back to Main Menu                    |");
        System.out.println("\t\t|                                             |");
        System.out.println("\t\t+---------------------------------------------+");

        return InputValidator.promptAndParseInt("Choice : ", 1, 3);
    }

    public void listProjects(List<Project> projects) {
        if (projects.size() == 0) {
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------------+");
            System.out.println(
                    "|                                              No projects found                                              |");
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------------+\n\n\n");
        }

        System.out.println(
                "+-------------------------------------------------------------------------------------------------------------+");
        System.out.println(
                "| Id |           Title          |          Author          |   Cost   |  Margin  |    Surface    |   Status   |");
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------------+");
        for (Project project : projects) {
            System.out.printf(
                    "| %-3d| %-24s | %-24s | %-8d |  %-2.2f %  |  %-6.2f m²  | %10s |\n",
                    project.getId(),
                    project.getProjectName(),
                    project.getClient().getName(),
                    project.getTotalCost(),
                    project.getProfitMargin(),
                    project.getSurface(),
                    project.getProjectStatus().toString());
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------------+");
        }
    }

    public void showDetails(Project project) {
        System.out.println(
                "+---------------------------------------------------------------------------------------------------------------------+");
        System.out.printf(
                "|\t ID : %-3d      %-85s \t|\n", project.getId(),
                project.getProjectName());
        System.out.println(
                "|\t                                                                                                     \t|");
        System.out.println(
                "|\t                                                                                                     \t|");
        // 101 spaces
        System.out.printf(
                "|\t \t Client : %-81s \t|\n", project.getClient().getName());
        System.out.printf(
                "|\t \t Site Address : %-75s \t|\n", project.getClient().getAddress());
        System.out.printf(
                "|\t \t Surface : %-5.3f m²                                                                     \t|\n",
                project.getSurface());
        System.out.println(
                "|\t                                                                                                     \t|");
        System.out.println(
                "|\t                                            Quote Details                                            \t|");
        System.out.println(
                "|\t                                                                                                     \t|");
        System.out.printf(
                "|\t \t Estimated Amount : %-6.2f £                                                             \t|\n",
                project.getQuote().getEstimatedAmount());
        System.out.printf(
                "|\t \t Issue Date : %-77s \t|\n", project.getQuote().getIssueDate());
        System.out.printf(
                "|\t \t Validity Date : %-74s \t|\n", project.getQuote().getValidityDate());
        System.out.println(
                "|\t                                                                                                     \t|");
        System.out.println(
                "|\t                                                                                                     \t|");
        System.out.println(
                "+---------------------------------------------------------------------------------------------------------------------+");
    }

    public void showProjectSummary(Project project, Client client, List<Material> materials,
            List<WorkForce> workForces) {
        System.out.println(
                "+---------------------------------------------------------------------------------------------------------------------+");
        System.out.printf(
                "|\t ID : %-3d     %-61s \t|\n", project.getId(),
                project.getProjectName());
        System.out.println(
                "|\t                                                                                                     \t|");
        System.out.println(
                "|\t                                                                                                     \t|");
        // 101 spaces
        System.out.printf(
                "|\t \t Client : %-81s \t|\n", client.getName());
        System.out.printf(
                "|\t \t Site Address : %-75s \t|\n", client.getAddress());
        System.out.printf(
                "|\t \t Surface : %-5.3f m²                                                                      \t|\n",
                project.getSurface());
        System.out.printf(
                "|\t \t Profit Margin : %-2.2f %                                                                     \t|\n",
                project.getProfitMargin());
        System.out.printf(
                "|\t \t VAT Rate : %-2.2f %                                                                          \t|\n",
                project.getVatRate());
        System.out.println(
                "|\t                                                                                                     \t|");
        System.out.println(
                "|\t                                          Components Details                                         \t|");
        System.out.println(
                "|\t                                                                                                     \t|");
        System.out.println(
                "|\t---------------------------------------------  Material  --------------------------------------------\t|");
        for (Material material : materials) {
            System.out.printf(
                    "|\t - %-15s: %-4.2f £ (Quantity : %-3.1f, Unit Cost : %3.1f, Quality: %1.1f, Transport: %3.2f) \t|\n",
                    material.getName(),
                    material.calculateCost(),
                    material.getQuantity(),
                    material.getUnitCost(),
                    material.getQualityCoefficient(),
                    material.getTransportCost());
        }
        System.out.printf(
                "|\t    Total cost before applying VAT : %6.2f £                                                     \t|\n",
                materials.stream()
                        .mapToDouble(Material::calculateCost)
                        .sum());
        System.out.printf(
                "|\t    Total cost after applying VAT : %6.2f £                                                      \t|\n",
                materials.stream()
                        .mapToDouble(Material::calculateCost)
                        .sum() * project.getVatRate() / 100);
        System.out.println(
                "|\t                                                                                                     \t|");
        System.out.println(
                "|\t--------------------------------------------  Work Force  -------------------------------------------\t|");
        for (WorkForce workForce : workForces) {
            System.out.printf(
                    "|\t - %-21s: %-4.2f £ (Hourly Rate: %-3.1f £/h, Work Hours: %3.1f h, Productivity: %1.1f) \t|\n",
                    workForce.getName(),
                    workForce.calculateCost(),
                    workForce.getHourlyRate(),
                    workForce.getWorkHours(),
                    workForce.getWorkerProductivity());
        }
        System.out.printf(
                "|\t    Total cost before applying VAT : %6.2f £                                                     \t|\n",
                workForces.stream()
                        .mapToDouble(WorkForce::calculateCost)
                        .sum());
        System.out.printf(
                "|\t    Total cost after applying VAT : %6.2f £                                                      \t|\n",
                workForces.stream()
                        .mapToDouble(WorkForce::calculateCost)
                        .sum() * project.getVatRate() / 100);
        System.out.println(
                "|\t                                                                                                     \t|");
        System.out.println(
                "|\t                                                                                                     \t|");
        System.out.println(
                "+---------------------------------------------------------------------------------------------------------------------+");
    }

}
