package com.musala.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.musala.domain.Medication;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.URL;

@JsonSerialize
public class MedicationDto {

    @JsonIgnore
    private Integer id;

    @JsonProperty("name")
    @Pattern(regexp = "[A-Za-z0-9]+_*-*",message = "Allowed Only Letters, Underscores and Numbers")
    private String name;

    @JsonProperty("weight")
    @Positive(message = "Weight Must Be Positive Values Only.")
    private Double weight;

    @NotNull
    @JsonProperty("code")
    @Pattern(regexp = "[A-Z_0-9]+-*", message = "Allowed Only Uppercase Letters, Underscore, And Numbers.")
    private String code;

    @JsonProperty("imageUrl")
    @URL
    @NotNull
    private String imageURL;

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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
