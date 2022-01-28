package by.epam.jwd.entity;

import java.util.Date;

public class Order {
    private int id;
    private String criteria;
    private Date requestDate;
    private String departPlace;
    private String arrivalPlace;
    private Date startDate;
    private Date end_date;
    private String order_status;
    private String distance;
    private String totalAmount;
    private String paymentStatus;
    private int clientId;
    private String clientName;
    private String clientSurname;
    private int carId;
    private String carLicensePlate;
    private int driverId;
    private String driverName;
    private String driverSurname;
    private int adminId;
    private String adminName;
    private String adminSurname;

    public Order() {
    }

    public Order(int id, String criteria, Date requestDate) {
        this.id = id;
        this.criteria = criteria;
        this.requestDate = requestDate;
    }

    public Order(int id, String criteria, Date requestDate, String departPlace, String arrivalPlace, Date startDate,
                 Date end_date, String order_status, String distance, String totalAmount, String paymentStatus, int clientId,
                 String clientName, String clientSurname, int carId, String carLicensePlate, int driverId, String driverName, String driverSurname,
                 int adminId, String adminName, String adminSurname) {
        this.id = id;
        this.criteria = criteria;
        this.requestDate = requestDate;
        this.departPlace = departPlace;
        this.arrivalPlace = arrivalPlace;
        this.startDate = startDate;
        this.end_date = end_date;
        this.order_status = order_status;
        this.distance = distance;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.carId = carId;
        this.carLicensePlate = carLicensePlate;
        this.driverId = driverId;
        this.driverSurname = driverSurname;
        this.driverName = driverName;
        this.adminId = adminId;
        this.adminSurname = adminSurname;
        this.adminName = adminName;
    }

    @Override
    public String toString() {
        return "\n Order{" +
                "id=" + id +
                ", criteria='" + criteria + '\'' +
                ", requestDate=" + requestDate +
                ", departPlace='" + departPlace + '\'' +
                ", arrivalPlace='" + arrivalPlace + '\'' +
                ", startDate=" + startDate +
                ", end_date=" + end_date +
                ", order_status='" + order_status + '\'' +
                ", distance='" + distance + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", clientSurname='" + clientSurname + '\'' +
                ", carId=" + carId +
                ", carLicensePlate='" + carLicensePlate + '\'' +
                ", driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", driverSurname='" + driverSurname + '\'' +
                ", adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminSurname='" + adminSurname + '\'' +
                '}';
    }
}
