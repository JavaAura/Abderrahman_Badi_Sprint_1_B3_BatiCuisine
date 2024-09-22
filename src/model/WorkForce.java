package model;

import enums.ComponentType;

public class WorkForce extends Component {
    private double hourlyRate;
    private double workHours;
    private double workerProductivity;

    public WorkForce() {
    }

    public WorkForce(String name, double hourlyRate, double workHours,
            double workerProductivity) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.workHours = workHours;
        this.workerProductivity = workerProductivity;
    }

    public WorkForce(Long id, String name, ComponentType componentType, double hourlyRate,
            double workHours, double workerProductivity) {
        super(id, name, componentType);
        this.hourlyRate = hourlyRate;
        this.workHours = workHours;
        this.workerProductivity = workerProductivity;
    }

    @Override
    public double calculateCost() {
        return (this.hourlyRate * this.workHours) * this.workerProductivity;
    }

    public double getHourlyRate() {
      return this.hourlyRate;
    }
    public void setHourlyRate(double value) {
      this.hourlyRate = value;
    }

    public double getWorkHours() {
      return this.workHours;
    }
    public void setWorkHours(double value) {
      this.workHours = value;
    }

    public double getWorkerProductivity() {
      return this.workerProductivity;
    }
    public void setWorkerProductivity(double value) {
      this.workerProductivity = value;
    }
}
