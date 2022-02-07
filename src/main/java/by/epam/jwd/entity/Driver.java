package by.epam.jwd.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Driver extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String category;
    private int drivingExperience;
    private Date dateOfEmployment;
    private Date dateOfDismissal;
    private int attachedCarId;
    private String license_plate;

    public Driver() {
    }

    public Driver(String name, String surname, String login, String password, String phoneNumber, String photo,
                  Status status, String eMail, String additionalInfo, Role role, String category, int drivingExperience,
                  Date dateOfEmployment, Date dateOfDismissal, int attachedCarId, String license_plate) {
        super(name, surname, login, password, phoneNumber, photo, status, eMail, additionalInfo, role);
        this.category = category;
        this.drivingExperience = drivingExperience;
        this.dateOfEmployment = dateOfEmployment;
        this.dateOfDismissal = dateOfDismissal;
        this.attachedCarId = attachedCarId;
        this.license_plate = license_plate;
    }

    public Driver(int id, String name, String surname, String login, String password, String phoneNumber,
                  String photo, Status status, String eMail, String additionalInfo, Role role, int user_id, String category,
                  int drivingExperience, Date dateOfEmployment, Date dateOfDismissal, int attachedCarId, String license_plate) {
        super(user_id, name, surname, login, password, phoneNumber, photo, status, eMail, additionalInfo, role);
        this.id = id;
        this.category = category;
        this.drivingExperience = drivingExperience;
        this.dateOfEmployment = dateOfEmployment;
        this.dateOfDismissal = dateOfDismissal;
        this.attachedCarId = attachedCarId;
        this.license_plate = license_plate;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getUserId(){
        return super.getId();
    }

    public void setUserId(int userId){
        super.setId(userId);
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDrivingExperience() {
        return drivingExperience;
    }

    public void setDrivingExperience(int drivingExperience) {
        this.drivingExperience = drivingExperience;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public Date getDateOfDismissal() {
        return dateOfDismissal;
    }

    public void setDateOfDismissal(Date dateOfDismissal) {
        this.dateOfDismissal = dateOfDismissal;
    }

    public int getAttachedCarId() {
        return attachedCarId;
    }

    public void setAttachedCarId(int attachedCarId) {
        this.attachedCarId = attachedCarId;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;
        if (!super.equals(o)) return false;
        Driver driver = (Driver) o;
        return id == driver.id && drivingExperience == driver.drivingExperience && attachedCarId == driver.attachedCarId && Objects.equals(category, driver.category) && Objects.equals(dateOfEmployment, driver.dateOfEmployment) && Objects.equals(dateOfDismissal, driver.dateOfDismissal) && Objects.equals(license_plate, driver.license_plate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, category, drivingExperience, dateOfEmployment, dateOfDismissal, attachedCarId, license_plate);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", drivingExperience=" + drivingExperience +
                ", dateOfEmployment=" + dateOfEmployment +
                ", dateOfDismissal=" + dateOfDismissal +
                ", attached_car_id=" + attachedCarId +
                ", license_plate='" + license_plate + '\'' + super.toString() +
                '}' ;
    }
}
