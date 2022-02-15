package by.epam.jwd.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String criteria;
    private Date requestDate;
    private String departPlace;
    private String arrivalPlace;
    private Date startDate;
    private Date endDate;
    private String orderStatus;
    private int distance;
    private int totalAmount;
    private String paymentStatus;
    private int clientId;
    private String contactDetails;
    private String clientPhone;
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
                 Date endDate, String orderStatus, int distance, int totalAmount, String paymentStatus, int clientId,
                 String contactDetails, String clientPhone, int carId, String carLicensePlate, int driverId, String driverName, String driverSurname,
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
        this.contactDetails = contactDetails;
        this.clientPhone = clientPhone;
        this.carId = carId;
        this.carLicensePlate = carLicensePlate;
        this.driverId = driverId;
        this.driverSurname = driverSurname;
        this.driverName = driverName;
        this.adminId = adminId;
        this.adminSurname = adminSurname;
        this.adminName = adminName;
    }

    public Order(int id, String criteria, Date requestDate, String departPlace, String arrivalPlace, Date startDate,
                 Date endDate, String orderStatus, int distance, int totalAmount, String paymentStatus, int clientId,
                 String contactDetails, String clientPhone, int carId, int driverId, int adminId) {
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
        this.contactDetails = contactDetails;
        this.clientPhone = clientPhone;
        this.carId = carId;
        this.driverId = driverId;
        this.adminId = adminId;
    }

    public Order(String criteria, Date requestDate, String departPlace, String arrivalPlace, Date startDate,
                 Date endDate, String orderStatus, int distance, int totalAmount, String paymentStatus, int clientId,
                 String contactDetails, String clientPhone, int carId, int driverId, int adminId) {
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
        this.contactDetails = contactDetails;
        this.clientPhone = clientPhone;
        this.carId = carId;
        this.driverId = driverId;
        this.adminId = adminId;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
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

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id && distance == order.distance && totalAmount == order.totalAmount &&
                clientId == order.clientId && carId == order.carId && driverId == order.driverId && adminId == order.adminId && Objects.equals(criteria, order.criteria) &&
                Objects.equals(requestDate, order.requestDate) && Objects.equals(departPlace, order.departPlace) && Objects.equals(arrivalPlace, order.arrivalPlace) &&
                Objects.equals(startDate, order.startDate) && Objects.equals(endDate, order.endDate) && Objects.equals(orderStatus, order.orderStatus) && Objects.equals(paymentStatus, order.paymentStatus) &&
                Objects.equals(contactDetails, order.contactDetails) && Objects.equals(clientPhone, order.clientPhone) && Objects.equals(carLicensePlate, order.carLicensePlate) && Objects.equals(driverName, order.driverName) &&
                Objects.equals(driverSurname, order.driverSurname) && Objects.equals(adminName, order.adminName) && Objects.equals(adminSurname, order.adminSurname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, criteria, requestDate, departPlace, arrivalPlace, startDate, endDate, orderStatus, distance, totalAmount, paymentStatus, clientId, contactDetails, clientPhone, carId, carLicensePlate, driverId, driverName, driverSurname, adminId, adminName, adminSurname);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", criteria='" + criteria + '\'' +
                ", requestDate=" + requestDate +
                ", departPlace='" + departPlace + '\'' +
                ", arrivalPlace='" + arrivalPlace + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", orderStatus='" + orderStatus + '\'' +
                ", distance='" + distance + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", clientId=" + clientId +
                ", contactDetails='" + contactDetails + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
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
