package by.epam.jwd.entity;

import java.io.Serializable;

public class CarModel implements Serializable{
 
	private static final long serialVersionUID = 1L;
	private int id;
    private String ModelName;
    private String type;
    private int loadCapacity;
    private int passengerCapacity;
    private String wheelDriveType;
    
    public CarModel() {}

    public CarModel(int id, String modelName, String type, int loadCapacity, int passengerCapacity, String wheelDriveType) {
        this.id = id;
        ModelName = modelName;
        this.type = type;
        this.loadCapacity = loadCapacity;
        this.passengerCapacity = passengerCapacity;
        this.wheelDriveType = wheelDriveType;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "id=" + id +
                ", ModelName='" + ModelName + '\'' +
                ", type='" + type + '\'' +
                ", loadCapacity=" + loadCapacity +
                ", passengerCapacity=" + passengerCapacity +
                ", wheelDriveType='" + wheelDriveType + '\'' +
                '}';
    }
}
