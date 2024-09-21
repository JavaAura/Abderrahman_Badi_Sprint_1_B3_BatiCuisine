package view;

import java.util.List;

import model.Project;

public class ProjectView {
    

    public void listProjects(List<Project> projects){
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
            System.out.printf("| %-3d| %-24s | %-24s | %-8d |  %-2.2f %  |  %-6.2f m²  | %10s |\n", project.getId(),
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

    public void showDetails(Project project){
        
    }

}
