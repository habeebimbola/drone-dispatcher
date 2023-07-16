package com.musala.rest.validation;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class DroneValidationError {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errors  = new ArrayList<>();

    private final String errorMessage;

    public DroneValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addError(String error)
    {
        errors.add(error);
    }
    public List<String> getErrors() {
        return errors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
