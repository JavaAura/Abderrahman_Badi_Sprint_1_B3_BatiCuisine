package model;

import enums.ComponentType;

public abstract class Component {
    private Long id;
    private String name;
    private ComponentType componentType;
    private double vatRate;

    public Component(){}

    public Component(String name, ComponentType componentType, double vatRate){
        this.name = name;
        this.componentType = componentType;
        this.vatRate = vatRate;
    }

    public Component(Long id ,String name, ComponentType componentType, double vatRate){
        this.id = id;
        this.name = name;
        this.componentType = componentType;
        this.vatRate = vatRate;
    }

  

    public Long getId() {
      return this.id;
    }
    public void setId(Long value) {
      this.id = value;
    }

    public String getName() {
      return this.name;
    }
    public void setName(String value) {
      this.name = value;
    }

    public ComponentType getComponentType() {
      return this.componentType;
    }
    public void setComponentType(ComponentType value) {
      this.componentType = value;
    }

    public double getVatRate() {
      return this.vatRate;
    }
    public void setVatRate(double value) {
      this.vatRate = value;
    }
}
