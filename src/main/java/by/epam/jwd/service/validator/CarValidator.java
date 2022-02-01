package by.epam.jwd.service.validator;

import java.util.Arrays;
import java.util.List;

public class CarValidator {
    private final List<String> COLUMN_CAR_NAMES = Arrays.asList("id", "licence_plate", "color", "car_photo", "odometr", "status", "car_model_id");
    private final List<String> COLUMN_CAR_MODEL_NAMES = Arrays.asList("id", "model_name", "type", "load_capacity", "passenger_capacity", "wheel_drive_type");
}
