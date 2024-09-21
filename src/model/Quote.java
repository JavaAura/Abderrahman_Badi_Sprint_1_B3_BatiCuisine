package model;

import java.time.LocalDate;

public class Quote {
  private Long id;
  private double estimatedAmount;
  private LocalDate issueDate;
  private LocalDate validityDate;
  private boolean isAccepted;

  public Quote() {
  }

  public Quote(double estimatedAmount, LocalDate issueDate, LocalDate validityDate, boolean isAccepted) {
    this.estimatedAmount = estimatedAmount;
    this.issueDate = issueDate;
    this.validityDate = validityDate;
    this.isAccepted = isAccepted;
  }

  public Quote(long id, double estimatedAmount, LocalDate issueDate, LocalDate validityDate, boolean isAccepted) {
    this.id = id;
    this.estimatedAmount = estimatedAmount;
    this.issueDate = issueDate;
    this.validityDate = validityDate;
    this.isAccepted = isAccepted;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public double getEstimatedAmount() {
    return this.estimatedAmount;
  }

  public void setEstimatedAmount(double value) {
    this.estimatedAmount = value;
  }

  public LocalDate getIssueDate() {
    return this.issueDate;
  }

  public void setIssueDate(LocalDate value) {
    this.issueDate = value;
  }

  public LocalDate getValidityDate() {
    return this.validityDate;
  }

  public void setValidityDate(LocalDate value) {
    this.validityDate = value;
  }

  public boolean getIsAccepted() {
    return this.isAccepted;
  }

  public void setIsAccepted(boolean value) {
    this.isAccepted = value;
  }
}
