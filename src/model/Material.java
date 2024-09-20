package model;

import enums.ComponentType;

public class Material extends Component {
    private double transportCost;
    private double qualityCoefficient;
    private double quantity;
    private double unitCost;

    public Material() {
    }

    public Material(String name, ComponentType componentType, double vatRate, double unitCost, double quantity,
            double transportCost, double qualityCoefficient) {
        super(name, componentType, vatRate);
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
    }

    public Material(Long id, String name, ComponentType componentType, double vatRate, double unitCost, double quantity,
            double transportCost,
            double qualityCoefficient) {
        super(id, name, componentType, vatRate);
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
    }

    @Override
    public double calculateCost() {
        return (this.quantity * this.unitCost * this.qualityCoefficient) + this.transportCost;
    }

    public double getTransportCost() {
      return this.transportCost;
    }
    public void setTransportCost(double value) {
      this.transportCost = value;
    }

    public double getQualityCoefficient() {
      return this.qualityCoefficient;
    }
    public void setQualityCoefficient(double value) {
      this.qualityCoefficient = value;
    }

    public double getQuantity() {
      return this.quantity;
    }
    public void setQuantity(double value) {
      this.quantity = value;
    }

    public double getUnitCost() {
      return this.unitCost;
    }
    public void setUnitCost(double value) {
      this.unitCost = value;
    }
}
