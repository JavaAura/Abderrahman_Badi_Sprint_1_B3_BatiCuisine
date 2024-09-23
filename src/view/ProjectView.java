package view;

import java.util.ArrayList;
import java.util.List;

import enums.ComponentType;
import enums.ProjectStatus;
import model.Client;
import model.Component;
import model.Project;
import model.Material;
import model.WorkForce;
import service.MaterialService;
import service.ProjectService;
import service.QuoteService;
import service.WorkForceService;
import util.IO;
import util.InputValidator;

public class ProjectView {

	private MaterialService materialService = new MaterialService();
	private WorkForceService workForceService = new WorkForceService();
	private ProjectService projectService = new ProjectService();
	private QuoteService quoteService = new QuoteService();
	private Boolean isRunning = true;

	public Project addProjectUI() {

		IO.clear();
		String name = InputValidator.promptAndParseString("Project Name : ");
		Double surface = InputValidator.promptAndParseDouble("Project Surface (m²) : ");
		Double profitMargin = InputValidator.promptAndParseDouble("Profit Margin : ");
		Double vatRate = InputValidator
				.promptAndParseNullableDouble("VAT Rate ( 0 if not applicale ) : ");

		return new Project(name, profitMargin, surface, vatRate);
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

	public Project listProjects(List<Project> projects) {
		Project selectedProject = null;

		if (projects.size() == 0) {
			IO.clear();
			System.out.println(
					"+-------------------------------------------------------------------------------------------------------------+");
			System.out.println(
					"|                                                                                                             |");
			System.out.println(
					"|                                              No projects found                                              |");
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
					"+--------------------------------------------------------------------------------------------------------------+");
			System.out.println(
					"|  Id |          Project         |          Client          |       Cost      |      Surface      |   Status   |");
			System.out.println(
					"+--------------------------------------------------------------------------------------------------------------+");
			for (Project project : projects) {
				System.out.printf(
						"| %-3d | %-24s | %-24s | %13.2f £ | %14.2f m² | %-10s |\n",
						project.getId(),
						project.getProjectName(),
						project.getClient().getName(),
						project.getTotalCost(),
						project.getSurface(),
						project.getProjectStatus() == null ? "PENDING" : project.getProjectStatus().toString());
				System.out.println(
						"+--------------------------------------------------------------------------------------------------------------+");
			}

			System.out.print(
					"0 - Return to Project Menu \nPlease pick a project by entering their ID \nProject ID : ");
			try {
				int selectedId = IO.getScanner().nextInt();
				if (selectedId == 0)
					return selectedProject;
				selectedProject = projects.stream()
						.filter(project -> project.getId() == selectedId)
						.findFirst()
						.orElse(null);

				if (selectedProject == null) {
					System.out.println("Invalid ID. Please try again.");
					IO.getScanner().next();
				}
			} catch (Exception e) {
				System.out.println("Invalid input. Please enter a valid number.");
				IO.getScanner().next();
				IO.getScanner().next();
			}
		} while (selectedProject == null);

		return selectedProject;

	}

