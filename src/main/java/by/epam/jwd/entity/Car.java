package by.epam.jwd.entity;

import java.io.Serializable;
import java.util.Objects;

public class Car extends CarModel  { //extends carModel?
   
	private static final long serialVersionUID = 1L;
	private int id;
    private String licencePlate;
    private String color;
    private int carModelId;
    
    public Car() {}

    public Car(int id, String licencePlate, String color, int carModelId, String modelName, String type, int loadCapacity, int passengerCapacity, String wheelDriveType) {
        super(carModelId, modelName, type, loadCapacity, passengerCapacity, wheelDriveType);
        this.id = id;
        this.licencePlate = licencePlate;
        this.color = color;
        this.carModelId = carModelId;
    }

    public int getId() {
        return id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return id == car.id && carModelId == car.carModelId && Objects.equals(licencePlate, car.licencePlate) && Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, licencePlate, color, carModelId);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", licencePlate='" + licencePlate + '\'' +
                ", color='" + color + '\'' +
                ", carModel=" + carModelId +
                "}" + super.toString()+ "\n";
    }
}
