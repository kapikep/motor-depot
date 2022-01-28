package by.epam.jwd.entity;

import java.util.Date;

public class Order {
    private int id;
    private String criteria;
    private Date requestDate;
    private String departPlace;
    private String arrivalPlace;
    private Date startDate;
    private Date endDate;
    private String orderStatus;
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
                 Date endDate, String orderStatus, String distance, String totalAmount, String paymentStatus, int clientId,
                 String clientName, String clientSurname, int carId, String carLicensePlate, int driverId, String driverName, String driverSurname,
                 int adminId, String adminName, String adminSurname) {
        this.id = id;
        this.criteria = criteria;
        this.requestDate = requestDate;
        this.departPlace = departPlace;
        this.arrivalPlace = arrivalPlace;
        this.startDate = startDate;
        this.endDate = endDate;
        this.orderStatus = orderStatus;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getDepartPlace() {
        return departPlace;
    }

    public void setDepartPlace(String departPlace) {
        this.departPlace = departPlace;
    }

    public String getArrivalPlace() {
        return arrivalPlace;
    }

    public void setArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverSurname() {
        return driverSurname;
    }

    public void setDriverSurname(String driverSurname) {
        this.driverSurname = driverSurname;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminSurname() {
        return adminSurname;
    }

    public void setAdminSurname(String adminSurname) {
        this.adminSurname = adminSurname;
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
                ", end_date=" + endDate +
                ", order_status='" + orderStatus + '\'' +
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