	public void subProjectMenu(Project project) {
		int choice = -1;
		int min = 1;
		int max = 3;
		isRunning = true;
		do {
			IO.clear();
			System.out.println(
					"+--------------------------------------------------------------------------------------------------------------+");
			System.out.println(
					"|  Id |          Project         |          Client          |       Cost      |      Surface      |   Status   |");
			System.out.println(
					"+--------------------------------------------------------------------------------------------------------------+");
			System.out.printf(
					"| %-3d | %-24s | %-24s | %13.2f £ | %14.2f m² | %-10s |\n",
					project.getId(),
					project.getProjectName(),
					project.getClient().getName(),
					project.getTotalCost(),
					project.getSurface(),
					project.getProjectStatus() == null ? "PENDING" : project.getProjectStatus().toString());
			System.out.println(
					"+--------------------------------------------------------------------------------------------------------------+");

			if (project.getProjectStatus() == ProjectStatus.FINISHED)
				return;

			if (project.getProjectStatus() == null) {
				max = 4;

				System.out.println("\t\t+---------------------------------------------+");
				System.out.println("\t\t|                                             |");
				System.out.println("\t\t|     1- Show project summary                 |");
				System.out.println("\t\t|     2- Mark project as validated            |");
				System.out.println("\t\t|     3- Mark project as rejected             |");
				System.out.println("\t\t|     4- Back                                 |");
				System.out.println("\t\t|                                             |");
				System.out.println("\t\t+---------------------------------------------+");
				System.out.print("Pick your choice : ");

				try {
					choice = IO.getScanner().nextInt();
					if (choice >= min && choice <= max) {
						handlePendingProject(project, choice);
					} else {
						System.out.print("Please pick a choice between " + min + " and " + max + "...");
						IO.getScanner().next();
					}
				} catch (Exception e) {
					System.out.println("Invalid input. Please enter a valid number.");
					IO.getScanner().next();
					IO.getScanner().next();
				}
			}

			if (project.getProjectStatus() == ProjectStatus.ONGOING) {
				System.out.println("\t\t+---------------------------------------------+");
				System.out.println("\t\t|                                             |");
				System.out.println("\t\t|     1- Show project summary                 |");
				System.out.println("\t\t|     2- Mark project as finished             |");
				System.out.println("\t\t|     3- Back                                 |");
				System.out.println("\t\t|                                             |");
				System.out.println("\t\t+---------------------------------------------+");
				System.out.print("Pick your choice : ");

				try {
					choice = IO.getScanner().nextInt();
					if (choice >= min && choice <= max) {
						handleOngoingProject(project, choice);
					} else {
						System.out.print("Please pick a choice between " + min + " and " + max + "...");
						IO.getScanner().next();
					}
				} catch (Exception e) {
					System.out.println("Invalid input. Please enter a valid number.");
					IO.getScanner().next();
					IO.getScanner().next();
				}
			}

		} while (isRunning);
	}

	private void handleOngoingProject(Project project, int choice) {
		switch (choice) {
			case 1:
				loadProject(project);
				IO.sysPause();
				break;
			case 2:
				if (!projectService.updateStatus(project, ProjectStatus.FINISHED))
					System.out.println("Unknown error occured while updating status");
				IO.sysPause();
				break;
			case 3:
				isRunning = false;
				break;

			default:
				break;
		}
	}

	private void handlePendingProject(Project project, int choice) {
		switch (choice) {
			case 1:
				loadProject(project);
				IO.sysPause();
				break;
			case 2:
				if (projectService.updateStatus(project, ProjectStatus.ONGOING)) {
					quoteService.updateStatus(project.getQuote(), true);
				} else
					System.out.println("Unknown error occured while updating status");
				IO.sysPause();
				break;
			case 3:
				if (projectService.updateStatus(project, ProjectStatus.CANCELLED)) {
					quoteService.updateStatus(project.getQuote(), false);
				} else
					System.out.println("Unknown error occured while updating status");
				IO.sysPause();
				break;
			case 4:
				isRunning = false;
				break;
			default:
				break;
		}
	}

	private void loadProject(Project project) {
		List<Component> components = new ArrayList<>();

		List<Material> materials = materialService.getAll(project.getId());
		List<WorkForce> workForces = workForceService.getAll(project.getId());

		components.addAll(materials);
		components.addAll(workForces);

		project.setComponents(components);

		showProjectSummary(project, project.getClient(), components);
	}

