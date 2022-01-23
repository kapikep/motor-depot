package by.epam.jwd.entity;

import java.io.Serializable;
import java.util.Objects;

public class CarModel implements Serializable{
 
	private static final long serialVersionUID = 1L;
	private int id;
    private String modelName;
    private String type;
    private int loadCapacity;
    private int passengerCapacity;
    private String wheelDriveType;
    
    public CarModel() {}

    public CarModel(int id, String modelName, String type, int loadCapacity, int passengerCapacity, String wheelDriveType) {
        this.id = id;
        this.modelName = modelName;
        this.type = type;
        this.loadCapacity = loadCapacity;
        this.passengerCapacity = passengerCapacity;
        this.wheelDriveType = wheelDriveType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public String getWheelDriveType() {
        return wheelDriveType;
    }

    public void setWheelDriveType(String wheelDriveType) {
        this.wheelDriveType = wheelDriveType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarModel carModel = (CarModel) o;
        return id == carModel.id && loadCapacity == carModel.loadCapacity && passengerCapacity == carModel.passengerCapacity && Objects.equals(modelName, carModel.modelName) && Objects.equals(type, carModel.type) && Objects.equals(wheelDriveType, carModel.wheelDriveType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelName, type, loadCapacity, passengerCapacity, wheelDriveType);
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "id=" + id +
                ", ModelName='" + modelName + '\'' +
                ", type='" + type + '\'' +
                ", loadCapacity=" + loadCapacity +
                ", passengerCapacity=" + passengerCapacity +
                ", wheelDriveType='" + wheelDriveType + '\'' +
                '}';
    }
}
