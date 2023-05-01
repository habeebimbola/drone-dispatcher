package com.musala.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;

public class MedicationValidationError {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errors = new ArrayList<>();

    private final String errorMessage;

    public MedicationValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addError(String errorMessage)
    {
        errors.add(errorMessage);
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
