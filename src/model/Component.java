package model;

import enums.ComponentType;

public abstract class Component {
    private Long id;
    private String name;
    private ComponentType componentType;

    public Component(){}

    public Component(String name){
        this.name = name;
    }

    public Component(Long id ,String name, ComponentType componentType){
        this.id = id;
        this.name = name;
        this.componentType = componentType;
    }

    public abstract double calculateCost();

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
}
