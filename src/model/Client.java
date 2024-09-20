package model;

public class Client {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private Boolean isProfessional;


    public Client(){

    }


    public Client(String name, String address, String phoneNumber , Boolean isProfessional){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isProfessional = isProfessional;

    }

    public Client(Long id ,String name, String address, String phoneNumber , Boolean isProfessional){
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isProfessional = isProfessional;

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

    public String getAddress() {
      return this.address;
    }
    public void setAddress(String value) {
      this.address = value;
    }

    public String getPhoneNumber() {
      return this.phoneNumber;
    }
    public void setPhoneNumber(String value) {
      this.phoneNumber = value;
    }

    public Boolean getIsProfessional() {
      return this.isProfessional;
    }
    public void setIsProfessional(Boolean value) {
      this.isProfessional = value;
    }
}