	public Double showProjectSummary(Project project, Client client, List<Component> components) {

		List<Material> materials = components.stream()
				.filter(component -> component.getComponentType() == ComponentType.MATERIAL)
				.map(component -> (Material) component).toList();

		List<WorkForce> workForces = components.stream()
				.filter(component -> component.getComponentType() == ComponentType.WORKFORCE)
				.map(component -> (WorkForce) component).toList();

		Double totalMaterial = materials.stream()
				.mapToDouble(Material::calculateCost)
				.sum();

		Double totalMaterialVAT = totalMaterial + (totalMaterial * project.getVatRate() / 100);

		Double totalWorkForce = workForces.stream()
				.mapToDouble(WorkForce::calculateCost)
				.sum();

		Double totalWorkForceVAT = totalWorkForce + (totalWorkForce * project.getVatRate() / 100);

		Double totalCost = (totalMaterialVAT + totalWorkForceVAT) * (client.getIsProfessional() ? 0.9 : 1);

		IO.clear();
		System.out.println(
				"+-----------------------------------------------------------------------------------------------------------------------+");
		System.out.println(
				"|\t                                                                                                        \t|");
		System.out.printf(
				"|\t           PROJECT :   %-85s \t|\n", project.getProjectName());
		System.out.println(
				"|\t                                                                                                        \t|");
		System.out.println(
				"|\t                                                                                                        \t|");
		// 101 spaces
		System.out.printf(
				"|\t \t Client : %-84s      \t|\n", client.getName());
		System.out.printf(
				"|\t \t Site Address : %-78s      \t|\n", client.getAddress());
		System.out.printf(
				"|\t \t Surface : %-10.2f m²                                                                          \t|\n",
				project.getSurface());
		System.out.printf(
				"|\t \t Profit Margin : %-5.2f %%                                                                          \t|\n",
				project.getProfitMargin());
		System.out.printf(
				"|\t \t VAT Rate : %-5.2f %%                                                                               \t|\n",
				project.getVatRate());
		System.out.println(
				"|\t                                                                                                        \t|");
		System.out.println(
				"|\t                                                                                                        \t|");
		System.out.println(
				"|\t                                            Components Details                                          \t|");
		System.out.println(
				"|\t                                                                                                        \t|");
		System.out.println(
				"|------------------------------------------------------  Material  -----------------------------------------------------|");
		System.out.println(
				"|\t                                                                                                        \t|");
		for (Material material : materials) {
			System.out.printf(
					"|\t - %-15s: %-9.2f £ (Quantity : %-7.2f, Unit Cost : %6.2f, Quality: %3.1f, Transport: %-6.2f) \t|\n",
					material.getName(),
					material.calculateCost(),
					material.getQuantity(),
					material.getUnitCost(),
					material.getQualityCoefficient(),
					material.getTransportCost());
		}
		System.out.println(
				"|\t                                                                                                        \t|");
		System.out.printf(
				"|\t    Total cost before applying VAT : %10.2f £                                                         \t|\n",
				totalMaterial);
		System.out.printf(
				"|\t    Total cost after applying VAT : %10.2f £                                                          \t|\n",
				totalMaterialVAT);
		System.out.println(
				"|\t                                                                                                        \t|");
		System.out.println(
				"|-----------------------------------------------------  Work Force  ----------------------------------------------------|");
		System.out.println(
				"|\t                                                                                                        \t|");
		for (WorkForce workForce : workForces) {
			System.out.printf(
					"|\t - %-21s: %-9.2f £ (Hourly Rate: %-6.2f £/h, Work Hours: %7.2f h, Productivity: %3.1f)      \t|\n",
					workForce.getName(),
					workForce.calculateCost(),
					workForce.getHourlyRate(),
					workForce.getWorkHours(),
					workForce.getWorkerProductivity());
		}
		System.out.println(
				"|\t                                                                                                        \t|");
		System.out.printf(
				"|\t    Total cost before applying VAT : %10.2f £                                                         \t|\n",
				totalWorkForce);
		System.out.printf(
				"|\t    Total cost after applying VAT : %10.2f £                                                          \t|\n",
				totalWorkForceVAT);
		System.out.println(
				"|\t                                                                                                        \t|");
		System.out.println(
				"|\t                                                                                                        \t|");
		System.out.printf(
				"|\t Total Cost is after applying professional discount : %10.2f                                         \t|\n",
				totalCost);
		System.out.println(
				"|\t                                                                                                        \t|");
		System.out.println(
				"|\t                                                                                                        \t|");
		System.out.println(
				"+-----------------------------------------------------------------------------------------------------------------------+");

		return totalCost;

	}

}