package com.musala.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.musala.domain.Medication;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.URL;

@JsonSerialize
public class MedicationDto extends Medication {

    @JsonProperty("name")
    @Pattern(regexp = "[A-Za-z0-9]+\\-*\\_*",message = "Allowed Only Letters, Underscores and Numbers")
    private String name;

    @JsonProperty("weight")
    @Positive(message = "Weight Must Be Positive Values Only.")
    private Double weight;

    @JsonProperty("code")
    @Pattern(regexp = "[A-Z]+\\-*\\_*", message = "Allowed Only Uppercase Letters, Underscore, And Numbers.")
    private String code;

    @JsonProperty("imageUrl")
    @NotNull
    @URL
    private String imageURL;

}
