package by.epam.jwd.entity;

import java.io.Serializable;
import java.util.Date;

public class Driver extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    int id;
    String category;
    double drivingExperience;
    Date dateOfEmployment;
    Date dateOfDismissal;
    int users_id;
    int attached_car_id;

    public Driver() {
    }


}
