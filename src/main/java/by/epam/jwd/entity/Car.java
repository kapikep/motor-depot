package by.epam.jwd.entity;

import java.io.Serializable;

public class Car extends CarModel implements Serializable { //extends carModel?
   
	private static final long serialVersionUID = -5841059907584776379L;
	private int id;
    private String licencePlate;
    private String color;
    private int carModelId;

    public Car(int id, String licencePlate, String color, int carModelId, String modelName, String type, int loadCapacity, int passengerCapacity, String wheelDriveType) {
        super(carModelId, modelName, type, loadCapacity, passengerCapacity, wheelDriveType);
        this.id = id;
        this.licencePlate = licencePlate;
        this.color = color;
        this.carModelId = carModelId;
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
