package by.epam.jwd.entity;

import java.io.Serializable;
import java.util.Objects;

public class Car extends CarModel  { //extends carModel?
   
	private static final long serialVersionUID = 1L;
	private int id;
    private String licencePlate;
    private String color;
    private int carModelId;
    private String odometr;
    private String status;
    private String photo;

    public Car() {}

    public Car(int id, String licencePlate, String color, String photo, String odometr, String status,  int carModelId) {
        this.id = id;
        this.licencePlate = licencePlate;
        this.color = color;
        this.carModelId = carModelId;
        this.odometr = odometr;
        this.status = status;
        this.photo = photo;
    }

    public Car(int id, String licencePlate, String color, int carModelId, String modelName, String type, int loadCapacity, int passengerCapacity, String wheelDriveType,
               String odometr, String status, String photo) {
        super(carModelId, modelName, type, loadCapacity, passengerCapacity, wheelDriveType);
        this.id = id;
        this.licencePlate = licencePlate;
        this.color = color;
        this.carModelId = carModelId;
        this.odometr = odometr;
        this.status = status;
        this.photo = photo;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(int carModelId) {
        this.carModelId = carModelId;
    }

    public String getOdometr() {
        return odometr;
    }

    public void setOdometr(String odometr) {
        this.odometr = odometr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return id == car.id && carModelId == car.carModelId && Objects.equals(licencePlate, car.licencePlate) && Objects.equals(color, car.color) && Objects.equals(odometr, car.odometr) && Objects.equals(status, car.status) && Objects.equals(photo, car.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, licencePlate, color, carModelId, odometr, status, photo);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", licencePlate='" + licencePlate + '\'' +
                ", color='" + color + '\'' +
                ", carModelId=" + carModelId +
                ", odometr='" + odometr + '\'' +
                ", status='" + status + '\'' +
                ", photo='" + photo + '\'' +
                '}' + super.toString()+ "\n";
    }
}
