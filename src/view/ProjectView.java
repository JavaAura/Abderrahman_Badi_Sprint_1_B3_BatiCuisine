package view;

import java.util.List;

import model.Project;
import util.InputValidator;

public class ProjectView {

        public int promptClient(){
                System.out.println("\033[H\033[2J"); // Clear screen
                System.out.println("Do you want to search for an existing customer or add a new one ?");
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
                        System.out.printf("| %-3d| %-24s | %-24s | %-8d |  %-2.2f %  |  %-6.2f m²  | %10s |\n",
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
                                "+------------------------------------------------------------------------------------------------------------+");
                System.out.printf(
                                "|\t\t ID : %-3d     %-61s \t\t|\n", project.getId(),
                                project.getProjectName());
                System.out.println(
                                "|\t\t                                                                            \t\t|");
                System.out.println(
                                "|\t\t                                                                            \t\t|");
                // 76 spaces
                System.out.printf(
                                "|\t\t \t Client : %-56s \t\t|\n", project.getClient().getName());
                System.out.printf(
                                "|\t\t \t Site Address : %-50s \t\t|\n", project.getClient().getAddress());
                System.out.printf(
                                "|\t\t \t Surface : %-53.2f  \t\t|\n", project.getSurface());
                System.out.println(
                                "|\t\t                                                                            \t\t|");
                System.out.println(
                                "|\t\t                                Quote Details                               \t\t|");
                System.out.println(
                                "|\t\t                                                                            \t\t|");
                System.out.printf(
                                "|\t\t \t Estimated Amount : %-46d  \t\t|\n", project.getQuote().getEstimatedAmount());
                System.out.printf(
                                "|\t\t \t Issue Date : %-52s   \t\t|\n", project.getQuote().getIssueDate());
                System.out.printf(
                                "|\t\t \t Validity Date : %-49s   \t\t|\n", project.getQuote().getValidityDate());
                System.out.println(
                                "|\t\t                                                                            \t\t|");
                System.out.println(
                                "|\t\t                                                                            \t\t|");
                System.out.println(
                                "+------------------------------------------------------------------------------------------------------------+");
        }

}
