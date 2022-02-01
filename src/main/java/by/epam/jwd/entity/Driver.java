package by.epam.jwd.entity;

import java.io.Serializable;
import java.util.Date;

public class Driver extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String category;
    private int drivingExperience;
    private Date dateOfEmployment;
    private Date dateOfDismissal;
    private int attached_car_id;
    private String license_plate;

    public Driver() {
    }

    public Driver(String name, String surname, String login, String password, String phoneNumber, String photo,
                  Status status, String eMail, String additionalInfo, Role role, String category, int drivingExperience,
                  Date dateOfEmployment, Date dateOfDismissal, int attached_car_id, String license_plate) {
        super(name, surname, login, password, phoneNumber, photo, status, eMail, additionalInfo, role);
        this.category = category;
        this.drivingExperience = drivingExperience;
        this.dateOfEmployment = dateOfEmployment;
        this.dateOfDismissal = dateOfDismissal;
        this.attached_car_id = attached_car_id;
        this.license_plate = license_plate;
    }

    public Driver(int id, String name, String surname, String login, String password, String phoneNumber,
                  String photo, Status status, String eMail, String additionalInfo, Role role, int user_id, String category,
                  int drivingExperience, Date dateOfEmployment, Date dateOfDismissal, int attached_car_id, String license_plate) {
        super(user_id, name, surname, login, password, phoneNumber, photo, status, eMail, additionalInfo, role);
        this.id = id;
        this.category = category;
        this.drivingExperience = drivingExperience;
        this.dateOfEmployment = dateOfEmployment;
        this.dateOfDismissal = dateOfDismissal;
        this.attached_car_id = attached_car_id;
        this.license_plate = license_plate;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getUserId(){
        return super.getId();
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

    public int getAttached_car_id() {
        return attached_car_id;
    }

    public void setAttached_car_id(int attached_car_id) {
        this.attached_car_id = attached_car_id;
    }



    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", drivingExperience=" + drivingExperience +
                ", dateOfEmployment=" + dateOfEmployment +
                ", dateOfDismissal=" + dateOfDismissal +
                ", attached_car_id=" + attached_car_id +
                ", license_plate='" + license_plate + '\'' + super.toString() +
                '}' ;
    }
}
