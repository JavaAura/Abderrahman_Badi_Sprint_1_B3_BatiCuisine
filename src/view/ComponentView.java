package view;

import java.util.ArrayList;
import java.util.List;

import model.Material;
import model.WorkForce;
import util.InputValidator;

public class ComponentView {

    public List<Material> addMaterialUI() {
        List<Material> materials = new ArrayList<>();
        System.out.println("------------ Project Material ------------");
        do {
            String name = InputValidator.promptAndParseString("Component Name : ");
            Double unitCost = InputValidator.promptAndParseDouble("Unit Cost : ");
            Double quantity = InputValidator.promptAndParseDouble("Quantity : ");
            Double transportCost = InputValidator.promptAndParseDouble("Transport Cost : ");
            Double qualityCoefficient = InputValidator.promptAndParseDouble("Quality Coefficient (max 1.5) : ", 1, 1.5);

            Material material = new Material(name, unitCost, quantity, transportCost, qualityCoefficient);
            materials.add(material);
        } while (InputValidator.promptYesOrNo("Do you want to add another material ?"));

        return materials;
    }

    public List<WorkForce> addWorkForceUI() {
        List<WorkForce> workForces = new ArrayList<>();
        System.out.println("----------- Project Work Force -----------");
        do {
            String name = InputValidator.promptAndParseString("Component Name : ");
            Double hourlyRate = InputValidator.promptAndParseDouble("Hourly Rate (£/h) : ");
            Double workHours = InputValidator.promptAndParseDouble("Total Work Hours : ");
            Double workerProductivity = InputValidator.promptAndParseDouble("Worker Productivity (max 1.5) : ", 1, 1.5);

            WorkForce workForce = new WorkForce(name, hourlyRate, workHours, workerProductivity);
            workForces.add(workForce);
        } while (InputValidator.promptYesOrNo("Do you want to add another work force ?"));
        return workForces;
    }

}
