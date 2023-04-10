package com.musala.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@JsonSerialize
public class MedicationDto {

    @JsonProperty("name")
    @Pattern(regexp = "[A-Za-z0-9]+\\-*\\_*",message = "Allowed Only Letters, Underscores and Numbers")
    private String name;

    @JsonProperty("weight")
    @Positive(message = "Weight Must Be Positive Values Only.")
    private Double weight;

    @JsonProperty("code")
    @Pattern(regexp = "[A-Z]+\\-*\\_*", message = "Allowed Only Uppercase Letters, Underscore, And Numbers.")
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
