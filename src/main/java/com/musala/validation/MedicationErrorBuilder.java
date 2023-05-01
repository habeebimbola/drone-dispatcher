package com.musala.validation;

import org.springframework.validation.Errors;

public class MedicationErrorBuilder {

    public static MedicationValidationError fromBindingErrors(Errors errors)
    {
        MedicationValidationError medicationValidationError = new MedicationValidationError(""+errors.getErrorCount()+" errors occurred. Please modify.");
        errors.getAllErrors().stream().forEach((objectError -> {  medicationValidationError.addError(objectError.getDefaultMessage());}));
        return medicationValidationError;
    }
}
