package com.musala.rest.validation;
import org.springframework.validation.Errors;

public class DroneValidationErrorBuilder {

    public static DroneValidationError fromBindingErrors(Errors errors){
        DroneValidationError droneValidationError = new DroneValidationError("Drone Validation Failed. Count Of "+errors.getErrorCount()+" Errors.");

        errors.getAllErrors().stream().forEach((objectError -> { droneValidationError.addError(objectError.getDefaultMessage());}));

        return droneValidationError;
    }
}
