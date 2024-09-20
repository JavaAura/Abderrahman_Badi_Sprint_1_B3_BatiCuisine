package model;

import java.util.List;

import enums.ProjectStatus;

public class Project {
  private Long id;
  private String projectName;
  private Double profitMargin;
  private Double totalCost;
  private ProjectStatus projectStatus;
  private Double surface;
  private Client client;
  private List<Component> components;

  public Project() {
  }

  public Project(String projectName, Double profitMargin, Double totalCost, ProjectStatus projectStatus, Double surface,
      Client client) {
    this.projectName = projectName;
    this.profitMargin = profitMargin;
    this.totalCost = totalCost;
    this.projectStatus = projectStatus;
    this.surface = surface;
    this.client = client;
  }

  public Project(Long id, String projectName, Double profitMargin, Double totalCost, ProjectStatus projectStatus, Double surface,
      Client client) {
    this.id = id;
    this.projectName = projectName;
    this.profitMargin = profitMargin;
    this.totalCost = totalCost;
    this.projectStatus = projectStatus;
    this.surface = surface;
    this.client = client;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public String getProjectName() {
    return this.projectName;
  }

  public void setProjectName(String value) {
    this.projectName = value;
  }

  public Double getProfitMargin() {
    return this.profitMargin;
  }

  public void setProfitMargin(Double value) {
    this.profitMargin = value;
  }

  public Double getTotalCost() {
    return this.totalCost;
  }

  public void setTotalCost(Double value) {
    this.totalCost = value;
  }

  public ProjectStatus getProjectStatus() {
    return this.projectStatus;
  }

  public void setProjectStatus(ProjectStatus value) {
    this.projectStatus = value;
  }

  public Client getClient() {
    return this.client;
  }

  public void setClient(Client value) {
    this.client = value;
  }

  public List<Component> getComponents() {
    return this.components;
  }

  public void setComponents(List<Component> value) {
    this.components = value;
  }

  public Double getSurface() {
    return this.surface;
  }

  public void setSurface(Double value) {
    this.surface = value;
  }
}
