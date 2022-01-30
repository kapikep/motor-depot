package by.epam.jwd.entity;

import java.io.Serializable;
import java.util.Date;

public class Driver extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String category;
    private double drivingExperience;
    private Date dateOfEmployment;
    private Date dateOfDismissal;
    private int users_id;
    private int attached_car_id;

    public Driver() {
    }

    public Driver(int id, String name, String surname, String login, String password, double phoneNumber, String photo,
                  Status status, String eMail, Role role, String category, double drivingExperience,
                  Date dateOfEmployment, Date dateOfDismissal, int users_id, int attached_car_id) {
        super(users_id, name, surname, login, password, phoneNumber, photo, status, eMail, role);
        this.id = id;
        this.category = category;
        this.drivingExperience = drivingExperience;
        this.dateOfEmployment = dateOfEmployment;
        this.dateOfDismissal = dateOfDismissal;
        this.users_id = users_id;
        this.attached_car_id = attached_car_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getDrivingExperience() {
        return drivingExperience;
    }

    public void setDrivingExperience(double drivingExperience) {
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

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public int getAttached_car_id() {
        return attached_car_id;
    }

    public void setAttached_car_id(int attached_car_id) {
        this.attached_car_id = attached_car_id;
    }
}
